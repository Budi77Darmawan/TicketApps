package com.example.ticketapps.searchResult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ItemRvTicketsBinding

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.SearchResultHolder>() {
    
    private val items = mutableListOf<TicketsModel>()
    private fun getPhotoImage(file: String) : String = "http://18.207.226.63:9090/uploads/$file"

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
        val item = items[position]
        val priceFormat = "$ ${item.ticketPrice}"
        val getTimeDifference = difference(setTimeFormat(item.timeDestination!!), setTimeFormat(item.timeDeparture!!))
        val timeDifference = "${getTimeDifference.hours} hours ${getTimeDifference.minutes} minutes"

        Glide.with(holder.binding.root)
            .load(getPhotoImage(item.planeImage!!))
            .into(holder.binding.airlinesIcon)
        holder.binding.tvCityDeparture.text = item.codeCityDeparture
        holder.binding.tvTimeDeparture.text = item.timeDeparture
        holder.binding.tvCityDestination.text = item.codeCityDestination
        holder.binding.tvTimeDestination.text = item.timeDestination
        holder.binding.tvPrice.text = priceFormat
        holder.binding.tvTimeDetail.text = timeDifference
    }

    override fun getItemCount(): Int = items.size

    data class Time(internal var hours: Int, internal var minutes: Int, internal var seconds: Int)

    fun setTimeFormat(time: String) : Time {
        val format = time.split(":").map { it.toInt() }
        return Time(format[0], format[1], format[2])
    }

    fun difference(timeDestination: Time, timeDeparture: Time) : Time {
        val diff = Time(0, 0, 0)

        if (timeDeparture.seconds > timeDestination.seconds) {
            --timeDestination.minutes
            timeDestination.seconds += 60
        }

        diff.seconds = timeDestination.seconds - timeDeparture.seconds
        if (timeDeparture.minutes > timeDestination.minutes) {
            --timeDestination.hours
            timeDestination.minutes += 60
        }

        diff.minutes = timeDestination.minutes - timeDeparture.minutes
        diff.hours = timeDestination.hours - timeDeparture.hours

        return diff

    }
}