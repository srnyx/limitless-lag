package xyz.srnyx.limitlesslag;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;
import xyz.srnyx.annoyingapi.data.EntityData;
import xyz.srnyx.annoyingapi.file.AnnoyingResource;

import java.util.Random;


public class LimitlessLag extends AnnoyingPlugin {
    @NotNull public static final Random RANDOM = new Random();

    public int chanceMove = 5;
    public int chanceBlockBreak = 20;
    public int chanceBlockPlace = 20;

    public LimitlessLag() {
        options
                .pluginOptions(pluginOptions -> pluginOptions.updatePlatforms(
                        PluginPlatform.modrinth("limitless-lag"),
                        PluginPlatform.hangar(this, "srnyx"),
                        PluginPlatform.spigot("109420")))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(18875))
                .registrationOptions
                .automaticRegistration(automaticRegistration -> automaticRegistration.packages(
                        "xyz.srnyx.limitlesslag.commands",
                        "xyz.srnyx.limitlesslag.listeners"))
                .papiExpansionToRegister(() -> new LimitlessPlaceholders(this));

        reload();
    }

    @Override
    public void reload() {
        // chanceMove
        final ConfigurationSection lagChances = new AnnoyingResource(this, "config.yml").getConfigurationSection("lag-chances");
        if (lagChances == null) return;
        chanceMove = fixChance(lagChances.getInt("move", 5));

        // chanceBlockBreak & chanceBlockPlace
        final ConfigurationSection blockChances = lagChances.getConfigurationSection("block");
        if (blockChances == null) return;
        chanceBlockBreak = fixChance(blockChances.getInt("break", 20));
        chanceBlockPlace = fixChance(blockChances.getInt("place", 20));
    }

    /**
     * Make sure the chance is between 0 and 100
     *
     * @param   chance  the chance to fix
     *
     * @return          the fixed chance
     */
    private int fixChance(int chance) {
        return Math.max(Math.min(chance, 100), 0);
    }

    /**
     * Check if the player has limitless lag enabled.
     *
     * @param   player  the player to check
     *
     * @return          true if the player has limitless lag enabled
     */
    public boolean isToggled(@NotNull Player player) {
        return new EntityData(this, player).has("ll_lag");
    }

    /**
     * Toggle limitless lag for the player
     *
     * @param   player  the player to toggle limitless lag for
     *
     * @return          true if limitless lag was enabled
     */
    public boolean toggle(@NotNull Player player) {
        final EntityData data = new EntityData(this, player);

        // Disable
        if (isToggled(player)) {
            data.remove("ll_lag");
            return false;
        }

        // Enable
        data.set("ll_lag", true);
        return true;
    }
}
