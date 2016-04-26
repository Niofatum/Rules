package org.virange.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Punishments {

    private CommandSender sender;
    private String target;
    private String punish;

    public Punishments(CommandSender sender, String target, String punish) {
        this.sender = sender;
        this.target = target;
        this.punish = punish;
    }

    public void PunishPlayer(String reason) {
        Bukkit.dispatchCommand(sender, punish + " " + target + " " + reason);
    }

    public void TempPunish(String reason, String time) {
        Bukkit.dispatchCommand(sender, punish + " " + target + " " + reason + " " + time);
    }

}
