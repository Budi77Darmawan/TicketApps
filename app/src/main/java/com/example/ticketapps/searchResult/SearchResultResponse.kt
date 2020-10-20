package com.example.ticketapps.searchResult

import com.google.gson.annotations.SerializedName

data class SearchResultResponse(val success: Boolean, val message: String?, val data: List<DataResult>?) {

    data class DataResult(
        @SerializedName("plane_image") val planeImage: String?,
        @SerializedName("city_departure") val cityDeparture: String?,
        @SerializedName("city_destination") val cityDestination: String?,
        @SerializedName("time_departure") val timeDeparture: String?,
        @SerializedName("time_destination") val timeDestination: String?,
        val price: Int?
    )
}