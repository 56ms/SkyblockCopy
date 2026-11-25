package com.power16.skyblockCopy;

import com.power16.skyblockCopy.commands.GamemodeCommand;
import com.power16.skyblockCopy.commands.GodCommand;
import com.power16.skyblockCopy.data.ProfileManager;
import com.power16.skyblockCopy.listeners.PlayerConnectionListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyblockCopy extends JavaPlugin {

    private static SkyblockCopy instance;
    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        profileManager = new ProfileManager();

        for (Player online : getServer().getOnlinePlayers()) {
            profileManager.loadProfile(online.getUniqueId());
        }

        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getCommand("god").setExecutor(new GodCommand());

        GamemodeCommand gamemodeCommand = new GamemodeCommand();
        for (String cmd : new String[]{"gmc", "gms", "gma", "gmsp"}) {
            getCommand(cmd).setExecutor(gamemodeCommand);
        }

        getServer().getScheduler().runTaskTimer(this, () -> profileManager.saveAll(), 6000L, 6000L);

        getLogger().info("SkyblockCopy Enabled!");
    }

    @Override
    public void onDisable() {
        if (profileManager != null) {
            profileManager.saveAll();
        }
        getLogger().info("SkyblockCopy Disabled!");
    }

    public String getMessage(String path) {
        String raw = getConfig().getString(path, "&cMissing config message: " + path);
        return ChatColor.translateAlternateColorCodes('&', raw);
    }

    public static SkyblockCopy getInstance() {
        return instance;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
