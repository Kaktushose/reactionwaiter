package de.kaktushose.discord.reactionwaiter;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * The ReactionWaiter waits for reactions being added to messages.
 * There are different variants possible. You can observe a specific message, but also all messages in general.
 * Further you can also only accept specific members.
 *
 * @author Kaktushose
 * @version 2.0.0
 * @since 1.0.0
 */

public class ReactionWaiter {

    private final Set<String> emotes;
    private final Message message;
    private final Member member;
    private Consumer<ReactionEvent> consumer;

    /**
     * This constructor creates a reaction waiter that will accept the given emotes, all messages and all users
     *
     * @param emotes the emotes that will be listened for
     */
    public ReactionWaiter(@Nonnull String... emotes) {
        this.emotes = new HashSet<>(Arrays.asList(emotes));
        this.message = null;
        this.member = null;
    }

    /**
     * This constructor creates a reaction waiter that will accept the given emotes and all users but is limited to a specific message.
     *
     * @param message the message that will be monitored
     * @param emotes  the emotes that will be listened for
     */
    public ReactionWaiter(@Nullable Message message, @Nonnull String... emotes) {
        this.emotes = new HashSet<>(Arrays.asList(emotes));
        this.message = message;
        this.member = null;
    }

    /**
     * This constructor creates a reaction waiter that will accept the given emotes and is limited to a specific message and user.
     *
     * @param member  the member that will be accepted
     * @param message the message that will be monitored
     * @param emotes  the emotes that will be listened for
     */
    public ReactionWaiter(@Nullable Message message, @Nullable Member member, @Nonnull String... emotes) {
        this.emotes = new HashSet<>(Arrays.asList(emotes));
        this.message = message;
        this.member = member;
    }

    /**
     * This method gets invoked when a GuildMessageReactionAddEvent matches the specifications given by the used constructor.
     * This method will also activate the waiter.
     *
     * @param consumer the callback that will be called when a {@link ReactionEvent} got triggered
     * @return the current instance to use fluent interface
     */
    public ReactionWaiter onEvent(@Nonnull Consumer<ReactionEvent> consumer) {
        ReactionListener.addReactionWaiter(this);
        this.consumer = consumer;
        return this;
    }

    /**
     * This method gets invoked when a GuildMessageReactionAddEvent matches the specifications given by the used constructor.
     * Additionally deactivates the reaction waiter after a given set of time. This method will also activate the waiter.
     *
     * @param consumer the callback that will be called when a {@link ReactionEvent} got triggered
     * @param delay    the time from now to deactivate the waiter
     * @param timeUnit the time unit of the delay parameter
     * @return the current instance to use fluent interface
     */
    public ReactionWaiter onEvent(@Nonnull Consumer<ReactionEvent> consumer, long delay, @Nonnull TimeUnit timeUnit) {
        ReactionListener.addReactionWaiter(this);
        ReactionListener.removeReactionWaiter(this, delay, timeUnit);
        this.consumer = consumer;
        return this;
    }

    /**
     * Deactivates the reaction waiter.
     *
     * @param removeReactions {@code true} if this method should also remove all reactions from the message
     * @return the current instance to use fluent interface
     */
    public ReactionWaiter stopWaiting(boolean removeReactions) {
        ReactionListener.removeReactionWaiter(this);
        return this;
    }

    /**
     * Deactivates the reaction waiter after a given set of time.
     *
     * @param delay    the time from now to deactivate the waiter
     * @param timeUnit the time unit of the delay parameter
     * @return the current instance to use fluent interface
     */
    public ReactionWaiter stopWaitingAfter(long delay, @Nonnull TimeUnit timeUnit) {
        ReactionListener.removeReactionWaiter(this, delay, timeUnit);
        return this;
    }

    /**
     * Get a Set containing the emotes that this waiter listens for.
     *
     * @return a Set containing the emotes that will be listened to
     */
    public Set<String> getEmotes() {
        return emotes;
    }

    /**
     * Get the {@code Message} that this waiter listens for. Might be null.
     *
     * @return the {@code Message} that will be listened to
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Get the {@code Member} that this waiter listens for. Might be null.
     *
     * @return the {@code Member} that will be listened to
     */
    public Member getMember() {
        return member;
    }

    /**
     * Get the {@link Consumer} that will be called when a {@link ReactionEvent} got triggered.
     *
     * @return the callback that will be called when a {@link ReactionEvent} got triggered
     */
    public Consumer<ReactionEvent> getConsumer() {
        return consumer;
    }
}
