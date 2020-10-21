package com.example.ticketapps.mybooking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ItemListMybookingBinding

class ListOrderAdapter(private val items: List<OrderModel>) :
    RecyclerView.Adapter<ListOrderAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        return ListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_mybooking,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tvDepature.text = item.code_depature
        holder.binding.tvDestination.text = item.code_destination
        holder.binding.tvNamePlane.text = item.plane_name

        if (item.status_payment.toString() == "paid") {
            holder.binding.tvStatus.text = "Ticket issued"
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_status1)
        } else {
            holder.binding.tvStatus.text = "Waiting for Payment"
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_status0)
        }

        holder.binding.layout.setOnClickListener {
           onItemClickCallback.onItemClicked(position)
        }
    }

    inner class ListViewHolder(val binding: ItemListMybookingBinding) :
        RecyclerView.ViewHolder(binding.root)

}