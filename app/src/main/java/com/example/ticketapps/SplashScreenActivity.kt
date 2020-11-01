@file:Suppress("DEPRECATION")

package com.example.ticketapps

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ticketapps.databinding.ActivitySplashScreenBinding
import com.example.ticketapps.login.LoginScreenActivity
import com.example.ticketapps.onBoard.OnBoardActivity
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var sharedPref: SharedPrefProvider
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        sharedPref = SharedPrefProvider(applicationContext)
        val sharedPrefLogin = sharedPref.getBoolean(Constant.KEY_REMEMBERLOGIN)
        val sharedPrefOnBoard = sharedPref.getBoolean(Constant.KEY_ONBOARD)

        val handler = Handler()
        handler.postDelayed({
            i = if (sharedPrefLogin && sharedPrefOnBoard) {
                Intent(this, MainActivity::class.java)
            } else if (!sharedPrefLogin && sharedPrefOnBoard) {
                Intent(this, LoginScreenActivity::class.java)
            } else {
                Intent(this, OnBoardActivity::class.java)
            }
            startActivity(i)
            finish()
        }, 3000)
    }
}