package com.example.ticketapps.profile

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileApiService {

    @GET("profile/{id}")
    suspend fun getProfileIdRequest(@Path("id") id: Int
    ) : ProfileResponse
}
