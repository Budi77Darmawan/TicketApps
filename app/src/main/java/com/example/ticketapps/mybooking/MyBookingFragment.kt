package com.example.ticketapps.mybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentMyBookingBinding
import com.example.ticketapps.util.ApiClient

class MyBookingFragment : Fragment() {
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
//                val intent = Intent(activity, DetailProjectActivity::class.java)
//                intent.putExtra(DetailProjectActivity.EXTRA_DATA, list[id])
//                startActivity(intent)
            }
        })
    }

    private fun subscribeLiveData() {
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