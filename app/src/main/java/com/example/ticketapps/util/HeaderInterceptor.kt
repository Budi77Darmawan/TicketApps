package com.example.ticketapps.util

import android.content.Context
import com.example.ticketapps.util.sharedpref.Constant
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(mContext: Context) : Interceptor {

    private val sharedPref = SharedPrefProvider(mContext)

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        val token = sharedPref.getString(Constant.KEY_TOKEN)
        proceed(
            request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}