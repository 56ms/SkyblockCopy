package com.power16.skyblockCopy.utils;

import com.power16.skyblockCopy.SkyblockCopy;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class SharedMethods {

    public List<String> wrap(String text) {
        Integer maxLength = SkyblockCopy.getInstance().getSharedConfig().getMaxLoreLineSize();
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (String word : text.split(" ")) {
            if (line.length() > 0 && line.length() + 1 + word.length() > maxLength) {
                lines.add(ChatColor.GRAY + line.toString());
                line = new StringBuilder();
            }
            if (line.length() > 0) line.append(' ');
            line.append(ChatColor.GRAY +word);
        }
        if (line.length() > 0) lines.add(ChatColor.GRAY +line.toString());
        return lines;
    }
}
