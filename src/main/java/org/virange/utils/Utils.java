package org.virange.utils;


import org.bukkit.ChatColor;


public class Utils {

    public static String colorMessage(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
