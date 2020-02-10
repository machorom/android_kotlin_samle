package com.machorom.retrofitdemo.data.model

import com.google.gson.annotations.SerializedName

open class BaseResponse (
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("code")
    val code: String? = null
)