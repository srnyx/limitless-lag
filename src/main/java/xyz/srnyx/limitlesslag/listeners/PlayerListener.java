package xyz.srnyx.limitlesslag.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingListener;
import xyz.srnyx.annoyingapi.data.EntityData;
import xyz.srnyx.annoyingapi.events.AnnoyingPlayerMoveEvent;

import xyz.srnyx.limitlesslag.LimitlessLag;

import java.util.Set;

import static xyz.srnyx.annoyingapi.reflection.org.bukkit.entity.RefEntity.ENTITY_GET_SCOREBOARD_TAGS_METHOD;
import static xyz.srnyx.annoyingapi.reflection.org.bukkit.entity.RefEntity.ENTITY_REMOVE_SCOREBOARD_TAG_METHOD;


public class PlayerListener implements AnnoyingListener {
    @NotNull private final LimitlessLag plugin;

    public PlayerListener(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
        return plugin;
    }

    @EventHandler
    public void onPlayerMove(@NotNull AnnoyingPlayerMoveEvent event) {
        // Check toggle, movement, and chance
        if (event.getMovementType().equals(AnnoyingPlayerMoveEvent.MovementType.ROTATION) || !plugin.isToggled(event.getPlayer()) || LimitlessLag.RANDOM.nextInt(101) >= plugin.chanceMove) return;

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

    /**
     * @deprecated  Used for old data conversion
     */
    @EventHandler @Deprecated
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        if (ENTITY_GET_SCOREBOARD_TAGS_METHOD == null || ENTITY_REMOVE_SCOREBOARD_TAG_METHOD == null) return;
        final Player player = event.getPlayer();
        try {
            if (!((Set<String>) ENTITY_GET_SCOREBOARD_TAGS_METHOD.invoke(player)).contains("limitlesslag")) return;
            new EntityData(plugin, player).set("ll_lag", true);
            ENTITY_REMOVE_SCOREBOARD_TAG_METHOD.invoke(player, "limitlesslag");
        } catch (final ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
