package io.applova.health;

import android.content.Context;
import android.telephony.TelephonyManager;

import io.applova.health.beans.AllMetricsInfo;
import io.applova.health.beans.BatteryInfo;
import io.applova.health.beans.ClientInfo;
import io.applova.health.beans.DeviceInfo;
import io.applova.health.beans.NetworkInfo;
import io.applova.health.beans.StorageInfo;
import io.applova.health.job.DeviceInfoJob;
import io.applova.health.service.BatteryInfoService;
import io.applova.health.service.DeviceInfoService;
import io.applova.health.service.NetworkInfoService;
import io.applova.health.service.StorageInfoService;
import io.realm.Realm;

public class DeviceInfoManager {

    private static final String PREFS_NAME = "DeviceInfoPrefs";
    private static final String KEY_UNIQUE_ID = "uniqueId";

    private final Context context;
//    public DeviceInfoManager(DeviceInfoJob deviceInfoJob) {
//    }

    public DeviceInfoManager (Context context){
        this.context = context;
    }

//    private final TelephonyManager telephonyManager;

//    public String getDeviceId() {
//        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//    }

//    public DeviceInfoManager(Context context) {
//        this.context = context;
//        this.telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//    }

    public AllMetricsInfo getAllMetrics(){

        BatteryInfoService batteryInfoService = new BatteryInfoService(context);
        BatteryInfo batteryInfo = batteryInfoService.getBatteryInfo();

        //network info
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        NetworkInfoService networkInfoService = new NetworkInfoService(context, telephonyManager);
        NetworkInfo networkInfo = networkInfoService.getNetworkInfo();

//        NetworkInfoService networkInfoService = new NetworkInfoService(context);
//        NetworkInfo networkInfo = networkInfoService.getNetworkInfo();

        //client info
        ClientInfo clientInfo = new ClientInfo();

        //device info
        DeviceInfoService deviceInfoService = new DeviceInfoService(context);
        DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo();

        //storage info
        StorageInfoService storageInfoService = new StorageInfoService(context);
        StorageInfo storageInfo = storageInfoService.getStorageInfo();

        AllMetricsInfo allMetricsInfo = new AllMetricsInfo();
        allMetricsInfo.setBatteryInfo(batteryInfo);
        //set network info
        allMetricsInfo.setNetworkInfo(networkInfo);
        //set client info
        allMetricsInfo.setClientInfo(clientInfo);
        //set device info
        allMetricsInfo.setDeviceInfo(deviceInfo);
        //set storoage info
        allMetricsInfo.setStorageInfo(storageInfo);
        return allMetricsInfo;
    }

//    // Ram Info
//    public static String getTotalRam(Context context) {
//        ActivityManager.MemoryInfo memoryInfo = getMemoryInfo(context);
//        return DeviceInfoManager.formatBytes(memoryInfo.totalMem);
//    }
//
//    public static String getAvailableRam(Context context) {
//        ActivityManager.MemoryInfo memoryInfo = getMemoryInfo(context);
//        return DeviceInfoManager.formatBytes(memoryInfo.availMem);
//    }
//
//    public static String getUsedRam(Context context) {
//        ActivityManager.MemoryInfo memoryInfo = getMemoryInfo(context);
//        long totalRam = memoryInfo.totalMem;
//        long usedRam = totalRam - memoryInfo.availMem;
//        return DeviceInfoManager.formatBytes(usedRam);
//    }
//
//    private static ActivityManager.MemoryInfo getMemoryInfo(Context context) {
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
//        activityManager.getMemoryInfo(memoryInfo);
//        return memoryInfo;
//    }
//
//    public String getUniqueId() {
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        String uniqueId = prefs.getString(KEY_UNIQUE_ID, null);
//
//        if (uniqueId == null) {
//            uniqueId = UUID.randomUUID().toString();
//            prefs.edit().putString(KEY_UNIQUE_ID, uniqueId).apply();
//        }
//        return uniqueId;
//    }
}


