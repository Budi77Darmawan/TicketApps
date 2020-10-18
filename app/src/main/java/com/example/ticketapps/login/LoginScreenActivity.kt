package com.example.ticketapps.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.MainActivity
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityLoginScreenBinding
import com.example.ticketapps.register.RegisterScreenActivity
import com.example.ticketapps.util.ApiClient
import com.example.ticketapps.util.sharedpref.SharedPrefProvider

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var sharedPref: SharedPrefProvider
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen)
        sharedPref = SharedPrefProvider(this)
        val service = ApiClient.getApiClient(this)?.create(LoginApiService::class.java)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.setSharedPreferences(sharedPref)

        if (service != null) {
            viewModel.setLoginService(service)
        }

        subscribeLiveData()

        binding.btnLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvToReg.setOnClickListener {
            intent = Intent(this, RegisterScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.isLoginLiveData.observe(this, {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}