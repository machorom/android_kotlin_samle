package com.machorom.retrofitdemo.api.httpextra

import android.content.Context
import android.os.Build
import com.machorom.retrofitdemo.DemoApplication
import com.machorom.retrofitdemo.common.AuthPreference
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

object HttpExtra {
    const val KEY_GO_AGENT = "GO-Agent"

    fun defineHeader(chain: Interceptor.Chain, context: Context): Response {
        val authPreference = AuthPreference(context)
        val requestBuilder = chain.request().newBuilder()
            .addHeader("Charset", "UTF-8")
            .addHeader("Content-Type", "application/json")
            .addHeader(KEY_GO_AGENT, "Android ${Build.VERSION.RELEASE}")
            .addHeader("User-Agent", "GO-Android/")
         if( authPreference.appKey != null ) {
             requestBuilder.addHeader("Cookie", authPreference.appKey)
         }
        val request = requestBuilder.build()
        val originalResponse = chain.proceed(request)
        if ( originalResponse.header("set-cookie") != null ){
            authPreference.appKey = originalResponse.header("set-cookie")!!
        }
        return originalResponse
    }

}