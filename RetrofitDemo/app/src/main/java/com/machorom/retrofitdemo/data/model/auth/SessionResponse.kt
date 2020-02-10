package com.machorom.retrofitdemo.data.model.auth

import com.google.gson.annotations.SerializedName
import com.machorom.retrofitdemo.data.model.BaseResponse

data class SessionResponse (
    @SerializedName("data")
    val data: SessionData
): BaseResponse()

data class SessionData(
    @SerializedName("brandName")
    val brandName: String? = null,
    @SerializedName("timeZone")
    val timeZone: TimeZoneData,
    @SerializedName("surveyAdmin")
    val surveyAdmin: String? = null,
    @SerializedName("adminPageBase")
    val adminPageBase: Int? = null,
    @SerializedName("repId")
    val repId: Int? = null,
    @SerializedName("companyGroupId")
    val companyGroupId: Int? = null,
    @SerializedName("mailAccountStatus")
    val mailAccountStatus: String? = null,
    @SerializedName("companies")
    val companies: List<CompanyData>,
    @SerializedName("otpDevice")
    val otpDevice: Boolean? = null,
    @SerializedName("dashboardAdmin")
    val dashboardAdmin: Boolean? = null,
    @SerializedName("serverTZOffset")
    val serverTZOffset: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("theme")
    val theme: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("companyId")
    val companyId: Int? = null,
    @SerializedName("useWebfolderAccess")
    val useWebfolderAccess: Boolean? = null,
    @SerializedName("useMailAccess")
    val useMailAccess: Boolean? = null,
    @SerializedName("loginId")
    val loginId: String? = null,
    @SerializedName("lastLoginedAt")
    val lastLoginedAt: String? = null,
    @SerializedName("siteAdmin")
    val siteAdmin: Boolean? = null,
    @SerializedName("domainName")
    val domainName: String? = null,
    @SerializedName("serviceAdminMode")
    val serviceAdminMode: Boolean? = null,
    @SerializedName("companyName")
    val companyName: String? = null,
    @SerializedName("employeeNumber")
    val employeeNumber: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("useOrg")
    val useOrg: Boolean? = null,
    @SerializedName("useOrgAccess")
    val useOrgAccess: Boolean? = null,
    @SerializedName("useChat")
    val useChat: Boolean? = null,
    @SerializedName("useContactAccess")
    val useContactAccess: Boolean? = null,
    @SerializedName("accountStatusDormant")
    val accountStatusDormant: Boolean? = null,
    @SerializedName("name")
    val name: String? = null
)

data class TimeZoneData(
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("gmt")
    val gmt: String? = null,
    @SerializedName("offset")
    val offset: String? = null,
    @SerializedName("displayName")
    val displayName: String? = null,
    @SerializedName("serverTZOffset")
    val serverTZOffset: String? = null
)

data class CompanyData(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("companyName")
    val companyName: String? = null,
    @SerializedName("siteUrl")
    val siteUrl: String? = null,
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("userLoginId")
    val userLoginId: String? = null,
    @SerializedName("userUseOrg")
    val userUseOrg: Boolean? = null,
    @SerializedName("userUseOrgAccess")
    val userUseOrgAccess: Boolean? = null,
    @SerializedName("userUseChat")
    val userUseChat: Boolean? = null,
    @SerializedName("userUseMailAccess")
    val userUseMailAccess: Boolean? = null,
    @SerializedName("userUseWebfolderAccess")
    val userUseWebfolderAccess: Boolean? = null,
    @SerializedName("userMobileAccessible")
    val userMobileAccessible: Boolean? = null,
    @SerializedName("userPcMessengerAccessible")
    val userPcMessengerAccessible: Boolean? = null,
    @SerializedName("mailAccountStatus")
    val mailAccountStatus: String? = null,
    @SerializedName("groupDepth")
    val groupDepth: Int? = null
)