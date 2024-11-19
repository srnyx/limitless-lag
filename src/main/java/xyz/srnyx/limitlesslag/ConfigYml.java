package xyz.srnyx.limitlesslag;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.file.AnnoyingResource;


public class ConfigYml {
    @NotNull private final AnnoyingResource config;
    @NotNull public final LagChances lagChances;

    public ConfigYml(@NotNull LimitlessLag plugin) {
        config = new AnnoyingResource(plugin, "config.yml");
        lagChances = new LagChances();
    }

    public class LagChances {
        public int move = fixChance(config.getInt("lag-chances.move", 1));
        @NotNull public final Block block = new Block();

        public class Block {
            public int breakChance = fixChance(config.getInt("lag-chances.block.break", 20));
            public int placeChance = fixChance(config.getInt("lag-chances.block.place", 20));
        }
    }

    private static int fixChance(int chance) {
        return Math.max(Math.min(chance, 100), 0);
    }
}
