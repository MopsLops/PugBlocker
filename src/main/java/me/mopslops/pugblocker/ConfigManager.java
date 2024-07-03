package me.mopslops.pugblocker;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final PugBlocker plugin;
    private FileConfiguration config;

    public ConfigManager(PugBlocker plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
    }

    public boolean applyToOps() {
        return config.getBoolean("applyToOps", true);
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }
}
