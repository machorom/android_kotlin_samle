package com.daou.beacondemo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.minew.beaconset.BluetoothState;
import com.minew.beaconset.MinewBeacon;
import com.minew.beaconset.MinewBeaconManager;
import com.minew.beaconset.MinewBeaconManagerListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final private static int MY_PERMISSIONS_REQUEST_READ_CONTACTS=100;
    private MinewBeaconManager mMinewBeaconManager;


    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "bluetooth le is not supoort", Toast.LENGTH_SHORT).show();
            finish();
        }
        initPermission();

        mMinewBeaconManager = MinewBeaconManager.getInstance(this);
        mMinewBeaconManager.setMinewbeaconManagerListener(new MinewBeaconManagerListener() {
            @Override
            public void onUpdateBluetoothState(BluetoothState state) {
                switch (state) {
                    case BluetoothStatePowerOff:
                        Toast.makeText(getApplicationContext(), "bluetooth off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothStatePowerOn:
                        Toast.makeText(getApplicationContext(), "bluetooth on", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onRangeBeacons(List<MinewBeacon> beacons) {
//                Collections.sort(beacons, comp);
//                mAdapter.setData(beacons);
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                Log.d("BeaconScan","beacons=" + beacons);
                mBeaconsAdapter.clear();
                mBeaconsAdapter.add("스캔시간 : "+currentDate);
                text.setText("["+currentDate+"]beacons=" + beacons);
                for (MinewBeacon beacon:beacons ) {
                    mBeaconsAdapter.add("uuid=" + beacon.getUuid()+",major=" + beacon.getMajor()+",minor=" + beacon.getMajor()+",macaddress=" + beacon.getMacAddress());
                    Log.d("BeaconScan","uuid=" + beacon.getUuid()+",major=" + beacon.getMajor()+",minor=" + beacon.getMajor()+",macaddress=" + beacon.getMacAddress());
                }
                mBeaconsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAppearBeacons(List<MinewBeacon> beacons) {
            }

            @Override
            public void onDisappearBeacons(List<MinewBeacon> beacons) {
            }
        });
        alertBeaconsScan();
        mMinewBeaconManager.startScan();
    }

    private ArrayAdapter<String> mBeaconsAdapter=null;
    private void alertBeaconsScan(){
        mBeaconsAdapter = new ArrayAdapter<String>( this, R.layout.dialog_list_item);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder( this);
        alertBuilder.setTitle("주위에 비콘 단말기를 확인해보세요~~");
        alertBuilder.setNegativeButton("닫기", new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); } });
        alertBuilder.setAdapter(mBeaconsAdapter, null);
        alertBuilder.show();
    }

    private void initPermission(){
        Log.d("MainActivity","initPermission !!!!!!!!!!!!!!!!!!!!");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "initPermission ACCESS_FINE_LOCATION rationale", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.d("MainActivity","initPermission ACCESS_FINE_LOCATION requestPermissions");
                return;
            }
        }else {
            Log.d("MainActivity","initPermission ACCESS_FINE_LOCATION PERMISSION_GRANTED");
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this, "initPermission ACCESS_COARSE_LOCATION rationale", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.d("MainActivity","initPermission ACCESS_COARSE_LOCATION requestPermissions");
                return;
            }
        }else {
            Log.d("MainActivity","initPermission ACCESS_COARSE_LOCATION PERMISSION_GRANTED");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.BLUETOOTH_ADMIN)) {
                Toast.makeText(this, "initPermission BLUETOOTH_ADMIN rationale", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_ADMIN},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.d("MainActivity","initPermission BLUETOOTH_ADMIN requestPermissions");
                return;
            }
        }else {
            Log.d("MainActivity","initPermission BLUETOOTH_ADMIN PERMISSION_GRANTED");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initPermission();
                } else {
                }
                return;
            }
        }
    }
}
