package com.example.ticketapps.flighdetail



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.MainActivity
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ActivityFlightDetailBinding
import com.example.ticketapps.databinding.FragmentMyBookingBinding
import com.example.ticketapps.login.LoginScreenActivity
import com.example.ticketapps.mybooking.MyBookingFragment
import com.example.ticketapps.register.RegisterApiService
import com.example.ticketapps.util.ApiClient

class FlightDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: FlightDetailViewModel
    private lateinit var binding: ActivityFlightDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight_detail)
        viewModel = ViewModelProvider(this).get(FlightDetailViewModel::class.java)
        val service = ApiClient.getApiClient(this)?.create(FlightDetailApiService::class.java)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        if (service != null) {
            viewModel.setRegisterService(service)
        }

        subscribeLiveData()

        binding.btnBookFlight.setOnClickListener {
            viewModel.callFlightDetailApi(
                "Andi",
                25000,
                3,
                "Adult",
                "Economy",
                "CGK",
                "JFK",
                "pagi"
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
                val intent = Intent(this, MyBookingFragment::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}
