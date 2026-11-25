package com.power16.skyblockCopy.itemManager.itemImpl;

import com.power16.skyblockCopy.itemManager.Ability;
import com.power16.skyblockCopy.itemManager.Rarity;
import com.power16.skyblockCopy.itemManager.SkyblockItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

public class DefaultItem extends SkyblockItem {

    @Override
    protected String itemID() {
        return "first_item";
    }

    @Override
    protected String displayName() {
        return "First Item";
    }

    @Override
    protected Material baseItem() {
        return Material.DIAMOND_SWORD;
    }

    @Override
    protected String[] lore() {
        return new String[]{ChatColor.GRAY + "The very first item created!"};
    }

    @Override
    protected Rarity rarity() {
        return Rarity.Special;
    }

    @Override
    protected String displayItemType() {
        return "SWORD";
    }

    @Override
    protected Ability[] abilities() {
        return new Ability[]{
                new Ability(
                        "First Strike",
                        Ability.Trigger.RIGHT_CLICK,
                        ChatColor.GRAY + "Plays a " + ChatColor.GREEN + "magical sound " + ChatColor.GRAY + "and sends a welcome message in chat when right-clicked!",
                        player -> {
                            player.sendMessage(ChatColor.GREEN + "You activated First Sound!");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                        }
                        )
        };
    }

}
