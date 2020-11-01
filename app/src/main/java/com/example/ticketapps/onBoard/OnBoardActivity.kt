package com.example.ticketapps.onBoard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityOnBoardBinding
import com.example.ticketapps.login.LoginScreenActivity
import com.example.ticketapps.register.RegisterScreenActivity
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_on_board.*

class OnBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardBinding
    private lateinit var sharedPref: SharedPrefProvider

    private val mResources = intArrayOf(
        R.drawable.ic_illustration,
        R.drawable.ic_illustration,
        R.drawable.ic_illustration
    )
    lateinit var adapter: SlidingPagerAdapter
    var currentTab = 0
    private var tabCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_on_board)

        sharedPref = SharedPrefProvider(applicationContext)

        binding.apply {
            login.setOnClickListener {
                sharedPref.putBoolean(Constant.KEY_ONBOARD, true)
                val intentLogin = Intent(this@OnBoardActivity, LoginScreenActivity::class.java)
                startActivity(intentLogin)
                finish()
            }
            register.setOnClickListener {
                sharedPref.putBoolean(Constant.KEY_ONBOARD, true)
                val intentRegister = Intent(this@OnBoardActivity, RegisterScreenActivity::class.java)
                startActivity(intentRegister)
                finish()
            }
        }

        tabCount = mResources.size
        adapter = SlidingPagerAdapter(supportFragmentManager, mResources)
        viewPager.adapter = adapter
        val pageTransformer = ParallaxTransformer()
        viewPager.setPageTransformer(true, pageTransformer)

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

        })
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
            }

        })

    }
}


@Suppress("DEPRECATION")
class SlidingPagerAdapter(fragmentManager: FragmentManager?, private val mResources: IntArray) :
    FragmentPagerAdapter(
        fragmentManager!!
    ) {

    override fun getItem(position: Int): Fragment {
        return IntroPage().newInstance(position)
    }

    override fun getCount(): Int {
        return mResources.size
    }
}