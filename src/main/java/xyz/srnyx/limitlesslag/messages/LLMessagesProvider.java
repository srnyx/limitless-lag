package xyz.srnyx.limitlesslag.messages;

import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.file.okaeri.ConfigBuilder;
import xyz.srnyx.annoyingapi.message.MessagesProvider;
import xyz.srnyx.limitlesslag.LimitlessLag;


public class LLMessagesProvider extends MessagesProvider {
    @NotNull private final LimitlessLag plugin;

    public LLMessagesProvider(@NotNull LimitlessLag plugin) {
        this.plugin = plugin;

        defaults
                .prefix("&6&lLAG &8&l| &e")
                .p("&e")
                .s("&6");
    }

    @Override @NotNull
    public LimitlessLag getAnnoyingPlugin() {
        return plugin;
    }

    @Override
    public void mutateBuilder(@NotNull ConfigBuilder builder) {
        builder.config(new LLMessages(plugin));
    }

    @Override @NotNull
    public LLMessages get() {
        return (LLMessages) messages;
    }
}
