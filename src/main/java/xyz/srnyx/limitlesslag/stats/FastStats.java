package xyz.srnyx.limitlesslag.stats;

import dev.faststats.Metrics;
import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.stats.loader.FastStatsLoader;
import xyz.srnyx.limitlesslag.LimitlessLag;


public class FastStats extends FastStatsLoader {
    @NotNull private final LimitlessLag plugin;

    public FastStats(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getId() {
        return "d0906ea69c9a0542530b5838991baa5c";
    }

    @Override
    public void mutateMetricsFactory(@NotNull Metrics.Factory factory) {
        factory.addMetric(config("config", () -> plugin.config));
    }
}
