package com.example.ticketapps.flightDetail



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ticketapps.MainActivity
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityFlightDetailBinding
import com.example.ticketapps.searchFlight.SearchFlightModel
import com.example.ticketapps.searchResult.TicketsModel
import com.example.ticketapps.util.ApiClient
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider

class FlightDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: FlightDetailViewModel
    private lateinit var binding: ActivityFlightDetailBinding
    private lateinit var sharedPref: SharedPrefProvider
    private var name_account = ""
    private fun getPhotoImage(file: String) : String = "http://18.212.194.218:9090/uploads/$file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight_detail)
        viewModel = ViewModelProvider(this).get(FlightDetailViewModel::class.java)
        val service = ApiClient.getApiClient(this)?.create(FlightDetailApiService::class.java)

        sharedPref = SharedPrefProvider(this)
        name_account = sharedPref.getString(Constant.KEY_NAME).toString()

        val dataFlight = intent.getParcelableExtra<TicketsModel>("Result")
        val dataFilter = intent.getParcelableExtra<SearchFlightModel>("FilterSearh")

        binding.apply {
            tvDepature.text = dataFlight?.codeCountryDepature
            tvDestination.text = dataFlight?.codeCountryDestinantion
            tvClass.text = dataFilter?.order_class
            tvChild.text = dataFilter?.value_child
            tvAdult.text = dataFilter?.value_adult
            Glide.with(this@FlightDetailActivity)
                .load(getPhotoImage(dataFlight?.planeImage.toString()))
                .into(imgLogo)
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        if (service != null) {
            viewModel.setRegisterService(service)
        }

        subscribeLiveData()

        binding.btnBookFlight.setOnClickListener {
            viewModel.callFlightDetailApi(
                name_account,
                25000,
                dataFlight?.idPlane?.toInt(),
                "Adult",
                dataFilter?.order_class.toString(),
                dataFlight?.codeDepature,
                dataFlight?.codeDestinantion,
                dataFilter?.times_flight.toString()
            )

        }
    }

    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(this, {

        })

        viewModel.isMessageLiveData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.isFlightLiveData.observe(this, {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("MyBooking", 1)
                startActivity(intent)
                finish()
            }
        })
    }
}
