package com.example.ticketapps

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ticketapps.databinding.ActivityMainBinding
import com.example.ticketapps.explore.ExploreFragment
import com.example.ticketapps.mybooking.MyBookingFragment
import com.example.ticketapps.profile.ProfileFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val intent = intent.getIntExtra("MyBooking", 0)
        if (intent == 1) {
            loadFragment(MyBookingFragment())
        } else {
            loadFragment(ExploreFragment())
        }
        
        binding.let {
            it.mybooking.setOnClickListener(this)
            it.explore.setOnClickListener(this)
            it.profile.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mybooking -> {
                binding.mybooking.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_calenderactive))
                binding.tvMybooking.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                binding.profile.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_user))
                binding.tvProfile.setTextColor(Color.parseColor("#979797"))
                loadFragment(MyBookingFragment())
            }
            R.id.explore -> {
                binding.mybooking.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_calender))
                binding.tvMybooking.setTextColor(Color.parseColor("#979797"))
                binding.profile.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_user))
                binding.tvProfile.setTextColor(Color.parseColor("#979797"))
                loadFragment(ExploreFragment())
            }
            R.id.profile -> {
                binding.mybooking.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_calender))
                binding.tvMybooking.setTextColor(Color.parseColor("#979797"))
                binding.profile.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_useractive))
                binding.tvProfile.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                loadFragment(ProfileFragment())
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}