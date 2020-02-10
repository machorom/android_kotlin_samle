package com.machorom.retrofitdemo.data.repository

import com.machorom.retrofitdemo.data.datasource.remote.AuthRemoteDataSource
import com.machorom.retrofitdemo.data.model.auth.LoginResponse

class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource ) {
    suspend fun login(userId:String, password:String ): LoginResponse {
        return authRemoteDataSource.login(userId, password)
    }
}