package com.power16.skyblockCopy.commands;

import com.power16.skyblockCopy.SkyblockCopy;
import com.power16.skyblockCopy.data.PlayerProfile;
import com.power16.skyblockCopy.data.ProfileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        SkyblockCopy plugin = SkyblockCopy.getInstance();

        if (!player.hasPermission("PowerSkyblock.commands.god")) {
            player.sendMessage(plugin.getMessage("messages.no-permission"));
            return true;
        }

        ProfileManager profileManager = plugin.getProfileManager();
        PlayerProfile profile = profileManager.getProfile(player.getUniqueId());

        if (profile == null) {
            player.sendMessage(ChatColor.RED + "Your player profile is not loaded. Please rejoin the server.");
            return true;
        }

        boolean currentState = profile.getBoolean("god-mode", false);
        boolean newState = !currentState;

        profileManager.setPlayerDataValue(player.getUniqueId(), "god-mode", newState);
        player.setInvulnerable(newState);

        if (newState) {
            player.sendMessage(plugin.getMessage("messages.god-enabled"));
        } else {
            player.sendMessage(plugin.getMessage("messages.god-disabled"));
        }

        return true;
    }
}
