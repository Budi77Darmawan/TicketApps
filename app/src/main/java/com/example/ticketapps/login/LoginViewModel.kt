package com.example.ticketapps.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoginLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    private lateinit var service: LoginApiService
    private lateinit var sharedPref: SharedPrefProvider

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setLoginService(service: LoginApiService) {
        this.service = service
    }

    fun setSharedPreferences(sharedPref: SharedPrefProvider) {
        this.sharedPref = sharedPref
    }

    fun callLoginApi(email: String?, password: String?) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.loginRequest(email, password)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    isLoginLiveData.value = false
                }
            }
            if (response is LoginResponse) {
                isLoginLiveData.value = true
            }
            isLoadingLiveData.value = false
        }
    }
}