package com.daou.beacondemo;

import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import mobi.inthepocket.android.beacons.ibeaconscanner.Beacon;
import mobi.inthepocket.android.beacons.ibeaconscanner.BluetoothScanBroadcastReceiver;

public class BeaconRagingService extends JobIntentService {
    private static final String TAG = "BeaconRangingService";


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        final Beacon beacon = intent.getParcelableExtra(BluetoothScanBroadcastReceiver.IBEACON_SCAN_BEACON_DETECTION);
        final boolean enteredBeacon = intent.getBooleanExtra(BluetoothScanBroadcastReceiver.IBEACON_SCAN_BEACON_ENTERED, false);
        final boolean exitedBeacon = intent.getBooleanExtra(BluetoothScanBroadcastReceiver.IBEACON_SCAN_BEACON_EXITED, false);

        if (beacon != null)
        {
            String logMessage = "";
            if (enteredBeacon)
            {
                Log.d(TAG, "entered beacon " + beacon.getUUID());
                Intent autoIntent = new Intent(this.getBaseContext(), MainActivity.class);
                autoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(autoIntent);
            }
            else if (exitedBeacon)
            {
                Log.d(TAG, "exited beacon " + beacon.getUUID());
            }
        }
    }
}
