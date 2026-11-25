package com.power16.skyblockCopy.utils;

import org.bukkit.ChatColor;

public class SharedConfig {
    private String commandErrorMessage = ChatColor.RED + "You do not have permissions to run this command. If you believe this is an error, contact a server administrator.";

    private String plugin_name = "Skyblock Copy";

    public String getCommandErrorMessage() {
        return commandErrorMessage;
    }

    public String getPlugin_name() {
        return plugin_name;
    }

    public Integer getMaxLoreLineSize() {
        return 50;
    }
}
