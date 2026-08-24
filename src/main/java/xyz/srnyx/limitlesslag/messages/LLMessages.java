package xyz.srnyx.limitlesslag.messages;

import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Include;
import eu.okaeri.configs.annotation.IncludePosition;
import eu.okaeri.validator.annotation.NotNull;
import xyz.srnyx.annoyingapi.file.okaeri.SubConfig;
import xyz.srnyx.annoyingapi.message.AnnoyingMessages;
import xyz.srnyx.annoyingapi.message.json.message.JsonChatMessage;
import xyz.srnyx.limitlesslag.LimitlessLag;


@Include(value = AnnoyingMessages.class, position = IncludePosition.BEFORE)
public class LLMessages extends AnnoyingMessages {
    public LLMessages(@org.jetbrains.annotations.NotNull LimitlessLag plugin) {
        super(plugin);
    }

    @Comment
    @NotNull public Command command = new Command(this);

    public static class Command extends SubConfig<LLMessages, LLMessages> {
        public Command(@org.jetbrains.annotations.NotNull LLMessages defaultsParent) {
            super(defaultsParent);
        }

        @NotNull public JsonChatMessage reload = getRoot().defaultMessage("%prefix%Plugin reloaded!@@%p%%command%@@%command%");

        @Comment
        @Comment("Placeholders: %state==boolean%, %player%")
        @NotNull public JsonChatMessage toggle = getRoot().defaultMessage("%prefix%You have %s%%state==enabled//disabled%%p% lag for %s%%player%@@%p%%command%@@%command%");
    }
}
