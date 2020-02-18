package com.example.firebasedemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
    }

    var masterData: JSONObject = JSONObject()

    override fun onStart() {
        super.onStart()
        val rootRef  = FirebaseDatabase.getInstance().getReference()
        val conditionRef = rootRef.child("message")

        val bt_add = findViewById<Button>(R.id.bt_add)
        val bt_close = findViewById<Button>(R.id.bt_close)
        conditionRef.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val text = p0.getValue()
                masterData = JSONObject(text.toString())
                Toast.makeText(this@AddActivity,"스튜디오 정보 동기화가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@AddActivity,"스튜디오 정보 동기화에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        } )

        val ed_studio_name = findViewById<EditText>(R.id.ed_studio_name)
        val ed_studio_info = findViewById<EditText>(R.id.ed_studio_info)
        val ed_studio_site = findViewById<EditText>(R.id.ed_studio_site)
        val ed_studio_image = findViewById<EditText>(R.id.ed_studio_image)
        bt_add.setOnClickListener { view ->
            val json = """{
                "studio_name":"${ed_studio_name.text.toString()}",
                "studio_info":"${ed_studio_info.text.toString()}",
                "studio_site":"${ed_studio_site.text.toString()}",
                "studio_image":"${ed_studio_image.text.toString()}"
                }
            """.trimIndent()
            if ( masterData.has("studios")){
                val jarr: JSONArray = masterData.get("studios") as JSONArray
                jarr.put(json)
                masterData.put("studios",jarr)
            } else {
                val jarr: JSONArray = JSONArray()
                jarr.put(json)
                masterData.put("studios",jarr)
            }
            conditionRef.setValue(masterData.toString())
        }
        bt_close.setOnClickListener{ view ->
            finish()
        }
    }

}

