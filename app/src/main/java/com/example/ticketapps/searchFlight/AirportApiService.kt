package com.example.ticketapps.searchFlight

import retrofit2.http.GET
import retrofit2.http.Query

interface AirportApiService {

    @GET("airport")
    suspend fun getFlight(): ListAirportResponse

    @GET("airport/data")
    suspend fun getDataFlight(
        @Query("city_depature") cityDeparture: String,
        @Query("city_destination") cityDestination: String
    ): DataFlightResponse
}