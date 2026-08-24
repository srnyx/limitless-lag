package xyz.srnyx.limitlesslag.commands;

import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.limitlesslag.LimitlessLag;


public class ReloadCommand extends xyz.srnyx.limitlesslag.commands.generated.LagreloadCmdGen {
    public ReloadCommand(@NotNull LimitlessLag plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        plugin.reloadPlugin();
        plugin.getMessages().get().command.reload.newMessage().send(sender);
    }
}
