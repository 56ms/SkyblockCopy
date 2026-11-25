package com.power16.skyblockCopy.itemManager;

import com.power16.skyblockCopy.SkyblockCopy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public abstract class SkyblockItem {

    public static final NamespacedKey ID_KEY = NamespacedKey.fromString("skyblockcopy:item_id");

    protected abstract String itemID();
    protected abstract String displayName();      // e.g. "Aspect of the End"
    protected abstract Material baseItem();
    protected abstract String[] lore();
    protected abstract Rarity rarity();
    protected abstract String displayItemType();  // e.g. "SWORD"
    protected Ability[] abilities() { return new Ability[]{}; }
    protected Enchants[] enchants() { return new Enchants[]{}; }

    public String getId() {
        return itemID();
    }

    public Material getBaseItem() {
        return baseItem();
    }

    public Rarity getRarity() {
        return rarity();
    }

    public String getDisplayItemType() {
        return displayItemType();
    }

    public String[] getLore() {
        return lore();
    }

    public Enchants[] getEnchants() {
        return enchants();
    }

    public Ability[] getAbilities() {
        return abilities();
    }

    public ItemStack createItemStack() {
        ItemStack stack = new ItemStack(baseItem());
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) return stack;

        meta.setDisplayName(rarity().getChatColor() + displayName());

        List<String> lines = new ArrayList<>();

        String[] lore = lore();
        if (lore != null && lore.length > 0) {
            for (String line : lore) {
                lines.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            lines.add("");
        }

        Ability[] abilities = abilities();
        if (abilities != null && abilities.length > 0) {
            for (Ability ability : abilities) {
                lines.add(ChatColor.GOLD + "Item Ability: " + ability.name() + " " + ChatColor.YELLOW + "" + ChatColor.BOLD + ability.trigger().getLabel());
                if (ability.description() != null && !ability.description().isEmpty()) {
                    lines.addAll(SkyblockCopy.getInstance().getSharedMethods().wrap(ability.description()));
                }
                if (ability.costs() != null) {
                    for (AbilityCost cost : ability.costs()) {
                        lines.add(cost.getLoreLine());
                    }
                }
                lines.add("");
            }
        }

        lines.add(rarity().getChatColor() + "" + ChatColor.BOLD + rarity().name().toUpperCase() + " " + displayItemType());

        meta.setLore(lines);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);

        meta.getPersistentDataContainer().set(ID_KEY, PersistentDataType.STRING, itemID());

        stack.setItemMeta(meta);
        return stack;
    }
}
