package com.power16.skyblockCopy.itemManager;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {

    private static final Map<String, SkyblockItem> ITEMS = new HashMap<>();

    public static void register(SkyblockItem item) {
        ITEMS.put(item.getItemID(), item);
    }

    public static SkyblockItem get(String id) {
        return ITEMS.get(id);
    }
}