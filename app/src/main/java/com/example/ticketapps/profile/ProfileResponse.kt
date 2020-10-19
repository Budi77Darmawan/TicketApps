package com.example.ticketapps.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(val success: Boolean, val message: String?, val data: DataResult) {

    data class DataResult(
        @SerializedName("id_account") val idAccount: Int?,
        @SerializedName("full_name") val full_name: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("phone_number") val phoneNumber: String?,
        @SerializedName("profile_image") val image: String?,
        @SerializedName("city") val city: String?,
        @SerializedName("address") val address : String?
    )
}