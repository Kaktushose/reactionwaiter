package de.kaktushose.discord.reactionwaiter;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.requests.RestAction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Represents a reaction event.
 * This class is similar to {@code GuildMessageReactionAddEvent}, but it provides some extra utilities to work with.
 *
 * @author Kaktushose
 * @version 2.0.0
 * @since 1.0.0
 */

public class ReactionEvent extends GuildMessageReactionAddEvent {

    private final Member executor;
    private final String emote;

    ReactionEvent(GuildMessageReactionAddEvent event, String emote) {
        super(event.getJDA(), event.getResponseNumber(), event.getMember(), event.getReaction());
        this.emote = emote;
        this.executor = event.getMember();

    }

    /**
     * Returns the codepoint of the emote the reaction event was triggered with.
     *
     * @return the codepoint of the emote
     */
    public String getEmote() {
        return emote;
    }

    /**
     * Sends a response to the channel where the reaction event was triggered.
     *
     * @param message the message to send
     */
    public void reply(@Nonnull String message) {
        getChannel().sendMessage(message).queue();
    }

    /**
     * Sends a formatted message using the specified format string and arguments to the channel where the reaction event was triggered.
     *
     * @param format the message to send
     * @param args   Arguments referenced by the format specifiers in the format string. If there are more arguments than
     *               format specifiers, the extra arguments are ignored. The number of arguments is variable and may be
     *               zero.
     * @throws java.util.IllegalFormatException If a format string contains an illegal syntax, a format specifier that
     *                                          is incompatible with the given arguments, insufficient arguments given
     *                                          the format string, or other illegal conditions.
     */
    public void reply(@Nonnull String format, @Nullable Object... args) {
        reply(String.format(format, args));
    }

    /**
     * Sends a response to the channel where the reaction event was triggered. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param message the message to send
     * @param success the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull String message, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(message).queue(success);
    }

    /**
     * Sends a response to the channel where the reaction event was triggered.
     *
     * @param message the {@code Message} to send
     */
    public void reply(@Nonnull Message message) {
        getChannel().sendMessage(message).queue();
    }

    /**
     * Sends a response to the channel where the reaction event was triggered. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param message the {@code Message} to send
     * @param success the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull Message message, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(message).queue(success);
    }

    /**
     * Sends a response to the channel where the reaction event was triggered.
     *
     * @param messageBuilder the {@code MessageBuilder} to send
     */
    public void reply(@Nonnull MessageBuilder messageBuilder) {
        getChannel().sendMessage(messageBuilder.build()).queue();
    }

    /**
     * Sends a response to the channel where the reaction event was triggered. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param messageBuilder the {@code MessageBuilder} to send
     * @param success        the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull MessageBuilder messageBuilder, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(messageBuilder.build()).queue(success);
    }

    /**
     * Sends a response to the channel where the reaction event was triggered.
     *
     * @param embedBuilder the {@code EmbedBuilder} to send
     */
    public void reply(@Nonnull EmbedBuilder embedBuilder) {
        getChannel().sendMessage(embedBuilder.build()).queue();
    }

    /**
     * Sends a message to the TextChannel where the command was called. This method also allows to access the JDA RestAction
     * consumer.
     *
     * @param embedBuilder the {@code EmbedBuilder} to send
     * @param success      the JDA RestAction success consumer
     * @see <a href="https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/RestAction.html">JDA RestAction Documentation</a>
     */
    public void reply(@Nonnull EmbedBuilder embedBuilder, @Nullable Consumer<Message> success) {
        getChannel().sendMessage(embedBuilder.build()).queue(success);
    }

    /**
     * Sends a direct message to the user who triggered the reaction event.
     *
     * @param message the message to send
     */
    public void sendPrivateMessage(@Nonnull String message) {
        executor.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(message).queue());
    }

    /**
     * Sends a direct message to the user who triggered the reaction event.
     * This method might be useful in combination with the MessageBuilder.
     *
     * @param message the {@code Message} to send send
     */
    public void sendPrivateMessage(@Nonnull Message message) {
        executor.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(message).queue());
    }

    /**
     * Sends an Embed via direct message to the user who triggered the reaction event.
     *
     * @param messageBuilder the {@code MessageBuilder} that will be send
     */
    public void sendPrivateMessage(@Nonnull MessageBuilder messageBuilder) {
        executor.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(messageBuilder.build()).queue());
    }

    /**
     * Sends an Embed via direct message to the user who triggered the reaction event.
     *
     * @param embedBuilder the {@code EmbedBuilder} to send
     */
    public void sendPrivateMessage(@Nonnull EmbedBuilder embedBuilder) {
        executor.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(embedBuilder.build()).queue());
    }

}
