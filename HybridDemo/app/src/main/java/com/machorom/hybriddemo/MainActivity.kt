package com.machorom.hybriddemo

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "FirebaseService"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl((if (request != null) request.getUrl() else null).toString())
                return true
            }
        }
        val setting = web_view.settings
        setting.javaScriptEnabled = true
        if (intent.extras != null && intent.extras.containsKey("click_action") && intent.extras.getString("click_action") == "fcm.ACTION.LINK") {
            Log.i(TAG, "intent extra:" + intent.extras.get("action_url"))
            web_view.loadUrl(intent.extras.getString("action_url"))
        } else if (intent.extras != null && intent.extras.containsKey("click_action") && intent.extras.getString("click_action") == "fcm.ACTION.CALL") {
            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse("tel:" + intent.extras.getString("action_url"))
            startActivity(i)
            web_view.loadUrl("https://www.naver.com")
        } else if(intent.hasExtra("click_action")){
            Log.i(TAG, "MainActive " + intent.getStringExtra("click_action") + "," +intent.getStringExtra("action_url"))
            if( intent.getStringExtra("click_action") == "fcm.ACTION.CALL" ) {
                val i = Intent(Intent.ACTION_CALL)
                i.data = Uri.parse("tel:" + intent.getStringExtra("action_url"))
                startActivity(i)
                web_view.loadUrl("https://mobile.snsform.co.kr/")
            } else {
                web_view.loadUrl(intent.getStringExtra("action_url"))
            }
        } else {
            web_view.loadUrl("https://mobile.snsform.co.kr/")
        }

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                // Log and toast
                Log.d(TAG, "FirebaseInstanceId : $token")
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
    }
}
