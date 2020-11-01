package com.example.ticketapps.searchResult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivitySearchResultBinding
import com.example.ticketapps.flightDetail.FlightDetailActivity
import com.example.ticketapps.searchFlight.SearchFlightModel
import com.example.ticketapps.util.ApiClient
import com.example.ticketapps.util.sharedpref.SharedPrefProvider

class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var viewModel: SearchResultViewModel
    private lateinit var sharedPref: SharedPrefProvider
    private var filterSearch = SearchFlightModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        sharedPref = SharedPrefProvider(this)

        filterSearch = intent.getParcelableExtra("SearchFilter")!!
        val value = "${filterSearch.value_adult} Adult & ${filterSearch.value_child} Child"

        binding.apply {
            tvCityDeparture.text = filterSearch.city_depature
            tvCountryDeparture.text = filterSearch.country_depature
            tvCityDestination.text = filterSearch.city_destination
            tvCountryDestination.text = filterSearch.country_destination
            tvClassData.text = filterSearch.order_class
            tvPassengerData.text = value
        }

        val service = ApiClient.getApiClient(this)?.create(SearchResultApiService::class.java)
        viewModel = ViewModelProvider(this).get(SearchResultViewModel::class.java)
        if (service != null) {
            viewModel.setSearchResultService(service)
        }

        viewModel.callSearchResultApi(
            filterSearch.code_city_destination.toString(),
            filterSearch.code_city_depature.toString(),
            filterSearch.order_class.toString(),
            "Adult"
        )
        subscribeLiveData()
    }

    private fun showRecyclerList(list: List<TicketsModel>) {
        binding.recycleViewTicket.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val listPlaneAdapter = SearchResultAdapter(list)
        binding.recycleViewTicket.adapter = listPlaneAdapter

        listPlaneAdapter.setOnItemClickCallback(object :
            SearchResultAdapter.OnItemClickCallback {
            override fun onItemClicked(id: Int) {
                val intent = Intent(this@SearchResultActivity, FlightDetailActivity::class.java)
                intent.putExtra("Result", list[id])
                intent.putExtra("FilterSearh", filterSearch)
                startActivity(intent)
            }
        })
    }

    private fun subscribeLiveData() {
        viewModel.listTicketLiveData.observe(this, {
            if (it != null) {
                showRecyclerList(it)
                val ticketsFounds = "${it.lastIndex} Flight Found"
                binding.tvFlightCount.text = ticketsFounds
            }
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