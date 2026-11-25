package com.power16.skyblockCopy.itemManager;

import org.bukkit.ChatColor;

public enum Rarity {
    Common(ChatColor.WHITE),
    Uncommon( ChatColor.GREEN),
    Rare( ChatColor.BLUE),
    Epic( ChatColor.DARK_PURPLE),
    Legendary( ChatColor.GOLD),
    Mythic( ChatColor.LIGHT_PURPLE),
    Divine( ChatColor.AQUA),
    Special( ChatColor.RED);

    private ChatColor chatColor;

    Rarity(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }



}
