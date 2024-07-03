package me.mopslops.pugblocker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PugBlocker extends JavaPlugin {
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        LuckPermsHook luckPermsHook = new LuckPermsHook(this);
        PlayerListener playerListener = new PlayerListener(this, luckPermsHook); // Pass a reference to PugBlocker

        getServer().getPluginManager().registerEvents(playerListener, this);
        getLogger().info("PugBlocker has been enabled");

        PluginCommand reloadCommand = getCommand("reload");
        if (reloadCommand != null) {
            reloadCommand.setExecutor(new ReloadConfigCommand());
        } else {
            getLogger().warning("Failed to register reload command! Check your plugin.yml.");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("PugBlocker has been disabled");
    }

    // Public method to provide access to ConfigManager instance
    public ConfigManager getConfigManager() {
        return configManager;
    }

    // Getters for messages from config
    public String getMessageNoPermission() {
        return getConfig().getString("messages.noPermission", "You do not have permission to execute this command.");
    }

    public String getMessageConfigReloaded() {
        return getConfig().getString("messages.configReloaded", "PugBlocker config reloaded!");
    }

    public String getMessageChatNoPermission() {
        return getConfig().getString("messages.chatNoPermission", "You do not have permission to chat.");
    }

    private class ReloadConfigCommand implements CommandExecutor {
        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
            if (args.length > 0 && args[0].equalsIgnoreCase("pugblocker")) {
                if (!sender.isOp() && !sender.hasPermission("pugblocker.reload")) {
                    sender.sendMessage(getMessageNoPermission());
                    return true;
                }

                getConfigManager().reloadConfig();
                sender.sendMessage(getMessageConfigReloaded());
                return true;
            }
            return false;
        }
    }
}
