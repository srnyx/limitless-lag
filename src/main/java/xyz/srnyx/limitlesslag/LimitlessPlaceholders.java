package xyz.srnyx.limitlesslag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import xyz.srnyx.annoyingapi.AnnoyingPAPIExpansion;


public class LimitlessPlaceholders extends AnnoyingPAPIExpansion {
    @NotNull private final LimitlessLag plugin;

    public LimitlessPlaceholders(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getIdentifier() {
        return "limitless";
    }

    @Override @Nullable
    public String onPlaceholderRequest(@Nullable Player player, @NotNull String identifier) {
        // lag
        if (player != null && identifier.equals("lag")) return String.valueOf(plugin.isToggled(player));

        // lag_PLAYER
        if (identifier.startsWith("lag_")) {
            final Player target = Bukkit.getPlayer(identifier.substring(4));
            return target == null ? null : String.valueOf(plugin.isToggled(target));
        }

        return null;
    }
}
