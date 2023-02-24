package xyz.srnyx.limitlesslag.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingMessage;
import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;

import xyz.srnyx.limitlesslag.LimitlessLag;


public class LagCommand implements AnnoyingCommand {
    @NotNull private final LimitlessLag plugin;

    public LagCommand(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public AnnoyingPlugin getPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getPermission() {
        return "limitlesslag.command";
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        final String[] args = sender.args;

        // No arguments
        if (args.length == 0 && sender.checkPlayer()) {
            final Player player = sender.getPlayer();
            new AnnoyingMessage(plugin, "command.toggle")
                    .replace("%state%", plugin.toggle(player), AnnoyingMessage.DefaultReplaceType.BOOLEAN)
                    .replace("%player%", player.getName())
                    .send(sender);
            return;
        }

        // <player>
        if (args.length == 1) {
            final Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                new AnnoyingMessage(plugin, "error.invalid-argument")
                        .replace("%argument%", args[0])
                        .send(sender);
                return;
            }
            new AnnoyingMessage(plugin, "command.toggle")
                    .replace("%state%", plugin.toggle(player), AnnoyingMessage.DefaultReplaceType.BOOLEAN)
                    .replace("%player%", player.getName())
                    .send(sender);
            return;
        }

        new AnnoyingMessage(plugin, "error.invalid-arguments").send(sender);
    }
}
