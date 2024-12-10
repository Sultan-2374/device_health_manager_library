package io.applova.health.service;

import android.content.Context;
import android.os.Build;
import io.applova.health.beans.DeviceInfo;

public class DeviceInfoService {

    private final Context context;

    public DeviceInfoService(Context context) {
        this.context = context;
    }

    public DeviceInfo getDeviceInfo() {
        return new DeviceInfo(
                Build.MANUFACTURER,
                Build.MODEL,
                Build.VERSION.RELEASE,
                Build.VERSION.SDK_INT
        );
    }

}








