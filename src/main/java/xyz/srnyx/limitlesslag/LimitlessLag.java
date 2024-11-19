package xyz.srnyx.limitlesslag;

import org.bukkit.OfflinePlayer;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;
import xyz.srnyx.annoyingapi.data.StringData;

import java.util.Random;


public class LimitlessLag extends AnnoyingPlugin {
    @NotNull public static final String KEY = "ll_lag";
    @NotNull public static final Random RANDOM = new Random();

    public ConfigYml config;

    public LimitlessLag() {
        options
                .pluginOptions(pluginOptions -> pluginOptions.updatePlatforms(
                        PluginPlatform.modrinth("TwwhAG4s"),
                        PluginPlatform.hangar(this),
                        PluginPlatform.spigot("109420")))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(18875))
                .dataOptions(dataOptions -> dataOptions
                        .enabled(true)
                        .entityDataColumns(KEY))
                .registrationOptions
                .automaticRegistration(automaticRegistration -> automaticRegistration.packages(
                        "xyz.srnyx.limitlesslag.commands",
                        "xyz.srnyx.limitlesslag.listeners"))
                .papiExpansionToRegister(() -> new LimitlessPlaceholders(this));

        reload();
    }

    @Override
    public void reload() {
        config = new ConfigYml(this);
    }

    /**
     * @return  true if limitless lag was enabled
     */
    public boolean toggle(@NotNull OfflinePlayer player) {
        final StringData data = new StringData(this, player);
        final boolean has = data.has(KEY);
        data.set(KEY, has ? null : true);
        return !has;
    }
}
