package com.example.ticketapps.profile

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import com.example.ticketapps.util.ApiClient
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentProfilBinding
import com.example.ticketapps.login.LoginScreenActivity
import com.squareup.picasso.Picasso


class ProfilFragment : Fragment() {
    private lateinit var sharedPref: SharedPrefProvider
    private lateinit var binding: FragmentProfilBinding
    private lateinit var viewModel: ProfileViewModel
    private fun getPhotoImage(file: String) : String = "http://10.0.2.2:9090/uploads/$file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profil, container, false)
        sharedPref = SharedPrefProvider(requireContext())

        val service = ApiClient.getApiClient(this.requireContext())?.create(ProfileApiService::class.java)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.getSharedPreferences(requireContext())
        val idAccount = sharedPref.getString(Constant.KEY_ID_ACCOUNT)?: ""
        if (service != null) {
            viewModel.setProfileService(service)
        viewModel.callProfileApi(idAccount)
        subscribeLiveData()
    }


    return binding.root
    }


    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isProfileLiveData.observe(viewLifecycleOwner, {
            if (it) {
                val data = viewModel.listLiveData.value?.firstOrNull()

                if (data?.image.isNullOrEmpty()) binding.photoProfile.setImageResource(R.drawable.blank_portrait)
                else Glide.with(this).load(getPhotoImage(data!!.image!!)).placeholder(R.drawable.blank_portrait).into(binding.photoProfile)
                Log.d("imageku","${data?.image?.let { it1 -> getPhotoImage(it1) }}")
                binding.tvNameProfile.text = data?.full_name?: "Full_name"
                binding.tvCity.text = data?.city ?: "City"
                binding.tvLocation.text = data?.address ?: "Address"
                binding.tvLogout.setOnClickListener {
                    logout()
                }
            } else {
                Toast.makeText(this.requireContext(), "Data Empty", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun logout() {
        val dialog = activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Log out")
                .setMessage("Are you sure want to logout?")
                .setCancelable(false)
                .setPositiveButton("YES") { _: DialogInterface?, _: Int ->
                    sharedPref.resetSharedPref()
                    var i = Intent(activity, LoginScreenActivity::class.java)
                    startActivity(i)
                    requireActivity().finish()
                    Toast.makeText(it, "Log Out", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("NO") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
        }
        dialog!!.show()
        }
}