package com.example.ticketapps.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hiringapps.sharedpref.SharedPrefProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RegisterViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isRegisterLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    private lateinit var service: RegisterApiService
    private lateinit var sharedPref: SharedPrefProvider

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setRegisterService(service: RegisterApiService) {
        this.service = service
    }

    fun setSharedPreferences(sharedPref: SharedPrefProvider) {
        this.sharedPref = sharedPref
    }

    fun callRegisterApi(name: String?, email: String?, password: String?, phoneNumber: String?, status: String?) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.registerRequest(name, email, password, phoneNumber, status)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    isRegisterLiveData.value = false
                }
            }
            if (response is RegisterResponse) {
                isRegisterLiveData.value = true
                Log.d("android1", "$response")
            }
            isLoadingLiveData.value = false
        }
    }
}