package com.power16.skyblockCopy.data;

import com.power16.skyblockCopy.SkyblockCopy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {

    private final File dataFolder;
    private final Map<UUID, PlayerProfile> profiles = new HashMap<>();

    public ProfileManager() {
        this.dataFolder = new File(SkyblockCopy.getInstance().getDataFolder(), "playerdata");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public PlayerProfile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }

    public void setPlayerDataValue(UUID uuid, String key, Object value) {
        PlayerProfile profile = profiles.get(uuid);
        if (profile != null) {
            profile.setValue(key, value);
        }
    }

    public Object getPlayerDataValue(UUID uuid, String key) {
        PlayerProfile profile = profiles.get(uuid);
        return (profile != null) ? profile.getValue(key) : null;
    }

    public void loadProfile(UUID uuid) {
        File playerFile = new File(dataFolder, uuid.toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);

        PlayerProfile profile = new PlayerProfile(uuid);

        double defaultCoins = SkyblockCopy.getInstance().getConfig().getDouble("starting-coins", 100.0);
        int defaultBits = SkyblockCopy.getInstance().getConfig().getInt("starting-bits", 0);
        profile.setValue("coins", defaultCoins);
        profile.setValue("bits", defaultBits);
        profile.setValue("god-mode", false);

        if (config.getConfigurationSection("data") != null) {
            for (String key : config.getConfigurationSection("data").getKeys(false)) {
                profile.setValue(key, config.get("data." + key));
            }
        }

        profiles.put(uuid, profile);
    }

    public void saveProfile(UUID uuid) {
        PlayerProfile profile = profiles.get(uuid);
        if (profile == null) return;

        File playerFile = new File(dataFolder, uuid.toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);

        for (Map.Entry<String, Object> entry : profile.getAllData().entrySet()) {
            config.set("data." + entry.getKey(), entry.getValue());
        }

        try {
            config.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unloadProfile(UUID uuid) {
        saveProfile(uuid);
        profiles.remove(uuid);
    }

    public void saveAll() {
        for (UUID uuid : profiles.keySet()) {
            saveProfile(uuid);
        }
    }
}
