package com.power16.skyblockCopy.listeners;

import com.power16.skyblockCopy.SkyblockCopy;
import com.power16.skyblockCopy.data.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {


    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player)) {return;}
        Player player = (Player) event.getEntity();

        SkyblockCopy.getInstance().getProfileManager().loadProfile(player.getUniqueId());

        PlayerProfile profile = SkyblockCopy.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile != null) {
           event.setCancelled(profile.getBoolean("god-mode", false));
        }


    }
}
