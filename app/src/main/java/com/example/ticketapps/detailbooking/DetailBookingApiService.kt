package com.example.ticketapps.detailbooking

import com.example.ticketapps.profile.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailBookingApiService {

    @GET("order/detail/{id}")
    suspend fun getDetailBookingID(@Path("id") id: Int
    ) : DetailBookingResponse

}