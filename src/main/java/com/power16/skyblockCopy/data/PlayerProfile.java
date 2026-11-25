package com.power16.skyblockCopy.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerProfile {

    private final UUID uuid;
    private final Map<String, Object> data = new HashMap<>();

    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setValue(String key, Object value) {
        data.put(key.toLowerCase(), value);
    }

    // Get raw object
    public Object getValue(String key) {
        return data.get(key.toLowerCase());
    }


    public double getDouble(String key, double defaultValue) {
        Object val = data.get(key.toLowerCase());
        return (val instanceof Number num) ? num.doubleValue() : defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        Object val = data.get(key.toLowerCase());
        return (val instanceof Number num) ? num.intValue() : defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Object val = data.get(key.toLowerCase());
        return (val instanceof Boolean bool) ? bool : defaultValue;
    }

    public String getString(String key, String defaultValue) {
        Object val = data.get(key.toLowerCase());
        return (val != null) ? val.toString() : defaultValue;
    }

    public Map<String, Object> getAllData() {
        return data;
    }
}
