package com.example.ticketapps.searchFlight

import com.google.gson.annotations.SerializedName

class ListAirportResponse(val success: Boolean, val message: String?, val data: List<DataResult>?) {

    data class DataResult(
        @SerializedName("code_city") val codeCity: String?,
        @SerializedName("city_name") val cityName: String?,
        @SerializedName("code_country") val codeCountry: String?,
        @SerializedName("country_name") val countryName: String?
    )
}