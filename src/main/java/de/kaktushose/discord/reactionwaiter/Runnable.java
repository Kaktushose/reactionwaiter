package de.kaktushose.discord.reactionwaiter;

/**
 * The functional interface used in the {@link ReactionWaiter#onEvent(Runnable)} method of
 * {@link ReactionWaiter}.
 *
 * @author Kaktushose
 * @version 1.0.0
 * @since 1.0.0
 */

public interface Runnable {

    void run(ReactionEvent event);

}
