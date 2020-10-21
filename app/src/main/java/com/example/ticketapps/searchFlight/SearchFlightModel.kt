package com.example.ticketapps.searchFlight

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchFlightModel (
    var order_class: String? = "",
    var times_flight : String? = "",
    var code_city_depature: String? = "",
    var city_depature: String? = "",
    var country_depature: String? = "",
    var code_city_destination: String? = "",
    var city_destination: String? = "",
    var country_destination: String? = "",
    var value_adult: String? = "",
    var value_child: String? = ""
) : Parcelable