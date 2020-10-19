package com.example.ticketapps.mybooking

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderModel (
    var id_order: String? = "",
    var order_name: String? = "",
    var status_payment: String? = "",
    var price: String? = "",
    var order_class: String? = "",
    var time_flight : String? = "",
    var code_depature: String? = "",
    var city_depature: String? = "",
    var code_country_depature: String? = "",
    var country_depature: String? = "",
    var code_destination: String? = "",
    var city_destination: String? = "",
    var code_country_destination: String? = "",
    var country_destination: String? = "",
    var plane_name: String? = "",
    var plane_image: String? = ""
) : Parcelable