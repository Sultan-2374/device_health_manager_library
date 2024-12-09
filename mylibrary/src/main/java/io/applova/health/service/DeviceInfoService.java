package io.applova.health.service;

import android.content.Context;
import android.os.Build;
import io.applova.health.DeviceInfoManager;
import io.applova.health.beans.DeviceInfo;

public class DeviceInfoService {

    private static final String TAG = "NetworkInfoService";
//    private final DeviceInfoManager deviceInfoManager;
    private final Context context;

    public DeviceInfoService(Context context) {
        this.context = context;
    }

//    public void logDeviceInfo(){
//        DeviceInfo deviceInfo = deviceInfoManager.getDeviceInfo();
//        Log.d(TAG, "Manufacturer: " + deviceInfo.getManufacturer());
//        Log.d(TAG, "Model: " + deviceInfo.getModel());
//        Log.d(TAG, "Android Version: " + deviceInfo.getAndroidVersion());
//        Log.d(TAG, "SDK Version: " + deviceInfo.getSdkVersion());
//    }

    public DeviceInfo getDeviceInfo() {
        return new DeviceInfo(
                Build.MANUFACTURER,
                Build.MODEL,
                Build.VERSION.RELEASE,
                Build.VERSION.SDK_INT
        );
    }

}








