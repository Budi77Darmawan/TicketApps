package com.example.ticketapps.mybooking

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentMyBookingBinding
import com.example.ticketapps.util.ApiClient

class MyBookingFragment : Fragment() {

    companion object {
        const val PUT_EXTRA = "0000"
    }

    private lateinit var binding: FragmentMyBookingBinding
    private lateinit var viewModel: MyBookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_booking, container, false)

        val service =
            ApiClient.getApiClient(this.requireContext())?.create(OrderApiService::class.java)
        viewModel = ViewModelProvider(this).get(MyBookingViewModel::class.java)
        if (service != null) {
            viewModel.setOrderService(service)
        }
        viewModel.getOrderApi()
        subscribeLiveData()

        return binding.root
    }

    private fun showRecyclerList(list: List<OrderModel>) {
        binding.rvMybooking.layoutManager = LinearLayoutManager(requireContext())
        val listOrderAdapter = ListOrderAdapter(list)
        binding.rvMybooking.adapter = listOrderAdapter


        listOrderAdapter.setOnItemClickCallback(object :
            ListOrderAdapter.OnItemClickCallback {
            override fun onItemClicked(id: Int) {
                if (list[id].status_payment == "paid") {
                    val intent = Intent(context, DetailBookingFragment::class.java)
                    intent.putExtra(PUT_EXTRA, list[id])

                    context?.startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Waiting for payment!", Toast.LENGTH_SHORT).show()
                }


            }
        })
    }

    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.listLiveData.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                binding.imageView.visibility = View.VISIBLE
                binding.desc.visibility = View.VISIBLE
            } else {
                binding.imageView.visibility = View.GONE
                binding.desc.visibility = View.GONE
                showRecyclerList(it)
            }
        })
    }
}