package com.example.ticketapps.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ItemListTopdestinationBinding
import java.util.ArrayList

class TopDestinationAdapter(private val items: ArrayList<DestinationModel>):
    RecyclerView.Adapter<TopDestinationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        return ListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_topdestination,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tvCity.text = item.city_name
        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(holder.binding.image)
    }

    override fun getItemCount(): Int = items.size

    inner class ListViewHolder(val binding: ItemListTopdestinationBinding) :
        RecyclerView.ViewHolder(binding.root)

}