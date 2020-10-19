package com.example.ticketapps.searchResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)

        binding.recycleViewTicket.adapter = SearchResultAdapter()
        binding.recycleViewTicket.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}