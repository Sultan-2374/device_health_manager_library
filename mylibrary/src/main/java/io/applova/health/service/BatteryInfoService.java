package io.applova.health.service;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import io.applova.health.beans.BatteryInfo;

public class BatteryInfoService {

    private final Context context;

    public BatteryInfoService(Context context) {
        this.context = context;
    }

    public BatteryInfo getBatteryInfo() {
        Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int level = batteryIntent != null ? batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        boolean isCharging = batteryIntent != null &&
                batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1) == BatteryManager.BATTERY_STATUS_CHARGING;
        float temperature = batteryIntent != null ?
                batteryIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10f : -1;
        int voltage = batteryIntent != null ? batteryIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) : -1;
        String technology = batteryIntent != null ?
                batteryIntent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY) : "Unknown";

        return new BatteryInfo(level, isCharging, temperature, voltage, technology);
    }
}
