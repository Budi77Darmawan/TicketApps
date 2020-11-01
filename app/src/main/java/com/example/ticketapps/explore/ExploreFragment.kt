package com.example.ticketapps.explore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketapps.R
import com.example.ticketapps.databinding.FragmentExploreBinding
import com.example.ticketapps.searchFlight.SearchFlightActivity
import java.util.ArrayList

class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private val listDestination = ArrayList<DestinationModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)

        binding.searchView.queryHint = "Where you want to go?"

        binding.searchView.setOnClickListener {
            val i = Intent(requireContext(), SearchFlightActivity::class.java)
            startActivity(i)
        }

        listDestination.addAll(DestinationData.listData)
        showRecyclerGrid()
        val listTopDestination = DestinationData.listData.asReversed()
        showRecyclerList(listTopDestination)

        return binding.root
    }

    private fun showRecyclerGrid() {
        binding.rvTrending.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val gridDestinationAdapter = ExploreGridAdapter(listDestination)
        binding.rvTrending.adapter = gridDestinationAdapter
    }

    private fun showRecyclerList(list: MutableList<DestinationModel>) {
        val array = ArrayList(list)
        binding.rvTopdestination.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val topDestinationAdapter = TopDestinationAdapter(array)
        binding.rvTopdestination.adapter = topDestinationAdapter
    }
}