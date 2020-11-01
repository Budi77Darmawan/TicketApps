package com.example.ticketapps.searchFlight

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataFlightModel(
    val codeDepature: String?,
    val cityDepature: String?,
    val codeCountryDepature: String?,
    val countryDepature: String?,
    val codeDestinantion: String?,
    val cityDestinantion: String?,
    val codeCountryDestinantion: String?,
    val countryDestinantion: String?
) : Parcelable