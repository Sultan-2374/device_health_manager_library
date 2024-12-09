package io.applova.health;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.applova.health.beans.ClientInfo;
import io.applova.health.job.DeviceInfoJob;

public class BootBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = BootBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.d(TAG, "Device rebooted, rescheduling job");

            // Re-schedule the job after reboot
            ClientInfo clientInfo = new ClientInfo();
            clientInfo.setContext(context);
            DeviceInfoJob deviceInfoJob = new DeviceInfoJob();
            deviceInfoJob.initialize(clientInfo);  // Re-schedule the job
        }
    }
}
