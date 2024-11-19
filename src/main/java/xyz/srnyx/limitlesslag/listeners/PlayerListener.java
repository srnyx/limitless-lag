package xyz.srnyx.limitlesslag.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingListener;
import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.data.EntityData;
import xyz.srnyx.annoyingapi.events.AdvancedPlayerMoveEvent;

import xyz.srnyx.limitlesslag.LimitlessLag;

import java.util.Map;
import java.util.logging.Level;


public class PlayerListener extends AnnoyingListener {
    @NotNull private final LimitlessLag plugin;

    public PlayerListener(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
        return plugin;
    }

    @EventHandler
    public void onPlayerMove(@NotNull AdvancedPlayerMoveEvent event) {
        // Check toggle, movement, and chance
        if (event.getMovementType().equals(AdvancedPlayerMoveEvent.MovementType.ROTATION) || !new EntityData(plugin, event.getPlayer()).has(LimitlessLag.KEY) || LimitlessLag.RANDOM.nextInt(101) >= plugin.config.lagChances.move) return;

        // Set the new location to simulate lag
        final Location from = event.getFrom();
        final Location to = event.getTo();
        final double divider = LimitlessLag.RANDOM.nextInt(50) / 100.0 + 1.01;
        final Location newTo = from.clone().add(
                (to.getX() - from.getX()) / divider,
                0,
                (to.getZ() - from.getZ()) / divider);
        newTo.setYaw(to.getYaw());
        newTo.setPitch(to.getPitch());
        event.setTo(newTo);
    }

    //TODO remove in future
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Map<String, String> failed = new EntityData(plugin, player).convertOldData(LimitlessLag.KEY);
        if (failed == null || !failed.isEmpty()) AnnoyingPlugin.log(Level.SEVERE, "&cFailed to convert data for &4" + player.getName() + "&c: &4" + failed);
    }
}
