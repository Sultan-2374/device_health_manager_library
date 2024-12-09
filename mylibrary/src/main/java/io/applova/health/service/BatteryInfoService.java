package io.applova.health.service;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import org.json.JSONObject;
import io.applova.health.beans.BatteryInfo;

public class BatteryInfoService {

    private static final String TAG = "BatteryInfoService";
    private final Context context;

    public BatteryInfoService(Context context) {
        this.context = context;
    }

//    public String logBatteryInfo() {
//        BatteryInfo batteryInfo = deviceInfoManager.getBatteryInfo();
//        Log.d(TAG, "Battery Level: " + batteryInfo.getLevel() + "%");
//        Log.d(TAG, "Is Charging: " + batteryInfo.isCharging());
//        Log.d(TAG, "Battery Temperature: " + batteryInfo.getTemperature() + "°C");
//        Log.d(TAG, "Battery Voltage: " + batteryInfo.getVoltage() + "mV");
//        Log.d(TAG, "Battery Technology: " + batteryInfo.getTechnology());
//
//        // Convert the BatteryInfo to JSON
        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("batteryLevel", batteryInfo.getLevel());
//            jsonObject.put("isCharging", batteryInfo.isCharging());
//            jsonObject.put("temperature", batteryInfo.getTemperature());
//            jsonObject.put("voltage", batteryInfo.getVoltage());
//            jsonObject.put("technology", batteryInfo.getTechnology());
//        } catch (Exception e){
//            Log.e(TAG, "Error converting battery info to JSON", e);
//        }
//        String jsonString = jsonObject.toString();
//
//        // Return the JSON string
//        return jsonString;
//    }

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
