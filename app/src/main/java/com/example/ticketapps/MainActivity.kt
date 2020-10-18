package com.example.ticketapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = SharedPrefProvider(this)
        val token = sharedPref.getString(Constant.KEY_TOKEN)
        val id = sharedPref.getString(Constant.KEY_ID_ACCOUNT)
        Log.d("ANDROID!", token.toString())
        Log.d("ANDROID!", id.toString())

        loadFragment(DetailBookingFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}