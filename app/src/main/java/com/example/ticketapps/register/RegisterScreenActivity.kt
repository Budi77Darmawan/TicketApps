package com.example.ticketapps.register

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityRegisterScreenBinding


class RegisterScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen)
        binding.btnReg.setOnClickListener {

        }
        binding.btnToLogin.setOnClickListener {
            onBackPressed()

        }


    }




    }
