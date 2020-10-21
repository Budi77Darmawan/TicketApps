package com.example.ticketapps.searchFlight

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class AirportModel(
    val codeCity: String?,
    val cityName: String?,
    val codeCountry: String?,
    val countryName: String?
) : Parcelable