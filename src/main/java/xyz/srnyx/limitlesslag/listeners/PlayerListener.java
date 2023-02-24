package xyz.srnyx.limitlesslag.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingListener;

import xyz.srnyx.limitlesslag.LimitlessLag;


public class PlayerListener implements AnnoyingListener {
    @NotNull private final LimitlessLag plugin;

    public PlayerListener(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getPlugin() {
        return plugin;
    }

    @EventHandler
    public void onPlayerMove(@NotNull PlayerMoveEvent event) {
        // Check toggle, movement, and chance
        final Location from = event.getFrom();
        final Location to = event.getTo();
        if (from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ() || !plugin.isToggled(event.getPlayer()) || plugin.random.nextInt(101) >= plugin.chanceMove) return;

        // Set the new location to simulate lag
        final double divider = plugin.random.nextInt(50) / 100.0 + 1.01;
        final Location newTo = from.clone().add(
                (to.getX() - from.getX()) / divider,
                0,
                (to.getZ() - from.getZ()) / divider);
        newTo.setYaw(to.getYaw());
        newTo.setPitch(to.getPitch());
        event.setTo(newTo);
    }
}
