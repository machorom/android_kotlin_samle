package com.daou.beacondemo2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import mobi.inthepocket.android.beacons.ibeaconscanner.Beacon;
import mobi.inthepocket.android.beacons.ibeaconscanner.BluetoothScanBroadcastReceiver;

public class BeaconRangingService extends JobIntentService {
    private static final String TAG = "BeaconRangingService";

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork intent " + intent);
        final Beacon beacon = intent.getParcelableExtra(BluetoothScanBroadcastReceiver.IBEACON_SCAN_BEACON_DETECTION);
        final boolean enteredBeacon = intent.getBooleanExtra(BluetoothScanBroadcastReceiver.IBEACON_SCAN_BEACON_ENTERED, false);
        final boolean exitedBeacon = intent.getBooleanExtra(BluetoothScanBroadcastReceiver.IBEACON_SCAN_BEACON_EXITED, false);

        if (beacon != null)
        {
            String logMessage = "";
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            Log.d(TAG, "entered beacon " + beacon.getUUID() + ", enteredBeacon="+enteredBeacon+ ", exitedBeacon="+exitedBeacon);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "beacon")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("비콘감지-"+currentDate)
                    .setContentText("비콘 감지를 테스트 합니다.")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(beacon.getUUID() + "가 감지되었습니다."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(100, builder.build());
        }
    }

}
