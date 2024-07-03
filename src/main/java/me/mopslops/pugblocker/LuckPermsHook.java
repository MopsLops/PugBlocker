package me.mopslops.pugblocker;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;

public class LuckPermsHook {
    private final PugBlocker plugin;
    private final LuckPerms luckPerms;

    public LuckPermsHook(PugBlocker plugin) {
        this.plugin = plugin;
        try {
            this.luckPerms = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            plugin.getLogger().severe("LuckPerms API is not loaded. Ensure LuckPerms is installed.");
            throw e;
        }
    }

    public boolean hasPermission(Player player, String permission) {
        // Bypass permission checks for ops if applyToOps is false
        if (player.isOp() && !plugin.getConfigManager().applyToOps()) {
            return true;
        }

        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        String fullPermission = "pugblocker." + permission;

        // Check for specific permission, pugblocker.*, or pugblocker
        // If permission is explicitly denied, return false
        return user.getCachedData().getPermissionData(QueryOptions.defaultContextualOptions())
                .checkPermission(fullPermission).asBoolean()
                || user.getCachedData().getPermissionData(QueryOptions.defaultContextualOptions())
                .checkPermission("pugblocker.*").asBoolean()
                || user.getCachedData().getPermissionData(QueryOptions.defaultContextualOptions())
                .checkPermission("pugblocker").asBoolean();
    }
}
