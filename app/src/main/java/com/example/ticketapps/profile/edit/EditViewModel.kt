package com.example.ticketapps.profile.edit

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticketapps.profile.ProfileApiService
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import kotlin.coroutines.CoroutineContext

class EditViewModel : ViewModel(), CoroutineScope {

    private lateinit var service: ProfileApiService

    override val coroutineContext: CoroutineContext
    get() = Job() + Dispatchers.Main


    fun setLoginService(service: ProfileApiService) {
        this.service = service
    }

    fun postProfile(city : RequestBody, address : RequestBody, image : MultipartBody.Part ) {
        launch {

            val response = withContext(Dispatchers.IO) {
                try {
                    service.patchProfile(city, address, image)
                } catch (e: Throwable) {
                    e.printStackTrace()

                }

            }

            Log.d("respone", "${response is EditResponse}")
            if (response is EditResponse) {
                // Action Success
            }
        }
    }
}