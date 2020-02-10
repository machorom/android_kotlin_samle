package com.daou.daouoffice

import com.google.gson.annotations.SerializedName

data class LoginConfigResponse (
    @SerializedName("data")
    val data: LoginConfigData
) : BaseResponse()

data class LoginConfigData(
    @SerializedName("protocol")
    val protocol: String? = null,
    @SerializedName("scope")
    val scope: String? = null,
    @SerializedName("useMultiSiteSelect")
    val useMultiSiteSelect: Boolean? = null,
    @SerializedName("useMultiSiteChat")
    val useMultiSiteChat: Boolean? = null,
    @SerializedName("useOutSideLogin")
    val useOutSideLogin: Boolean? = null,
    @SerializedName("outSideLoginUrl")
    val outSideLoginUrl: String? = null,
    @SerializedName("service")
    val service: Boolean? = null
)