package xyz.srnyx.limitlesslag.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingListener;
import xyz.srnyx.annoyingapi.data.EntityData;

import xyz.srnyx.limitlesslag.LimitlessLag;

import java.util.HashSet;
import java.util.Set;


public class BlockListener extends AnnoyingListener {
    @NotNull private final LimitlessLag plugin;
    @NotNull private final Set<Location> blocks = new HashSet<>();

    public BlockListener(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
        return plugin;
    }

    @EventHandler
    public void onBlockBreak(@NotNull BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        final Location location = block.getLocation();

        // Check if block is already "broken"
        if (blocks.remove(location)) {
            event.setCancelled(true);
            return;
        }

        // Check toggle & chance
        if (!new EntityData(plugin, player).has(LimitlessLag.KEY) || LimitlessLag.RANDOM.nextInt(101) > plugin.config.lagChances.block.breakChance) return;
        event.setCancelled(true);

        // Check delay
        final int delay = LimitlessLag.RANDOM.nextInt(200);
        if (delay > 100) return;

        // Delayed break
        blocks.add(location);
        new BukkitRunnable() {
            public void run() {
                block.breakNaturally(player.getItemInHand());
            }
        }.runTaskLater(plugin, delay);
    }

    @EventHandler
    public void onBlockPlace(@NotNull BlockPlaceEvent event) {
        if (new EntityData(plugin, event.getPlayer()).has(LimitlessLag.KEY) && LimitlessLag.RANDOM.nextInt(101) < plugin.config.lagChances.block.placeChance) event.setCancelled(true);
    }
}
