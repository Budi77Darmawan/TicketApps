package com.example.ticketapps.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.MainActivity
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityLoginScreenBinding
import com.example.ticketapps.register.RegisterScreenActivity
import com.example.ticketapps.util.ApiClient

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen)
        val service = ApiClient.getApiClient(this)?.create(LoginApiService::class.java)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        if (service != null) {
            viewModel.setLoginService(service)
        }

        subscribeLiveData()

        binding.btnLogin.setOnClickListener {

            viewModel.callLoginApi(
                binding.etEmail.text.toString(),
                binding.inputPass.text.toString()
            )
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

        viewModel.isMessageLiveData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.isLoginLiveData.observe(this, {
            if (it) {
                viewModel.putSharedPreferences(this)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}