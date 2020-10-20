package com.example.ticketapps.searchResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivitySearchResultBinding
import com.example.ticketapps.util.ApiClient
import com.example.ticketapps.util.sharedpref.SharedPrefProvider

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var viewModel: SearchResultViewModel
    private lateinit var sharedPref: SharedPrefProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        sharedPref = SharedPrefProvider(this)

        val service = ApiClient.getApiClient(this)?.create(SearchResultApiService::class.java)
        viewModel = ViewModelProvider(this).get(SearchResultViewModel::class.java)
        if (service != null) {
            viewModel.setSearchResultService(service)
        }

        binding.recycleViewTicket.adapter = SearchResultAdapter()
        binding.recycleViewTicket.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        viewModel.callSearchResultApi()
        subscribeLiveData()
    }

    private fun subscribeLiveData() {

        viewModel.listTicketLiveData.observe(this, {
            (binding.recycleViewTicket.adapter as SearchResultAdapter).addList(it)
            val ticketsFounds = "${it.lastIndex} Flight Found"
            binding.tvFlightCount.text = ticketsFounds
        })
        viewModel.isLoadingLiveData.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.countTicketsLiveData.observe(this, {
            val ticketsFounds = "$it Flight Found"
            binding.tvFlightCount.text = ticketsFounds
        })
    }
}