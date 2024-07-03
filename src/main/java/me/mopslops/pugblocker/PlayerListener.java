package me.mopslops.pugblocker;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

import java.util.HashSet;
import java.util.Set;

public class PlayerListener implements Listener {
    private final PugBlocker plugin;
    private final LuckPermsHook luckPermsHook;
    private final Set<org.bukkit.block.Block> openBlocks = new HashSet<>();

    public PlayerListener(PugBlocker plugin, LuckPermsHook luckPermsHook) {
        this.plugin = plugin;
        this.luckPermsHook = luckPermsHook;
    }

    private boolean hasPermission(Player player, String permission) {
        return luckPermsHook.hasPermission(player, permission);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "move")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "break")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "place")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        Player player = event.getPlayer();
        if (player != null && !hasPermission(player, "nearplace")) {
            for (BlockFace face : BlockFace.values()) {
                if (face != BlockFace.SELF && event.getBlock().getRelative(face).getType() != Material.AIR) {
                    event.setBuildable(false);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "chat")) {
            event.setCancelled(true);
            player.sendMessage(plugin.getMessageChatNoPermission());
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "drop")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!hasPermission(player, "pickup")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "interact")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (!hasPermission(player, "inventory.click")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (!hasPermission(player, "inventory.drag")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            if (!hasPermission(player, "inventory.open")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        if (event.getViewers().stream().anyMatch(viewer -> viewer instanceof Player && !hasPermission((Player) viewer, "craft"))) {
            event.getInventory().setResult(null);
        }
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "bucket")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if (!hasPermission(player, "bucket")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockOpen(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !hasPermission(player, "open")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerOpenBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            org.bukkit.block.Block block = event.getClickedBlock();
            if (block != null) {
                if (openBlocks.contains(block)) {
                    event.setCancelled(true);
                } else {
                    openBlocks.add(block);
                    plugin.getServer().getScheduler().runTaskLater(plugin, () -> openBlocks.remove(block), 20L);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerStepOnPlate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock() != null && event.getClickedBlock().getType().toString().contains("PRESSURE_PLATE") && !hasPermission(player, "plate")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        if (event.getBlock().getWorld().getPlayers().stream().anyMatch(player -> !hasPermission(player, "piston"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        if (event.getBlock().getWorld().getPlayers().stream().anyMatch(player -> !hasPermission(player, "piston"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (!hasPermission(player, "attack")) {
                event.setCancelled(true);
            }
        }
    }
}
