package org.virange;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.virange.commands.RulesCommands;
import org.virange.settings.Settings;

public class Rules extends JavaPlugin implements Listener {

    private static Rules instance;

    @Override
    public void onEnable() {
        instance = this;
        Settings.load();
        getCommand("rule").setExecutor(new RulesCommands());

    }

    public static Rules getInstance() {
        return instance;
    }
}
