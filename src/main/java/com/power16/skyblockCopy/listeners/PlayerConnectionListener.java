package com.power16.skyblockCopy.listeners;

import com.power16.skyblockCopy.SkyblockCopy;
import com.power16.skyblockCopy.data.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SkyblockCopy.getInstance().getProfileManager().loadProfile(player.getUniqueId());

        PlayerProfile profile = SkyblockCopy.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile != null) {
            player.setInvulnerable(profile.getBoolean("god-mode", false));
        }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        // Save their file to Disk and clear them from RAM
        SkyblockCopy.getInstance().getProfileManager().unloadProfile(event.getPlayer().getUniqueId());
    }
}
