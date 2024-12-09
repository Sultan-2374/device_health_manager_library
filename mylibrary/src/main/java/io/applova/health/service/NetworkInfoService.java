package io.applova.health.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import io.applova.health.beans.NetworkInfo;

public class NetworkInfoService {

    private static final String TAG = "NetworkInfoService";
    private final Context context;
    private final TelephonyManager telephonyManager;

    public NetworkInfoService(Context context, TelephonyManager telephonyManager) {
        this.context = context;
        this.telephonyManager = telephonyManager;
    }

//    public void logNetworkInfo(){
//        NetworkInfo networkInfo = deviceInfoManager.getNetworkInfo();
//        Log.d(TAG, "Is Connected: " + networkInfo.isConnected());
//        Log.d(TAG, "Connection Type: " + (networkInfo.getType() != null ? networkInfo.getType() : "Unknown"));
//        Log.d(TAG, "Is WiFi: " + networkInfo.isWifi());
//        Log.d(TAG, "Is Mobile: " + networkInfo.isMobile());
//        Log.d(TAG, "Is VPN: " + networkInfo.isVPN());
//        Log.d(TAG, "Network Quality: " + (networkInfo.getNetworkQuality() != null ? networkInfo.getNetworkQuality() : "Unknown"));
//        Log.d(TAG, "Bandwidth: " + networkInfo.getBandwidth() + " Mbps");
//        Log.d(TAG, "Signal Strength: " + (networkInfo.getSignalStrength() != null ? networkInfo.getSignalStrength() : "Unknown"));
//    }

    public NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Default values
        boolean isWifi = false;
        boolean isMobile = false;
        boolean isVPN = false;
        String type = "None";
        String networkQuality = "Unknown";
        int bandwidth = 0;
        String signalStrength = "Unknown";

        android.net.Network network = connectivityManager.getActiveNetwork();
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);

        if (capabilities != null) {
            isWifi = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            isMobile = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
            isVPN = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN);

            if (isWifi) type = "WiFi";
            else if (isMobile) type = "Mobile";
            else if (isVPN) type = "VPN";

            // Bandwidth and quality determination
            bandwidth = capabilities.getLinkDownstreamBandwidthKbps() / 1000; // Mbps
            networkQuality = getNetworkQuality(bandwidth);
            signalStrength = isWifi ? getWifiSignalStrength() : getMobileSignalStrength();
        }

        return new NetworkInfo(isWifi || isMobile || isVPN, type, isWifi, isMobile, isVPN,
                networkQuality, bandwidth, signalStrength);
    }

    private String getWifiSignalStrength() {
        WifiManager wifiManager = (WifiManager)
                context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int rssi = wifiInfo.getRssi();

        if (rssi >= -50) return "Excellent";
        else if (rssi >= -60) return "Very Good";
        else if (rssi >= -70) return "Good";
        else if (rssi >= -80) return "Fair";
        else return "Poor";
    }

    private String getMobileSignalStrength() {
        if (telephonyManager == null) return "Unknown";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            SignalStrength signalStrength = telephonyManager.getSignalStrength();
            if (signalStrength == null) return "Unknown";

            int gsmSignalStrength = signalStrength.getGsmSignalStrength();
            if (gsmSignalStrength != 99) {
                int dBm = -113 + (2 * gsmSignalStrength);
                if (dBm >= -70) return "Excellent";
                else if (dBm >= -85) return "Very Good";
                else if (dBm >= -100) return "Good";
                else if (dBm >= -110) return "Fair";
                else return "Poor";
            }
        }
        return "Unknown";
    }

    private String getNetworkQuality(int bandwidth) {
        if (bandwidth >= 100) return "Excellent";
        else if (bandwidth >= 50) return "Very Good";
        else if (bandwidth >= 20) return "Good";
        else if (bandwidth >= 10) return "Fair";
        else return "Poor";
    }

}
