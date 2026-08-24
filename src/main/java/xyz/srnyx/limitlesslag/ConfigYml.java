package xyz.srnyx.limitlesslag;

import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.validator.annotation.Max;
import eu.okaeri.validator.annotation.Min;
import eu.okaeri.validator.annotation.NotNull;
import xyz.srnyx.annoyingapi.file.okaeri.RootConfig;
import xyz.srnyx.annoyingapi.file.okaeri.SubConfig;


public class ConfigYml extends RootConfig {
    @Comment("The chance (amount) out of 100 (%) for a player to lag (that has it enabled) for a certain action")
    @Comment("Maximum value is 100, minimum is 0")
    @NotNull public LagChances lag_chances = new LagChances(this);

    public static class LagChances extends SubConfig<ConfigYml, ConfigYml> {
        public LagChances(@org.jetbrains.annotations.NotNull ConfigYml defaultsParent) {
            super(defaultsParent);
        }

        @Comment("This should be kept low, as it triggers everytime the player moves (even just a tiny bit)")
        @Comment("Default: 1 (1%)")
        @Min(0) @Max(100)
        public int move = 1;

        @Comment
        @Comment("Defaults: 20 (20%)")
        @NotNull public Block block = new Block(this);

        public static class Block extends SubConfig<ConfigYml, LagChances> {
            public Block(@org.jetbrains.annotations.NotNull LagChances defaultsParent) {
                super(defaultsParent);
            }

            @Min(0) @Max(100)
            public int break_chance = 20;

            @Min(0) @Max(100)
            public int place_chance = 20;
        }
    }
}
