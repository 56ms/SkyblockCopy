package com.power16.skyblockCopy.commands;

import com.power16.skyblockCopy.SkyblockCopy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        GameMode mode;

        switch (command.getName().toLowerCase()) {
            case "gmc": mode = GameMode.CREATIVE; break;
            case "gms": mode = GameMode.SURVIVAL; break;
            case "gma": mode = GameMode.ADVENTURE; break;
            case "gmsp": mode = GameMode.SPECTATOR; break;
            default: return true;
        }

        String requiredPermission = "PowerSkyblock.commands.gamemode." + command.getName().toLowerCase();

        if (!sender.hasPermission(requiredPermission)) {
            sender.sendMessage(SkyblockCopy.getInstance().getMessage("message.no-permission"));
            return true;
        }


        Player target;
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
            if (target==null) {
                sender.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }}
        else if (sender instanceof Player player) {
                target = player;
        } else {
            sender.sendMessage(ChatColor.RED + "Console must specify a player.");
            return true;
        }
        target.setGameMode(mode);
        target.sendMessage(ChatColor.YELLOW + "Gamemode set to " + mode.name().substring(1).toLowerCase());
        if (target != sender) {
            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s gamemode to " + mode.name().substring(1).toLowerCase() + ".");
        }




        return true;
    }
}
