package xyz.srnyx.limitlesslag;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.data.StringData;
import xyz.srnyx.limitlesslag.messages.LLMessagesProvider;
import xyz.srnyx.limitlesslag.stats.FastStats;


public class LimitlessLag extends AnnoyingPlugin {
    @NotNull public static final String KEY = "ll_lag";

    public ConfigYml config;

    public LimitlessLag() {
        options
                .statsOptions(statsOptions -> statsOptions
                        .bStats(bStatsOptions -> bStatsOptions.id(18875))
                        .fastStats(fastStatsOptions -> fastStatsOptions.loader(FastStats.class)))
                .dataOptions(dataOptions -> dataOptions
                        .entityDataColumns(KEY))
                .registrationOptions.papiExpansionToRegister(() -> new LimitlessPlaceholders(this));
    }

    @Override @NotNull
    public LLMessagesProvider getMessages() {
        return (LLMessagesProvider) super.getMessages();
    }

    @Override
    public void load() {
        config = configLoader.build(builder -> builder.config(new ConfigYml()));
    }

    @Override
    public void reload() {
        config.reload();
    }

    /**
     * @return  true if limitless lag was enabled
     */
    public boolean toggle(@NotNull OfflinePlayer player, @Nullable Boolean state) {
        final StringData data = new StringData(this, player);
        if (state == null) state = !data.has(KEY);
        data.set(KEY, state ? true : null);
        return state;
    }
}
