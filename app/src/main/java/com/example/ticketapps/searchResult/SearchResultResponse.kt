package com.example.ticketapps.searchResult

import com.google.gson.annotations.SerializedName

data class SearchResultResponse(val success: Boolean, val message: String?, val data: List<DataResult>?) {

    data class DataResult(
        @SerializedName("id_plane") val idPlane: String?,
        @SerializedName("plane_image") val planeImage: String?,
        @SerializedName("price") val price: String?,
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