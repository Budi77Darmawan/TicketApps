package com.example.ticketapps.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityRegisterScreenBinding
import com.example.ticketapps.login.LoginScreenActivity
import com.example.ticketapps.util.ApiClient


class RegisterScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterScreenBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen)
        val service = ApiClient.getApiClient(this)?.create(RegisterApiService::class.java)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        if (service != null) {
            viewModel.setRegisterService(service)
        }

        subscribeLiveData()

        binding.btnReg.setOnClickListener {
            viewModel.callRegisterApi(
                binding.etNameReg.text.toString(),
                binding.etEmailReg.text.toString(),
                binding.etPassReg.text.toString(),
                binding.etNoReg.text.toString(),
                "activated"
            )
        }
        binding.btnToLogin.setOnClickListener {
            onBackPressed()
        }
    }
    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isRegisterLiveData.observe(this, {
            if (it) {
                val intent = Intent(this, LoginScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}
