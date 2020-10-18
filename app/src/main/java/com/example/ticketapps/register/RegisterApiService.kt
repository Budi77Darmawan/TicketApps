package com.example.ticketapps.register

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterApiService {

    @FormUrlEncoded
    @POST("account/register")
    suspend fun registerRequest(
        @Field("full_name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("phone_number") phoneNumber: String?,
        @Field("status") status: String?
    ) : RegisterResponse
}