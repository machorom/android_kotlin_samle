package com.machorom.retrofitdemo

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.machorom.retrofitdemo.api.AuthApi
import com.machorom.retrofitdemo.api.httpextra.HttpExtra
import com.machorom.retrofitdemo.data.datasource.remote.AuthRemoteDataSource
import com.machorom.retrofitdemo.data.repository.AuthRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideAuthApi(get()) }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

val dataSource = module {
    factory { AuthRemoteDataSource(get()) }
}

val repositoryModule = module {
    factory { AuthRepository(get()) }
}

private fun provideAuthApi(retrofit: Retrofit) = retrofit.create(AuthApi::class.java)

private fun provideOkHttpClient(context: Context) = OkHttpClient().newBuilder()
    .addInterceptor { HttpExtra.defineHeader(it, context) }
    .connectTimeout(5, TimeUnit.MINUTES)
    .writeTimeout(5, TimeUnit.MINUTES)
    .readTimeout(5, TimeUnit.MINUTES)
    .retryOnConnectionFailure(true)
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl("https://nstaging.daouoffice.com/")
    .client(okHttpClient)
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val appModules = listOf(
    networkModule,
    dataSource,
    repositoryModule
)