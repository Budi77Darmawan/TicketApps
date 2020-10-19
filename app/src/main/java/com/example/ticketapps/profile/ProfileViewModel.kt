package com.example.ticketapps.profile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProfileViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isProfileLiveData = MutableLiveData<Boolean>()
    val listLiveData = MutableLiveData<List<ProfileModel>>()

    private lateinit var service: ProfileApiService
    private lateinit var sharedPref: SharedPrefProvider

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setProfileService(service: ProfileApiService) {
        this.service = service
    }

    fun getSharedPreferences(mContext:Context) {
        sharedPref = SharedPrefProvider(mContext)
    }


    fun callProfileApi(id: String) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getProfileIdRequest(id.toInt())

                } catch (e: Throwable) {
                    e.printStackTrace()
                    withContext(Job() + Dispatchers.Main) {
                        isProfileLiveData.value = false
                    }
                }
            }

            if (response is ProfileResponse) {
                val data = response.data
                val list =
                    ProfileModel(
                        data.idAccount,
                        data.full_name,
                        data.email,
                        data.phoneNumber,
                        data.image,
                        data.city,
                        data.address)

                listLiveData.value = listOf(list)
                isProfileLiveData.value = true
            }
            isLoadingLiveData.value = false
        }
    }

}