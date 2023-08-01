package xyz.srnyx.limitlesslag.commands;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;

import xyz.srnyx.limitlesslag.LimitlessLag;


public class ReloadCommand implements AnnoyingCommand {
    @NotNull private final LimitlessLag plugin;

    public ReloadCommand(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getName() {
        return "lagreload";
    }

    @Override @NotNull
    public String getPermission() {
        return "limitlesslag.reload";
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        plugin.reloadPlugin();
        new AnnoyingMessage(plugin, "command.reload").send(sender);
    }
}
