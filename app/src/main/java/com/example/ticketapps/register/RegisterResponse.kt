package com.example.ticketapps.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(val success: Boolean, val message: String?, val data: DataResult?) {

    data class DataResult(
        @SerializedName("full_name") val name: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("phone_number") val phoneNumber: String?,
        @SerializedName("status") val status: String?
    )
}