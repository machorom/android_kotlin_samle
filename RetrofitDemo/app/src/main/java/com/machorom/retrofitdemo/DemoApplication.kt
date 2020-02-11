package com.machorom.retrofitdemo

import android.app.Application
import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import com.machorom.retrofitdemo.common.AuthPreference
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DemoApplication : Application() {

    override fun onCreate() {
         val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        display = windowManager.defaultDisplay
        display.getSize(size)
        super.onCreate()
        authPreference =  AuthPreference(this)
        startKoin {
            androidLogger()
            androidContext(this@DemoApplication)
            modules(appModules)
        }
    }

    companion object {
        lateinit var authPreference:AuthPreference
        lateinit var display: Display
        val size = Point()
    }
}