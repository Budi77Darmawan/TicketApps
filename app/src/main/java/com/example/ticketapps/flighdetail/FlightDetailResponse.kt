package com.example.ticketapps.flighdetail

import com.google.gson.annotations.SerializedName

class FlightDetailResponse (val success: Boolean, val message: String?, val data: DataResult?) {

    data class DataResult(
        @SerializedName("id_account") val id_account: String?,
        @SerializedName("id_price") val id_price: String?,
        @SerializedName("order_name") val order_name: String?,
        @SerializedName("created_at") val created: String?,
        @SerializedName("updated_at") val updated: String?
    )
}