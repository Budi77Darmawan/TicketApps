package com.example.ticketapps.flighdetail

import com.example.ticketapps.register.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FlightDetailApiService {
    @FormUrlEncoded
    @POST("order")
    suspend fun orderFlightRequest(
        @Field("order_name") order_name: String?,
        @Field("total_price") total_price: Int?,
        @Field("id_plane") id_plane: Int?,
        @Field("passengger") passengger: String?,
        @Field("order_class") order_class: String?,
        @Field("city_depature") depature: String?,
            @Field("city_destination") destination: String?,
        @Field("times_flight") times_flight: String?,
    ) : FlightDetailResponse
}