package com.machorom.retrofitdemo.api

import com.machorom.retrofitdemo.data.model.auth.LoginConfigResponse
import com.machorom.retrofitdemo.data.model.auth.LoginResponse
import com.machorom.retrofitdemo.data.model.auth.SessionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Content-Type: application/json")
    @GET("api/login/config")
    suspend fun loginConfig(
    ): LoginConfigResponse

    @Headers("Content-Type: application/json")
    @POST("api/login")
    suspend fun login(
        @Body params: HashMap<String, String>
    ): LoginResponse


    @Headers("Content-Type: application/json")
    @GET("api/user/session")
    suspend fun userSession(
    ): SessionResponse
}