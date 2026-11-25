package com.power16.skyblockCopy.listeners;

import com.power16.skyblockCopy.itemManager.Ability;
import com.power16.skyblockCopy.itemManager.AbilityCost;
import com.power16.skyblockCopy.itemManager.ItemRegistry;
import com.power16.skyblockCopy.itemManager.SkyblockItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class AbilityListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;

        Action action = event.getAction();
        if (action == Action.PHYSICAL) return;

        Player player = event.getPlayer();
        ItemStack held = player.getInventory().getItemInMainHand();
        if (held.getType().isAir() || !held.hasItemMeta()) return;

        ItemMeta meta = held.getItemMeta();
        String id = meta.getPersistentDataContainer()
                .get(SkyblockItem.ID_KEY, PersistentDataType.STRING);
        if (id == null) return;

        SkyblockItem item = ItemRegistry.get(id);
        if (item == null) return;

        Ability.Trigger trigger = toTrigger(action, player.isSneaking());
        if (trigger == null) return;

        for (Ability ability : item.getAbilities()) {
            if (ability.trigger() != trigger) continue;

            // Costs
            if (ability.costs() != null) {
                for (AbilityCost cost : ability.costs()) {
                    if (!cost.canAfford(player)) {
                        player.sendMessage(ChatColor.RED + "You can't afford " + ability.name() + "!");
                        return;
                    }
                }
                for (AbilityCost cost : ability.costs()) {
                    cost.deduct(player);
                }
            }

            ability.action().accept(player);
            event.setCancelled(true);
            return;
        }
    }

    private Ability.Trigger toTrigger(Action action, boolean sneaking) {
        boolean right = action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
        boolean left = action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK;

        if (right && sneaking) return Ability.Trigger.SHIFT_RIGHT_CLICK;
        if (right) return Ability.Trigger.RIGHT_CLICK;
        if (left) return Ability.Trigger.LEFT_CLICK;
        return null;
    }
}