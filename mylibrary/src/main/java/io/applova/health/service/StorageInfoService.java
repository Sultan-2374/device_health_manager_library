package io.applova.health.service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;
import io.applova.health.beans.StorageInfo;
import io.realm.Realm;

public class StorageInfoService {

    private static final String TAG = "CacheSize";
    private final Context context;

    public StorageInfoService(Context context) {
        this.context = context;
    }

    public StorageInfo getStorageInfo() {
        StatFs internalStats = new StatFs(Environment.getDataDirectory().getPath());
        StatFs externalStats = new StatFs(Environment.getExternalStorageDirectory().getPath());

        // Get app-specific storage information
        long appSize = 0;
        long userData = 0;
        long cacheSize = 0;
        long totalAppSize = 0;

        try {
            // Get app size
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            File appDir = new File(applicationInfo.sourceDir);
            appSize = appDir.length();

            // Get user data size
            File dataDir = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dataDir = new File(context.getDataDir().getAbsolutePath());
            }
            assert dataDir != null;
            userData = getFolderSize(dataDir);// - getCacheSize(context);

            // Calculate cache size (include internal and external cache directories)
            File internalCacheDir = context.getCacheDir();
            if (internalCacheDir != null) {
                cacheSize += getFolderSize(internalCacheDir);
            }

            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                cacheSize += getFolderSize(externalCacheDir);
            }

            // Calculate total
            totalAppSize = appSize + userData + cacheSize;

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Application package not found", e);
        }

        return new StorageInfo(
                internalStats.getTotalBytes(),
                internalStats.getAvailableBytes(),
                externalStats.getTotalBytes(),
                externalStats.getAvailableBytes(),
                appSize,
                userData,
                cacheSize,
                totalAppSize,
                getRealmDatabaseSize()
        );
    }

    private static long getFolderSize(File folder) {
        long size = 0;

        if (folder != null && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        size += file.length();
                    } else {
                        size += getFolderSize(file);
                    }
                }
            }
        }
        return size;
    }

    public String getRealmDatabaseSize() {

        try (Realm realm = Realm.getDefaultInstance()) {
            String realmPath = realm.getPath();

            // Get the file and check its size`
            File realmFile = new File(realmPath);
            long realmSize = realmFile.length();  // Size in bytes

            return String.format("%.4f MB", realmSize / (1024.0 * 1024.0));
        }
    }

    public static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }

    public static String formatSize(long bytes) {
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        double size = bytes;
        int unit = 0;
        while (size >= 1024 && unit < units.length - 1) {
            size /= 1024;
            unit++;
        }
        return String.format("%.2f %s", size, units[unit]);
    }
}
