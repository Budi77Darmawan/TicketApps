package com.example.ticketapps

import com.example.ticketapps.mybooking.OrderResponse
import retrofit2.http.GET

interface OrderApiService {

    @GET("order/detail/")
    suspend fun getOrderRequest(): OrderResponse
}