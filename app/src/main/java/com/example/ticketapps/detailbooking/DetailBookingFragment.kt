package com.example.ticketapps.detailbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentDetailBookingBinding
import com.example.ticketapps.util.ApiClient
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider

class DetailBookingFragment : Fragment() {
    private lateinit var binding: FragmentDetailBookingBinding
    private lateinit var sharedPref: SharedPrefProvider
    private lateinit var viewModel: DetailBookingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail_booking, container, false)
        sharedPref = SharedPrefProvider(requireContext())
        val service = ApiClient.getApiClient(this.requireContext())?.create(DetailBookingApiService::class.java)
        viewModel = ViewModelProvider(this).get(DetailBookingViewModel::class.java)
        viewModel.getSharedPreferences(requireContext())
        val idOrder = sharedPref.getString(Constant.KEY_ID_ORDER)?: ""
        if (service != null) {
            viewModel.setDetailBookingService(service)
            viewModel.callDetailBookingApi("10")
            subscribeLiveData()
        }


        return binding.root
    }
    private fun subscribeLiveData() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.isDetailBookingLiveData.observe(viewLifecycleOwner, {
            if (it) {
                val data = viewModel.listLiveData.value?.firstOrNull()


                binding.tvPassenger.text = data?.order_name?: " "
                binding.tvDepature.text = data?.depature ?: " "
                binding.tvDestination.text = data?.destination ?: " "
                binding.tvClass.text = data?.order_class ?: ""
                binding.backSpace.setOnClickListener {
                    activity?.onBackPressed()
                }
            } else {
                Toast.makeText(this.requireContext(), "error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}