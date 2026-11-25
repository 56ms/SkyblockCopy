package com.power16.skyblockCopy.itemManager;

import org.bukkit.entity.Player;
import java.util.List;
import java.util.function.Consumer;

public record Ability(
        String name,
        Trigger trigger,
        List<AbilityCost> costs,
        String description,
        Consumer<Player> action
) {
    public Ability(String name, Trigger trigger, String description, Consumer<Player> action, AbilityCost... costs) {
        this(name, trigger, List.of(costs), description, action);
    }

    public enum Trigger {
        RIGHT_CLICK("RIGHT CLICK"),
        LEFT_CLICK("LEFT CLICK"),
        SHIFT_RIGHT_CLICK("SNEAK RIGHT CLICK");

        private final String label;
        Trigger(String label) { this.label = label; }
        public String getLabel() { return label; }
    }
}
