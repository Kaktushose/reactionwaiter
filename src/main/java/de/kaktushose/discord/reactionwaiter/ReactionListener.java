package de.kaktushose.discord.reactionwaiter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is a sub class of {@code ListenerAdapter} from JDA.
 * It checks all incoming {@code GuildMessageReactionAddEvent}s if they matches one of the active {@link de.kaktushose.discord.reactionwaiter.ReactionWaiter}s <br>
 * Make sure to activate the listener before using this library. Otherwise no {@code GuildMessageReactionAddEvent} will be tracked.
 *
 * @author Kaktushose
 * @version 1.0.0
 * @since 1.0.0
 */
public class ReactionListener extends ListenerAdapter {

    private static List<ReactionWaiter> waiters = new CopyOnWriteArrayList<>();

    /**
     * Adds this listener to the active listeners of the JDA. You have to call this method before using the library.
     *
     * @param jda he JDA with which the listener will be registered
     */
    public static void startListening(JDA jda) {
        jda.addEventListener(ReactionListener.class);
    }

    /**
     * Removes this listener from the active listeners of the JDA. This method has no real use case, but is available for the sake of completeness.
     *
     * @param jda the JDA where the listener will be removed
     */
    public static void stopListening(JDA jda) {
        jda.removeEventListener(ReactionListener.class);
    }

    static void addReactionWaiter(ReactionWaiter waiter) {
        waiters.add(waiter);
    }

    static void removeReactionWaiter(ReactionWaiter waiter) {
        waiters.remove(waiter);
    }

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if (event.getUser().isBot()) return;
        waiters.forEach(waiter -> waiter.getEmotes().forEach(emote -> {
            if (!emote.equals(event.getReactionEmote().getName())) return;
            if (!(waiter.getMessageId() == 0 || waiter.getMessageId() == event.getMessageIdLong())) return;
            if (!(event.getMember().getIdLong() == 0) || event.getMember().getIdLong() == waiter.getMemberId())
            waiter.called(new ReactionEvent(event, emote));
        }));
    }
}

