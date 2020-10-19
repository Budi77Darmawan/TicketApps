package com.example.ticketapps.searchResult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ItemRvTicketsBinding

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.SearchResultHolder>() {
    
    private val items = mutableListOf<TicketsModel>()

    fun addList(list: List<TicketsModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class SearchResultHolder(val binding: ItemRvTicketsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder {
        return SearchResultHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_rv_tickets, parent, false))
    }

    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
    }

    override fun getItemCount(): Int = items.size
}