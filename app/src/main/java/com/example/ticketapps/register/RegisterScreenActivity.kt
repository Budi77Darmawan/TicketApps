package com.example.ticketapps.register

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hiringapps.sharedpref.SharedPrefProvider
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityRegisterScreenBinding
import com.example.ticketapps.util.ApiClient


class RegisterScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterScreenBinding
    private lateinit var sharedPref: SharedPrefProvider
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen)
        sharedPref = SharedPrefProvider(this)
        val service = ApiClient.getApiClient(this)?.create(RegisterApiService::class.java)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewModel.setSharedPreferences(sharedPref)

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
                "Activated"
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
                Log.d("android1", "Sukses")
            } else {
                Log.d("android1", "false")
            }
        })
    }
}
