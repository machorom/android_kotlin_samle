package com.machorom.retrofitdemo.data.model.auth

import com.google.gson.annotations.SerializedName
import com.machorom.retrofitdemo.data.model.BaseResponse

data class LoginResponse (
    @SerializedName("data")
    val data: LoginData
): BaseResponse()

data class LoginData(
    @SerializedName("redirect")
    val redirect: String? = null,
    @SerializedName("xmppDomain")
    val xmppDomain: String? = null,
    @SerializedName("useSkipPwdChange")
    val useSkipPwdChange: String? = null
)