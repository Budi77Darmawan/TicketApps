package com.example.ticketapps.searchResult

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchResultApiService {

    @GET("order/ticket")
    suspend fun getTicketRequest(
        @Query("city_destination") cityDestination: String,
        @Query("city_departure") cityDeparture: String,
        @Query("order_class") orderClass: String,
        @Query("passengger") passenger: String
    ): SearchResultResponse
}