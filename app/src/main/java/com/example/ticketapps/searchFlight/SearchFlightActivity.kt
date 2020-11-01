package com.example.ticketapps.searchFlight


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivitySearchFlightBinding
import com.example.ticketapps.searchResult.SearchResultActivity
import com.example.ticketapps.searchResult.SearchResultApiService
import com.example.ticketapps.searchResult.SearchResultViewModel
import com.example.ticketapps.util.ApiClient
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_searchflight.view.*
import java.text.SimpleDateFormat
import java.util.*

class SearchFlightActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySearchFlightBinding
    private lateinit var bottomSheetProject: BottomSheetDialog
    private lateinit var viewModel: SearchFlightViewModel
    private var filterSearch = SearchFlightModel()
    private var codeCityDeparture: String = "CGK"
    private var cityDeparture: String = "Jakarta"
    private var countryDeparture: String = "Indonesia"
    private var codeCityDestination: String = "JFK"
    private var cityDestination: String = "New York"
    private var countryDestination: String = "United States"
    private var planeClass: String = "Economy"
    private var valueAdult: String = "1"
    private var valueChild: String = "1"
    private var years = 0
    private var months = 0
    private var days = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_flight)

        val c = Calendar.getInstance()
        years = c.get(Calendar.YEAR)
        months = c.get(Calendar.MONTH)
        days = c.get(Calendar.DAY_OF_MONTH)
        c[years, months] = days
        val format = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
        val dateString = format.format(c.time)
        binding.tvDate.text = dateString

        bottomSheetProject = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottomsheet_searchflight, null)
        bottomSheetProject.setContentView(view)

        val service = ApiClient.getApiClient(this)?.create(AirportApiService::class.java)
        viewModel = ViewModelProvider(this).get(SearchFlightViewModel::class.java)
        if (service != null) {
            viewModel.setSearchFlightService(service)
        }
        subscribeLiveData()

        view.number_adult.maxValue = 10
        view.number_adult.minValue = 1
        view.number_child.maxValue = 10
        view.number_child.minValue = 1

        binding.tvCitydepature.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
        binding.tvDate.setOnClickListener(this)
        binding.tvAdult.setOnClickListener(this)
        binding.tvChild.setOnClickListener(this)

        view.number_adult.setOnValueChangedListener { _, _, _ ->
            valueAdult = view.number_adult.value.toString()
            binding.tvAdult.text = "$valueAdult Adult"
        }
        view.number_child.setOnValueChangedListener { _, _, _ ->
            valueChild = view.number_child.value.toString()
            binding.tvChild.text = "$valueChild Child"
        }

        binding.rgClass.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.tv_economy -> {
                    planeClass = "Economy"
                }
                R.id.tv_business -> {
                    planeClass = "Business"
                }
                R.id.tv_firstclass -> {
                    planeClass = "First Class"
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_search -> {
                filterSearch = SearchFlightModel(
                    planeClass,
                    "Pagi",
                    codeCityDeparture,
                    cityDeparture,
                    countryDeparture,
                    codeCityDestination,
                    cityDestination,
                    countryDestination,
                    valueAdult,
                    valueChild
                )
                val i = Intent(this, SearchResultActivity::class.java)
                i.putExtra("SearchFilter", filterSearch)
                startActivity(i)
            }
            R.id.btn_back -> {
                onBackPressed()
            }
            R.id.tv_date -> {
                val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    years = year
                    months = monthOfYear
                    days = dayOfMonth
                    calendar[year, monthOfYear] = dayOfMonth
                    val format = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
                    val dateString = format.format(calendar.time)
                    binding.tvDate.text = dateString
                }, years, months, days)
                dpd.show()
            }
            R.id.tv_adult -> {
                bottomSheetProject.show()
            }
            R.id.tv_child -> {
                bottomSheetProject.show()
            }
            R.id.tv_citydepature -> {


            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun subscribeLiveData() {
        viewModel.getDataFlightApi(
            codeCityDeparture,
            codeCityDestination
        )

        viewModel.dataFlightLiveData.observe(this, {
            cityDeparture = it.cityDepature.toString()
            cityDestination = it.cityDestinantion.toString()
            codeCityDeparture = it.codeDepature.toString()
            codeCityDestination = it.codeDestinantion.toString()
            countryDeparture = it.countryDepature.toString()
            countryDestination = it.countryDestinantion.toString()
        })
    }
}