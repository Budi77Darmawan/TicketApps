package com.example.ticketapps.profile

import com.example.ticketapps.profile.edit.EditResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ProfileApiService {

    @GET("profile/{id}")
    suspend fun getProfileIdRequest(@Path("id") id: Int
    ) : ProfileResponse

    @Multipart
    @PATCH("profile")
    suspend fun patchProfile(@Part("city") city: RequestBody,
                     @Part("address") address: RequestBody,
                     @Part("image") image: MultipartBody.Part) : EditResponse
}
