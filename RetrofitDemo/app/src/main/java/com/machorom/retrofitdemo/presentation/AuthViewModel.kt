package com.machorom.retrofitdemo.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.machorom.retrofitdemo.data.repository.AuthRepository
import kotlinx.coroutines.launch

import retrofit2.HttpException

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun loginConfig(){
        viewModelScope.launch {
            //val response = LoginRetroFit.getService().loginConfig()
//            Log.i("API","response=" + response)
        }
    }

    fun login(){
        viewModelScope.launch {
            val loginresponse = authRepository.login("checker","1qaz2wsx#")
            Log.i("API","response=" + loginresponse)
        }
    }

    fun userSession(){
        viewModelScope.launch {
//            try {
//                val sessionresponse = LoginRetroFit.getService().userSession()
//                Log.i("API", "response=" + sessionresponse)
//            }catch(e: HttpException){
//                Log.i("API","code="+e.code()+", message="+e.message())
//            }
        }
    }

}