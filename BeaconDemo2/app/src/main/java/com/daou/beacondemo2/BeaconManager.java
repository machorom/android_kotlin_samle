package com.daou.beacondemo2;

import android.content.Context;
import android.util.Log;

import mobi.inthepocket.android.beacons.ibeaconscanner.Beacon;
import mobi.inthepocket.android.beacons.ibeaconscanner.IBeaconScanner;

public class BeaconManager {
    private static final String TAG = "BeaconManager";
    private static final Beacon beacon = Beacon.newBuilder()
            .setUUID("FDA50693-A4E2-4FB1-AFCF-C6EB07647825")
            .setMajor(10001)
            .setMinor(19641)
            .build();

    public static synchronized void init(Context context) {
        Log.d(TAG, "init context=" + context);
        IBeaconScanner.initialize(IBeaconScanner.newInitializer(context)
                .setTargetService(BeaconRangingService.class)
                .build());
        IBeaconScanner.getInstance().startMonitoring(beacon);
        IBeaconScanner.getInstance().start();
        Log.d(TAG, "init starMonitor beacon=" + beacon);
    }


    public static synchronized void stop(Context context) {
        IBeaconScanner.initialize(IBeaconScanner.newInitializer(context)
                .setTargetService(BeaconRangingService.class)
                .build());
        IBeaconScanner.getInstance().stopMonitoring(beacon);
        IBeaconScanner.getInstance().stop();
    }
}
