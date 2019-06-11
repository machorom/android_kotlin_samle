package com.example.weatherdemo

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

class MainActivity : AppCompatActivity() {

    private var locationManager : LocationManager? = null

    private val PermissionsRequestCompleteCode = 200
    private val PermissionsRequestAleadyCode = 201

    private lateinit var managePermissions: ManagePermissions
    private val permissions = listOf<String>(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // GPS 정보를 받기 위해서는 Manifest의 Permission 설정에 외에 runtime permission도 confirm 받아야한다.
        managePermissions = ManagePermissions(this,permissions,PermissionsRequestCompleteCode,PermissionsRequestAleadyCode)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            managePermissions.checkPermissions()
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("GPSLOG" , "lon=" + location.longitude + ":lat=" + location.latitude)
            // location 정보를 한번만 받기 위해서 lisner를 해지 한다.
            locationManager?.removeUpdates(this)
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    // permission 확인이 끝나면 GPS 정보를 받는다.
    fun requestGPS(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        try {
            // location정보가 한번만 필요하기  때문에 그리 멀지 않은 시간에 추출했으면 그냥 마지막 gps정보를 쓴다.
            val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val diff = System.currentTimeMillis() - location!!.time
            Log.i("GPSLOG", "diffTime="+ diff )
            if (location != null && diff < 80000000L) {
                Log.d("GPSLOG" , "lastknown lon=" + location.longitude + ":lat=" + location.latitude)
            } else {
                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
            }
        } catch(ex: SecurityException) {
            Log.d("GPSLOG", "Security Exception, no location available, "+ ex.message)
        }
    }

    // permission manager의 결과를 기다린다.
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            PermissionsRequestCompleteCode ->{
                val isPermissionsGranted = managePermissions
                    .processPermissionsResult(requestCode,permissions,grantResults)
                if(isPermissionsGranted){
                    // Do the task now
                    Log.i("GPSLOG","Permissions granted.")
                    requestGPS()
                }else{
                    Log.i("GPSLOG","Permissions denied.")
                }
                return
            }
            PermissionsRequestAleadyCode->{
                Log.i("GPSLOG","Permissions aleady.")
                requestGPS()
            }
        }
    }
}
