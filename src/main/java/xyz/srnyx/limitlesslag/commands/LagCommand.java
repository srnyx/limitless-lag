package xyz.srnyx.limitlesslag.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.DefaultReplaceType;
import xyz.srnyx.annoyingapi.utility.BukkitUtility;
import xyz.srnyx.limitlesslag.LimitlessLag;

import java.util.List;


public class LagCommand extends xyz.srnyx.limitlesslag.commands.generated.LagCmdGen {
    public LagCommand(@NotNull LimitlessLag plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        // No arguments (toggle self)
        if (sender.args.length == 0) {
            if (!sender.checkPlayer()) return;
            final Player player = sender.getPlayer();
            plugin.getMessages().get().command.toggle.newMessage()
                    .replace("%state%", plugin.toggle(player, null), DefaultReplaceType.BOOLEAN)
                    .replace("%player%", player.getName())
                    .send(sender);
            return;
        }

        // <player>
        final List<OfflinePlayer> players = sender
                .getSelector(0, OfflinePlayer.class)
                .orElseFlatSingle(BukkitUtility::getOfflinePlayer);
        if (players == null) return;

        // <on|off>
        final Boolean state = sender.getArgumentOptional(1)
                .map(arg -> arg.equalsIgnoreCase("on"))
                .orElse(null);

        // Toggle for players
        for (final OfflinePlayer player : players) {
            plugin.getMessages().get().command.toggle.newMessage()
                    .replace("%state%", plugin.toggle(player, state), DefaultReplaceType.BOOLEAN)
                    .replace("%player%", player.getName())
                    .send(sender);
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull AnnoyingSender sender) {
        if (sender.args.length == 1) return sender.withSelectorKeys(BukkitUtility.getOnlinePlayerNames(), OfflinePlayer.class);
        if (sender.args.length == 2) return List.of("on", "off");
        return null;
    }
}
