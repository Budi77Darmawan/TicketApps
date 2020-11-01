package com.example.ticketapps.searchFlight

import com.google.gson.annotations.SerializedName

class DataFlightResponse(val success: Boolean, val message: String?, val data: List<DataResult>?) {

    data class DataResult(
        @SerializedName("code_depature") val codeDepature: String?,
        @SerializedName("city_depature") val cityDepature: String?,
        @SerializedName("code_country_depature") val codeCountryDepature: String?,
        @SerializedName("country_depature") val countryDepature: String?,
        @SerializedName("code_destination") val codeDestinantion: String?,
        @SerializedName("city_destinantion") val cityDestinantion: String?,
        @SerializedName("code_country_destination") val codeCountryDestinantion: String?,
        @SerializedName("country_destination") val countryDestinantion: String?
    )
}