package com.example.ticketapps.detailbooking

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentDetailBookingBinding
import com.example.ticketapps.mybooking.MyBookingFragment
import com.example.ticketapps.mybooking.OrderModel
import com.example.ticketapps.util.ApiClient

class DetailBookingFragment : AppCompatActivity() {
    private lateinit var binding: FragmentDetailBookingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_detail_booking)
            val orderModel: OrderModel? = intent.getParcelableExtra<OrderModel>(MyBookingFragment.PUT_EXTRA)



                binding.tvPassenger.text = orderModel?.order_name?: " "
                binding.tvDepature.text = orderModel?.code_depature ?: " "
                binding.tvDestination.text = orderModel?.code_destination ?: " "
                binding.tvClass.text = orderModel?.order_class ?: ""
                Glide.with(this).load("http://3.84.47.133:9090/uploads/" + orderModel?.plane_image).into(binding.imgLogo)
                binding.tvTimedepature.text = orderModel?.times_flight ?: ""
                binding.progressBar.visibility = View.GONE
                binding.backSpace.setOnClickListener {
                   onBackPressed()
                }


    }

}