package com.example.ticketapps



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.databinding.ActivityFlightDetailBinding

class FlightDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: FlightDetailViewModel
    private lateinit var binding: ActivityFlightDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight_detail)
        viewModel = ViewModelProvider(this).get(FlightDetailViewModel::class.java)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}