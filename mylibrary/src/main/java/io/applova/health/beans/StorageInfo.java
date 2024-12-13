package io.applova.health.beans;

import static io.applova.health.service.StorageInfoService.formatSize;

public class StorageInfo {
    private final long totalInternalStorage;
    private final long availableInternalStorage;
    private final long totalExternalStorage;
    private final long availableExternalStorage;
    private final long appSize;
    private final long userData;
    private final long cacheSize;
    private final long totalAppSize;
    private final String realmDatabaseSize;

    public StorageInfo(long totalInternalStorage, long availableInternalStorage,
                       long totalExternalStorage, long availableExternalStorage,
                       long appSize, long userData, long cacheSize,
                       long totalAppSize, String realmDatabaseSize) {
        this.totalInternalStorage = totalInternalStorage;
        this.availableInternalStorage = availableInternalStorage;
        this.totalExternalStorage = totalExternalStorage;
        this.availableExternalStorage = availableExternalStorage;
        this.appSize = appSize;
        this.userData = userData;
        this.cacheSize = cacheSize;
        this.totalAppSize = totalAppSize;
        this.realmDatabaseSize = realmDatabaseSize;

    }

    // Getters
    public long getTotalInternalStorage() { return totalInternalStorage; }
    public long getAvailableInternalStorage() { return availableInternalStorage; }
    public long getTotalExternalStorage() { return totalExternalStorage; }
    public long getAvailableExternalStorage() { return availableExternalStorage; }
    public long getAppSize() { return appSize; }
    public long getUserData() { return userData; }
    public long getCacheSize() { return cacheSize; }
    public long getTotalAppSize() { return totalAppSize; }
    public String getRealmDatabaseSize() { return realmDatabaseSize; }

    @Override
    public String toString() {
        return "StorageInfo{" +
                "totalInternalStorage:" + formatSize(totalInternalStorage) +
                ", availableInternalStorage:" + formatSize(availableInternalStorage) +
                ", totalExternalStorage:" + formatSize(totalExternalStorage) +
                ", availableExternalStorage:" + formatSize(availableExternalStorage) +
                ", appSize:" + formatSize(appSize) +
                ", userData:" + formatSize(userData) +
                ", cacheSize:" + formatSize(cacheSize) +
                ", totalAppSize:" + formatSize(totalAppSize) +
                ", realmDatabaseSize:" +  realmDatabaseSize +
                '}';
    }
}