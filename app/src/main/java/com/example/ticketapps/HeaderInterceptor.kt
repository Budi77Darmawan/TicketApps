package com.example.ticketapps

import android.content.Context
import com.example.hiringapps.sharedpref.Constant
import com.example.hiringapps.sharedpref.SharedPrefProvider
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
