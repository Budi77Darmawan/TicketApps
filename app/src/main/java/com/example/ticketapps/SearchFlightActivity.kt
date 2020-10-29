package com.example.ticketapps


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ticketapps.databinding.ActivitySearchFlightBinding
import com.example.ticketapps.searchResult.SearchResultActivity

class SearchFlightActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchFlightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_flight)
        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, SearchResultActivity::class.java)
            startActivity(intent)
        }


    }
}