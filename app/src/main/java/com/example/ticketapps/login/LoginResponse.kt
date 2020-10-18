package com.example.ticketapps.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(val success: Boolean, val message: String?, val data: DataResult) {

    data class DataResult(
        @SerializedName("id_account") val idAccount: Int?,
        @SerializedName("full_name") val name: String?,
        val email: String?,
        @SerializedName("phone_number") val phoneNumber: String?,
        val status: String?,
        val token: String?
    )
}