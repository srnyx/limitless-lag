package xyz.srnyx.limitlesslag.commands;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;
import xyz.srnyx.annoyingapi.message.DefaultReplaceType;
import xyz.srnyx.annoyingapi.utility.BukkitUtility;

import xyz.srnyx.limitlesslag.LimitlessLag;

import java.util.Set;


public class LagCommand extends AnnoyingCommand {
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
        // No arguments (toggle self)
        if (sender.args.length == 0) {
            if (!sender.checkPlayer()) return;
            final Player player = sender.getPlayer();
            new AnnoyingMessage(plugin, "command.toggle")
                    .replace("%state%", plugin.toggle(player), DefaultReplaceType.BOOLEAN)
                    .replace("%player%", player.getName())
                    .send(sender);
            return;
        }

        // <player>
        sender.getArgumentOptionalFlat(0, BukkitUtility::getOfflinePlayer).ifPresent(player -> new AnnoyingMessage(plugin, "command.toggle")
                .replace("%state%", plugin.toggle(player), DefaultReplaceType.BOOLEAN)
                .replace("%player%", player.getName())
                .send(sender));
    }

    @Override
    public Set<String> onTabComplete(@NotNull AnnoyingSender sender) {
        return sender.args.length == 1 ? BukkitUtility.getOnlinePlayerNames() : null;
    }
}
