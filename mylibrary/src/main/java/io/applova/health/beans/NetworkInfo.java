package io.applova.health.beans;

public class NetworkInfo {
    private final boolean isConnected;
    private final String type;
    private final boolean isWifi;
    private final boolean isMobile;
    private final boolean isVPN;
    private final String networkQuality;
    private final int bandwidth;
    private final String signalStrength;

    public NetworkInfo(boolean isConnected, String type, boolean isWifi, boolean isMobile,
                       boolean isVPN, String networkQuality, int bandwidth, String signalStrength) {
        this.isConnected = isConnected;
        this.type = type;
        this.isWifi = isWifi;
        this.isMobile = isMobile;
        this.isVPN = isVPN;
        this.networkQuality = networkQuality;
        this.bandwidth = bandwidth;
        this.signalStrength = signalStrength;
    }

    // Getters
    public boolean isConnected() { return isConnected; }
    public String getType() { return type; }
    public boolean isWifi() { return isWifi; }
    public boolean isMobile() { return isMobile; }
    public boolean isVPN() { return isVPN; }
    public String getNetworkQuality() { return networkQuality; }
    public int getBandwidth() { return bandwidth; }
    public String getSignalStrength() { return signalStrength; }

    @Override
    public String toString() {
        return "NetworkInfo{" +
                "isConnected=" + isConnected +
                ", type='" + type + '\'' +
                ", isWifi=" + isWifi +
                ", isMobile=" + isMobile +
                ", isVPN=" + isVPN +
                ", networkQuality='" + networkQuality + '\'' +
                ", bandwidth=" + bandwidth +
                ", signalStrength='" + signalStrength + '\'' +
                '}';
    }
}
