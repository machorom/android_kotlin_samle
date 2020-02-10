package com.machorom.retrofitdemo.data.datasource.remote

import com.machorom.retrofitdemo.api.AuthApi
import com.machorom.retrofitdemo.data.model.auth.LoginResponse

class AuthRemoteDataSource(private val authApi: AuthApi){
    suspend fun login(username:String, password:String):LoginResponse{
        val body = HashMap<String, String>()
        body.put("username","checker")
        body.put("password","1qaz2wsx#")
        body.put("locale","ko")
        return authApi.login(body)
    }
}