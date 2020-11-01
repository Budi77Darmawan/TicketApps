package com.example.ticketapps.detailBooking

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentDetailBookingBinding
import com.example.ticketapps.mybooking.MyBookingFragment
import com.example.ticketapps.mybooking.OrderModel

class DetailBookingFragment : AppCompatActivity() {
    private lateinit var binding: FragmentDetailBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_detail_booking)
        val orderModel: OrderModel? =
            intent.getParcelableExtra(MyBookingFragment.PUT_EXTRA)

        binding.tvPassenger.text = orderModel?.order_name ?: " "
        binding.tvDepature.text = orderModel?.code_depature ?: " "
        binding.tvDestination.text = orderModel?.code_destination ?: " "
        binding.tvClass.text = orderModel?.order_class ?: ""
        Glide.with(this)
            .load("http://18.212.194.218:9090/uploads/" + orderModel?.plane_image)
            .into(binding.imgLogo)
        binding.tvTimedepature.text = orderModel?.times_flight ?: ""
        binding.progressBar.visibility = View.GONE

        binding.backSpace.setOnClickListener {
            onBackPressed()
        }
    }
}