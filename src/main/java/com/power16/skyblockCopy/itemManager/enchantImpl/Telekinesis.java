package com.power16.skyblockCopy.itemManager.enchantImpl;

import com.power16.skyblockCopy.itemManager.SkyblockEnchant;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Telekinesis extends SkyblockEnchant {
    @Override
    protected String enchantID() {
        return "telekinesis";
    }

    @Override
    protected String description() {
        return "Allows you to automatically pickup blocks when broken and mob drops when killed.";
    }


    @Override
    protected Integer maxLevel() {
        return 1;
    }



}
