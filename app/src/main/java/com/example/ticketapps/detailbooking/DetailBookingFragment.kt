package com.example.ticketapps.detailbooking

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentDetailBookingBinding
import com.example.ticketapps.util.ApiClient

class DetailBookingFragment : AppCompatActivity() {
    private lateinit var binding: FragmentDetailBookingBinding
    private lateinit var viewModel: DetailBookingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_detail_booking)
        val service = ApiClient.getApiClient(this)?.create(DetailBookingApiService::class.java)
        viewModel = ViewModelProvider(this).get(DetailBookingViewModel::class.java)
        val idOrder = intent.getStringExtra("DetailBookingFragment")!!.toInt()
        if (service != null) {
            viewModel.setDetailBookingService(service)
            viewModel.callDetailBookingApi(idOrder)
            subscribeLiveData()
        }

    }
    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isDetailBookingLiveData.observe(this, {
            if (it) {
                val data = viewModel.listLiveData.value?.firstOrNull()


                binding.tvPassenger.text = data?.order_name?: " "
                binding.tvDepature.text = data?.depature ?: " "
                binding.tvDestination.text = data?.destination ?: " "
                binding.tvClass.text = data?.order_class ?: ""
                binding.tvTimedepature.text = data?.times_flight ?: ""
                binding.backSpace.setOnClickListener {
                   onBackPressed()
                }
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}