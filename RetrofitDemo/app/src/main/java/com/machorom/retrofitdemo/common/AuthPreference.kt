package com.machorom.retrofitdemo.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AuthPreference{
    constructor(context: Context){
        preferenceManager = context.getSharedPreferences(FILE_NAME,0)
    }

    companion object{
        private const val FILE_NAME = "daouoffice_auth"
        private const val APP_KEY = "app_key"
    }
    var preferenceManager: SharedPreferences


    var appKey:String?
        get() = preferenceManager.getString(APP_KEY,"")?:""
        set(value) = preferenceManager.edit{
            putString(APP_KEY, value)
        }
}