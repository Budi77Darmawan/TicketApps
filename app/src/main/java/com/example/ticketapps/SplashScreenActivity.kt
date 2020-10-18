@file:Suppress("DEPRECATION")

package com.example.ticketapps

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ticketapps.onboard.OnBoardActivity
import com.example.ticketapps.databinding.ActivitySplashScreenBinding


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        var handler = Handler()
        handler.postDelayed({
            intent = Intent(this, OnBoardActivity::class.java )
            startActivity(intent)
            finish()
        }, 3000)//delaying 3 seconds to open login activity
    }


}