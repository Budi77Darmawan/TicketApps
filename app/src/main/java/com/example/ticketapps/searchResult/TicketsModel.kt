package com.example.ticketapps.searchResult

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TicketsModel(
    val idPlane: String?,
    val planeImage: String?,
    val price: String?,
    val codeDepature: String?,
    val cityDepature: String?,
    val codeCountryDepature: String?,
    val countryDepature: String?,
    val codeDestinantion: String?,
    val cityDestinantion: String?,
    val codeCountryDestinantion: String?,
    val countryDestinantion: String?
) : Parcelable