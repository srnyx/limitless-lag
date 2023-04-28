package xyz.srnyx.limitlesslag;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.file.AnnoyingResource;

import xyz.srnyx.limitlesslag.commands.LagCommand;
import xyz.srnyx.limitlesslag.commands.ReloadCommand;
import xyz.srnyx.limitlesslag.listeners.BlockListener;
import xyz.srnyx.limitlesslag.listeners.PlayerListener;

import java.util.Collections;
import java.util.Random;


public class LimitlessLag extends AnnoyingPlugin {
    @NotNull public static final Random RANDOM = new Random();

    public int chanceMove = 5;
    public int chanceBlockBreak = 20;
    public int chanceBlockPlace = 20;

    public LimitlessLag() {
        super();
        Collections.addAll(options.commandsToRegister,
                new LagCommand(this),
                new ReloadCommand(this));
        Collections.addAll(options.listenersToRegister,
                new BlockListener(this),
                new PlayerListener(this));
        reload();
    }

    @Override
    public void reload() {
        // lag-chances
        final ConfigurationSection lagChances = new AnnoyingResource(this, "config.yml").getConfigurationSection("lag-chances");
        if (lagChances == null) return;
        chanceMove = fixChance(lagChances.getInt("move", 5));

        // lag-chances.block
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
        if (chance < 0) chance = 0;
        if (chance > 100) chance = 100;
        return chance;
    }

    /**
     * Check if the player has limitless lag enabled.
     *
     * @param   player  the player to check
     *
     * @return          true if the player has limitless lag enabled
     */
    public boolean isToggled(@NotNull Player player) {
        return player.getScoreboardTags().contains("limitlesslag");
    }

    /**
     * Toggle limitless lag for the player
     *
     * @param   player  the player to toggle limitless lag for
     *
     * @return          true if limitless lag was enabled
     */
    public boolean toggle(@NotNull Player player) {
        // Disable
        if (isToggled(player)) {
            player.removeScoreboardTag("limitlesslag");
            return false;
        }

        // Enable
        player.addScoreboardTag("limitlesslag");
        return true;
    }
}
