package com.example.ticketapps.mybooking

import com.google.gson.annotations.SerializedName

class OrderResponse(val success: Boolean, val message: String?, val data: List<DataResult>?) {

    data class DataResult(
        @SerializedName("id_order") val id_order: String?,
        @SerializedName("order_name") val order_name: String?,
        @SerializedName("status_payment") val status_payment: String?,
        @SerializedName("price") val price: String?,
        @SerializedName("order_class") val order_class: String?,
        @SerializedName("times_flight") val times_flight : String?,
        @SerializedName("code_depature") val code_depature: String?,
        @SerializedName("city_depature") val city_depature: String?,
        @SerializedName("code_country_depature") val code_country_depature: String?,
        @SerializedName("country_depature") val country_depature: String?,
        @SerializedName("code_destination") val code_destination: String?,
        @SerializedName("city_destination") val city_destination: String?,
        @SerializedName("code_country_destination") val code_country_destination: String?,
        @SerializedName("country_destination") val country_destination: String?,
        @SerializedName("plane_name") val plane_name: String?,
        @SerializedName("plane_image") val plane_image: String?
    )
}