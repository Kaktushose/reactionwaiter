package de.kaktushose.discord.reactionwaiter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is a sub class of {@code ListenerAdapter} from JDA.
 * It checks all incoming {@code GuildMessageReactionAddEvent}s if they matches one of the active {@link de.kaktushose.discord.reactionwaiter.ReactionWaiter}s <br>
 * Make sure to activate the listener before using this library. Otherwise no {@code GuildMessageReactionAddEvent} will be tracked.
 *
 * @author Kaktushose
 * @version 1.1.0
 * @since 1.0.0
 */
public class ReactionListener extends ListenerAdapter {


    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final static List<ReactionWaiter> waiters = new CopyOnWriteArrayList<>();
    private static boolean autoRemove = true;
    private static boolean removeReactions;
    private static long delay = 5;
    private static TimeUnit timeUnit = TimeUnit.MINUTES;

    /**
     * Adds this listener to the active listeners of the JDA. You have to call this method before using the library.
     *
     * @param jda he JDA with which the listener will be registered
     */
    public static void startListening(@Nonnull JDA jda) {
        jda.addEventListener(ReactionListener.class);
    }

    /**
     * Removes this listener from the active listeners of the JDA. This method has no real use case, but is available for the sake of completeness.
     *
     * @param jda the JDA where the listener will be removed
     */
    public static void stopListening(@Nonnull JDA jda) {
        jda.removeEventListener(ReactionListener.class);
    }

    /**
     * Set this to {@code true} to automatically remove waiters after a given set of time. Default this will happen after
     * five minutes and will also clear all reactions.
     *
     * @param autoRemove {@code true} if waiters should be removed automatically
     */
    public static void setAutoRemove(boolean autoRemove) {
        ReactionListener.autoRemove = autoRemove;
    }

    /**
     * Set the time interval after which waiters will be removed automatically. The default value is five minutes.
     *
     * @param delay    the time from now to deactivate the waiter
     * @param timeUnit the time unit of the delay parameter
     */
    public static void setAutoRemoveDelay(long delay, TimeUnit timeUnit) {
        ReactionListener.delay = delay;
        ReactionListener.timeUnit = timeUnit;
    }

    /**
     * Set this to {@code true} if all reactions of a message should be removed when the waiter gets unregistered.
     *
     * @param removeReactions {@code true} if all reactions of a message should be removed
     */
    public void setRemoveReactions(boolean removeReactions) {
        ReactionListener.removeReactions = removeReactions;
    }

    static void addReactionWaiter(ReactionWaiter waiter) {
        waiters.add(waiter);
        if (autoRemove) {
            removeReactionWaiter(waiter, delay, timeUnit);
        }
    }

    static void removeReactionWaiter(ReactionWaiter waiter) {
        if (!waiters.remove(waiter)) {
            return;
        }
        if (removeReactions) {
            waiter.getMessage().clearReactions().queue();
        }
    }

    static void removeReactionWaiter(ReactionWaiter waiter, long delay, TimeUnit timeUnit) {
        scheduler.schedule(() -> removeReactionWaiter(waiter), delay, timeUnit);
    }

    @Override
    @SubscribeEvent
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if (event.getUser().isBot()) {
            return;
        }
        waiters.forEach(waiter -> waiter.getEmotes().forEach(emote -> {
            if (!emote.equals(event.getReactionEmote().getName())) {
                return;
            }

            if (waiter.getMessage() != null) {
                if (waiter.getMessage().getIdLong() != event.getMessageIdLong()) {
                    return;
                }
            }

            if (waiter.getMember() != null) {
                if (!waiter.getMember().equals(event.getMember())) {
                    return;
                }
            }

            waiter.getConsumer().accept(new ReactionEvent(event, emote));
        }));
    }
}

