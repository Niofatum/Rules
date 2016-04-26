package org.virange.settings;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.virange.Rules;
import org.virange.utils.RulesList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Settings {

    public static HashMap<String, RulesList> RuleList;

    private static Configuration configuration = Rules.getInstance().getConfig();

    public static Configuration getConfig() {
        return configuration;
    }

    public static void load() {
        Rules.getInstance().saveDefaultConfig();
        FileConfiguration rulesfile = loadFile("rules.yml");
        RuleList = new HashMap<String, RulesList>();

        for (String rule : rulesfile.getKeys(false)) {

            ConfigurationSection node = rulesfile.getConfigurationSection(rule);

            if (!node.isSet("punish")) {
                System.out.print("The rule " + rule + " has no punishment!");
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(rule);
                list.add(node.getString("reason"));
                list.add(node.getString("punish"));
                list.add(node.getString("message"));
                list.add(node.getString("time"));

                RulesList ruleList = new RulesList(list);
                RuleList.put(rule, ruleList);
            }
        }
    }

    public static FileConfiguration loadFile(String path) {
        if (!path.endsWith(".yml")) path = path + ".yml";

        File file = new File(Rules.getInstance().getDataFolder(), path);

        if (!file.exists()) {
            try {
                Rules.getInstance().saveResource(path, false);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        YamlConfiguration RulesFile = YamlConfiguration.loadConfiguration(file);

        return RulesFile;
    }

    public static RulesList getRuleList(String rule) {
        return RuleList.get(rule);
    }
}
