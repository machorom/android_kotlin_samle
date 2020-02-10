package com.machorom.retrofitdemo.data.repository

import com.machorom.retrofitdemo.data.datasource.remote.AuthRemoteDataSource

class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource ) {
    suspend fun login(userId:String, password:String ){
        authRemoteDataSource.login(userId, password)
    }
}