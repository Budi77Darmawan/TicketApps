package com.example.ticketapps.util

import android.content.Context
import com.example.hiringapps.sharedpref.Constant
import com.example.hiringapps.sharedpref.SharedPrefProvider
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(val mContext: Context) : Interceptor {

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