package com.example.ticketapps.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ItemGridDestinationBinding
import java.util.ArrayList

class ExploreGridAdapter(private val items: ArrayList<DestinationModel>):
    RecyclerView.Adapter<ExploreGridAdapter.GridViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): GridViewHolder {
        return GridViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_grid_destination,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tvCity.text = item.city_name
        holder.binding.tvCountry.text = item.country_name
        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(holder.binding.imgDestination)
    }

    override fun getItemCount(): Int = items.size

    inner class GridViewHolder(val binding: ItemGridDestinationBinding) :
        RecyclerView.ViewHolder(binding.root)

}