package com.example.ticketapps.mybooking

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketapps.R
import com.example.ticketapps.databinding.ItemListMybookingBinding
import com.example.ticketapps.detailbooking.DetailBookingFragment

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

//            if (item.status_payment.toString() != "paid")
//            {
//                Toast.makeText(content, "Waiting for Payment", Toast.LENGTH_SHORT).show()
//            }else{
//                val intent = Intent(content, DetailBookingFragment::class.java)
//                intent.putExtra("DetailBookingFragment", item.id_order)
//                content.startActivity(intent)
//            }
        }
    }

    inner class ListViewHolder(val binding: ItemListMybookingBinding) :
        RecyclerView.ViewHolder(binding.root)

}