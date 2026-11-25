package com.power16.skyblockCopy.itemManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface AbilityCost {
    boolean canAfford(Player player);
    void deduct(Player player);
    String getLoreLine();

    record Mana(int amount) implements AbilityCost {
        @Override
        public boolean canAfford(Player player) {
            // Replace with your custom ManaManager check
            return true;
        }

        @Override
        public void deduct(Player player) {

        }

        @Override
        public String getLoreLine() {
            return ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + amount;
        }
    }

    record Health(double amount) implements AbilityCost {
        @Override
        public boolean canAfford(Player player) {
            return player.getHealth() > amount; // > so it doesn't kill the player
        }

        @Override
        public void deduct(Player player) {
            player.setHealth(Math.max(0.5, player.getHealth() - amount));
        }

        @Override
        public String getLoreLine() {
            return ChatColor.DARK_GRAY + "Health Cost: " + ChatColor.RED + (int) amount + "❤";
        }
    }

    record Experience(int levels) implements AbilityCost {
        @Override
        public boolean canAfford(Player player) {
            return player.getLevel() >= levels;
        }

        @Override
        public void deduct(Player player) {
            player.setLevel(player.getLevel() - levels);
        }

        @Override
        public String getLoreLine() {
            return ChatColor.DARK_GRAY + "XP Cost: " + ChatColor.GREEN + levels + " Levels";
        }
    }
}
