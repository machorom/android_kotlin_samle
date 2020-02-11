package com.machorom.retrofitdemo.data.repository

import com.machorom.retrofitdemo.data.datasource.remote.AuthRemoteDataSource
import com.machorom.retrofitdemo.data.model.auth.LoginConfigResponse
import com.machorom.retrofitdemo.data.model.auth.LoginResponse
import com.machorom.retrofitdemo.data.model.auth.SessionResponse

class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource ) {
    suspend fun login(userId:String, password:String ): LoginResponse {
        return authRemoteDataSource.login(userId, password)
    }

    suspend fun loginConfig():LoginConfigResponse{
        return authRemoteDataSource.loginConfig()
    }

    suspend fun userSession():SessionResponse{
        return authRemoteDataSource.userSession()
    }
}