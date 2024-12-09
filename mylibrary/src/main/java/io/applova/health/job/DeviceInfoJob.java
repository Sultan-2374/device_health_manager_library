package io.applova.health.job;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import io.applova.health.DeviceInfoManager;
import io.applova.health.beans.AllMetricsInfo;
import io.applova.health.beans.ClientInfo;
import io.applova.health.service.JsonConverterHelper;

public class DeviceInfoJob extends JobService {
    private static final int JOB_ID = 1;
    private static final String TAG = DeviceInfoJob.class.getName();

    @Override
    public boolean onStartJob(JobParameters params) {
        DeviceInfoManager deviceInfoManager = new DeviceInfoManager(this);
        AllMetricsInfo allMetrics = deviceInfoManager.getAllMetrics();
        Log.d(TAG, "Device Metrics: " + JsonConverterHelper.beanToJson(allMetrics));

        // Simulate pushing device info to an API
        pushDeviceInfoToAPI("Device123", "4GB", "2GB", "2GB");

        // Job finished successfully
        jobFinished(params, false);
        return true;
    }

    private void pushDeviceInfoToAPI(String ID, String totalRam, String availableRam, String usedRam) {
        Log.d(TAG, "Pushing device info to API");
        Log.d(TAG, "Device ID: " + ID);
        Log.d(TAG, "Total Ram: " + totalRam);
        Log.d(TAG, "Available Ram: " + availableRam);
        Log.d(TAG, "Used Ram: " + usedRam);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job stopped");
        return false; // Do not reschedule the job
    }

    public void initialize(ClientInfo clientInfo) {
        Context context = clientInfo.getContext();
        ComponentName componentName = new ComponentName(context, DeviceInfoJob.class);

        // Schedule the job to run every 15 minutes (900000L)
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)  // No network requirement
                .setPeriodic(900000L)  // Set the job to run every 15 minutes
                .setPersisted(true)    // Ensure the job survives device reboot
                .build();

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            int resultCode = jobScheduler.schedule(jobInfo);
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "Job scheduled successfully");
            } else {
                Log.d(TAG, "Job scheduling failed");
            }
        } else {
            Log.d(TAG, "Error initializing job service, service is null");
        }
    }
}
