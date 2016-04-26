package org.virange.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.virange.Messages;
import org.virange.Rules;
import org.virange.utils.Punishments;
import org.virange.settings.Settings;
import org.virange.utils.Utils;

public class RulesCommands implements CommandExecutor {

    private String rule;
    private String reason;
    private String message;
    private String punishtype;
    private String time;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        Messages Message = new Messages(player);

        if (args.length == 0) {
            Message.sendErrorMessage(Message.NO_ARGUMENTS);
            return true;
        }

        if (args[0].equals("reload")) {
            Rules.getInstance().reloadConfig();
            Settings.load();
            Message.sendErrorMessage(Message.PLUGIN_RELOADED);
            return true;
        }

        if (args.length == 1) {
            Message.sendErrorMessage(Message.NOT_ENOUGH_PLAYER_NAME);
            return true;
        }

        if (args.length > 2) {
            Message.sendErrorMessage(Message.TOO_MANY_ARGUMENTS);
            return true;
        }

        if (Settings.getRuleList(args[0]) == null) {
            Message.sendErrorMessage(Message.INCORECTRULE);
            return true;
        }

        rule = Settings.getRuleList(args[0]).getRule();
        reason = Settings.getRuleList(args[0]).getReason();
        message = Settings.getRuleList(args[0]).getMessage();
        punishtype = Settings.getRuleList(args[0]).getPunish();

        if (Bukkit.getPlayer(args[1]) == null) {
            Message.sendErrorMessage(Message.PLAYER_DOESNT_EXIT);
            return false;
        }

        if (!player.hasPermission("rules." + punishtype)) {
            Message.sendErrorMessage(Message.NO_PERMISSION);
            return false;
        }

        if (player.hasPermission("rules.immunity")) {
            Message.sendErrorMessage(Message.IMMUNITY);
            return false;
        }

        Punishments punish = new Punishments(sender, args[1], punishtype);

        if (Settings.getRuleList(args[0]).getTime() != null) {
            time = Settings.getRuleList(args[0]).getTime();
            punish.TempPunish(reason, time);
            Message.sendBroadcast(message, args[1], args[0]);
            return true;
        }

        punish.PunishPlayer(reason);
        Message.sendBroadcast(message, args[1], args[0]);
        return false;
    }
}
