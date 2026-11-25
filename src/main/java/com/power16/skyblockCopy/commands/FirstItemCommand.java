package com.power16.skyblockCopy.commands;

import com.power16.skyblockCopy.itemManager.ItemRegistry;
import com.power16.skyblockCopy.itemManager.itemImpl.DefaultItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FirstItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Player player = (Player) sender;

        player.getInventory().addItem(ItemRegistry.get("first_item").createItemStack());

        return true;
    }
}
