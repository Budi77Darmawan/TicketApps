package com.example.ticketapps.profile.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.MainActivity
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityEditBinding
import com.example.ticketapps.profile.ProfileApiService
import com.example.ticketapps.util.ApiClient
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class EditActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditBinding
    private lateinit var viewModel : EditViewModel
    private lateinit var defaultPref: SharedPrefProvider

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        val service = ApiClient.getApiClient(this)?.create(ProfileApiService::class.java)

        defaultPref = SharedPrefProvider(this)

        if (service != null) {
            viewModel.setLoginService(service)
        }

        binding.btnUpload.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }

    }


    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE ->{
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            binding.imageView.setImageURI(data?.data)

            val filePath = getPath(this, data?.data)
            val file = File(filePath)

            val img: MultipartBody.Part?
            val mediaTypeImg = "image/jpeg".toMediaType()
            val inputStream = contentResolver.openInputStream(data?.data!!)
            val reqFile: RequestBody? = inputStream?.readBytes()?.toRequestBody(mediaTypeImg)
            val id = defaultPref.getString(Constant.KEY_ID_ACCOUNT)?:""

            img = reqFile?.let { it1 ->
                MultipartBody.Part.createFormData("image", file.name,
                    it1
                )
            }

            binding.btnProfile.setOnClickListener {


                val city = createPartFromString(binding.city.text.toString())
                val address = createPartFromString(binding.address.text.toString())




                if (img != null) {
                    viewModel.postProfile(city, address, img)
                }
               onBackPressed()
            }
        }

    }

    fun getPath(context: Context, uri: Uri?): String {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? =
            uri?.let { context.contentResolver.query(it, proj, null, null, null) }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        if (result == null) {
            result = "Not found"
        }
        return result
    }

    @NonNull
    private fun createPartFromString(json: String): RequestBody {
        val mediaType = "multipart/form-data".toMediaType()
        return json
            .toRequestBody(mediaType)
    }
}