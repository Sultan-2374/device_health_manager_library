package io.applova.health.beans;

public class BatteryInfo {
    private final int level;
    private final boolean isCharging;
    private final float temperature;
    private final int voltage;
    private final String technology;

    public BatteryInfo(int level, boolean isCharging, float temperature, int voltage, String technology) {
        this.level = level;
        this.isCharging = isCharging;
        this.temperature = temperature;
        this.voltage = voltage;
        this.technology = technology;
    }

    // Getters
    public int getLevel() { return level; }
    public boolean isCharging() { return isCharging; }
    public float getTemperature() { return temperature; }
    public int getVoltage() { return voltage; }
    public String getTechnology() { return technology; }

    @Override
    public String toString() {
        return "BatteryInfo{" +
                "level:" + level +
                ", isCharging:" + isCharging +
                ", temperature:" + temperature +
                ", voltage:" + voltage +
                ", technology:'" + technology + '\'' +
                '}';
    }
}
