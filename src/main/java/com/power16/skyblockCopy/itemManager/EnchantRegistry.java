package com.power16.skyblockCopy.itemManager;

import java.util.HashMap;
import java.util.Map;

public class EnchantRegistry {

    private static final Map<String, SkyblockEnchant> ENCHANTS = new HashMap<>();

    public static void register(SkyblockEnchant enchant) {
        ENCHANTS.put(enchant.getEnchantID(), enchant);
    }

    public static SkyblockEnchant get(String id) {
        return ENCHANTS.get(id);
    }
}
