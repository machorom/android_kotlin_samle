package com.machorom.retrofitdemo

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.machorom.retrofitdemo.data.model.auth.LoginConfigResponse
import com.machorom.retrofitdemo.data.model.auth.LoginResponse
import com.machorom.retrofitdemo.data.model.auth.SessionResponse
import com.machorom.retrofitdemo.data.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : KoinTest {

    val service : AuthRepository by inject()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.machorom.retrofitdemo", appContext.packageName)
    }

    @Test
    fun loginConfig_ok() {
        runBlocking {
            val loginConfigResponse: LoginConfigResponse = service.loginConfig()
            println(loginConfigResponse)
            assertEquals("200", loginConfigResponse.code)
        }
    }

    @Test
    fun login_ok() {
        runBlocking {
            val loginResponse: LoginResponse = service.login("checker", "1qaz2wsx#")
            println(loginResponse)
            assertEquals("200", loginResponse.code)
        }
    }

    @Test
    fun usersission_ok() {
        runBlocking {
            val sessionResponse: SessionResponse = service.userSession()
            println(sessionResponse)
            assertEquals("200", sessionResponse.code)
        }
    }
}
