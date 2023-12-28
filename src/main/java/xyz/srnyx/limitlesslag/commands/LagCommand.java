package xyz.srnyx.limitlesslag.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;
import xyz.srnyx.annoyingapi.message.DefaultReplaceType;
import xyz.srnyx.annoyingapi.utility.BukkitUtility;

import xyz.srnyx.limitlesslag.LimitlessLag;

import java.util.Set;


public class LagCommand implements AnnoyingCommand {
    @NotNull private final LimitlessLag plugin;

    public LagCommand(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
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
                    .replace("%state%", plugin.toggle(player), DefaultReplaceType.BOOLEAN)
                    .replace("%player%", player.getName())
                    .send(sender);
            return;
        }

        // <player>
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.invalidArgument(args[0]);
            return;
        }
        new AnnoyingMessage(plugin, "command.toggle")
                .replace("%state%", plugin.toggle(player), DefaultReplaceType.BOOLEAN)
                .replace("%player%", player.getName())
                .send(sender);
    }

    @Override
    public Set<String> onTabComplete(@NotNull AnnoyingSender sender) {
        return sender.args.length == 1 ? BukkitUtility.getOnlinePlayerNames() : null;
    }
}
