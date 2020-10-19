package com.example.ticketapps.detailbooking

import com.google.gson.annotations.SerializedName

class DetailBookingResponse(val success: Boolean, val message: String?, val data: DataResult) {

    data class DataResult(
        @SerializedName("id_order") val id_order: Int?,
        @SerializedName("id_account") val id_account: Int?,
        @SerializedName("order_name") val order_name: String?,
        @SerializedName("prince") val prince: Int?,
        @SerializedName("order_class") val order_class: String?,
        @SerializedName("passangger") val passangger: String?,
        @SerializedName("city_destination") val destination: String?,
        @SerializedName("city_depature") val depature: String?,
        @SerializedName("times_flight") val times_flight: String?
    )
}