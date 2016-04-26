package org.virange;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.virange.settings.Settings;
import org.virange.utils.Utils;

public class Messages {

    private Player player;

    public static final Configuration config = Settings.getConfig();

    public String PREFIX = Utils.colorMessage(config.getString("Messages.Prefix"));
    public String PLUGIN_RELOADED = config.getString("Messages.Plugin-reloaded");
    public String NO_PERMISSION = config.getString("Messages.No-permission");
    public String NOT_ENOUGH_PLAYER_NAME = config.getString("Messages.Not-enough-player-name");
    public String TOO_MANY_ARGUMENTS = config.getString("Messages.Too-many-arguments");
    public String PLAYER_DOESNT_EXIT = config.getString("Messages.Player_Doesnt_Exit");
    public String NO_ARGUMENTS = config.getString("Messages.No_arguments");
    public String IMMUNITY = config.getString("Messages.Immunity");
    public String INCORECTRULE = config.getString("Messages.IncorectRule");
    public Messages(Player sender) {
        this.player = sender;
    }

    public String replacement(String message, String punishwho, String rule) {
        String msg = message.replace("%punishwho%", punishwho).
                replace("%punishby%", player.getName()).
                replace("%rule%", Settings.getRuleList(rule).getRule());
        return Utils.colorMessage(msg);
    }

    public void sendErrorMessage(String message) {
        String msg = Utils.colorMessage(message);
        player.sendMessage(PREFIX + msg);
    }

    public void sendBroadcast(String message, String punishwho, String rule) {
        String msg = replacement(message, punishwho, rule);
        Bukkit.broadcastMessage(PREFIX + msg);
    }
}
