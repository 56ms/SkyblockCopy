package com.power16.skyblockCopy.itemManager;

import com.power16.skyblockCopy.SkyblockCopy;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class SkyblockEnchant implements Listener {

    protected abstract String enchantID();

    protected abstract String description();

    protected abstract Integer maxLevel();

    public String getEnchantID() {return enchantID();}

    public List<String> getDescription() {return SkyblockCopy.getInstance().getSharedMethods().wrap(description());}

    public Integer getMaxLevel() {return maxLevel();}

}
