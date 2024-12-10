package io.applova.health.beans;

import android.content.Context;

public class ClientInfo {

    private String deviceId = "123456";
    private String businessId = "987654";
    private Context context;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "deviceId:'" + deviceId + '\'' +
                ", businessId:'" + businessId + '\'' +
                '}';
    }
}
