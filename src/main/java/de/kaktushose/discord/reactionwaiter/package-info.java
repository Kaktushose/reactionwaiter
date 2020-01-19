/**
 * Created and maintained by Kaktushose.<p>
 * License:
 * The MIT License (MIT)
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p>
 *
 * This framework is build around the class {@link de.kaktushose.discord.reactionwaiter.ReactionWaiter}.<br>
 * It is used to create special listeners that will wait for reactions to be added. In doing so they will follow the restrictions defined by the constructor used.<br>
 * {@link de.kaktushose.discord.reactionwaiter.ReactionEvent} is a sub class of {@code GuildMessageReactionAddEvent} from JDA
 * and provides some extra utilities.<br>
 * {@link de.kaktushose.discord.reactionwaiter.ReactionListener} implements the {@code onGuildMessageReactionAdd} method from JDA.
 * It reviews all incoming {@code GuildMessageReactionAddEvent}s.<br>
 * {@link de.kaktushose.discord.reactionwaiter.Runnable} is a functional interface used in the {@link de.kaktushose.discord.reactionwaiter.ReactionWaiter#onEvent(de.kaktushose.discord.reactionwaiter.Runnable)}
 * method of {@link de.kaktushose.discord.reactionwaiter.ReactionWaiter}.<br>
 * {@link de.kaktushose.discord.reactionwaiter.EmoteType} is an enum with some emotes that might be frequently used. It can be used instead of the {@code Emote} from JDA.
 *
*/
package de.kaktushose.discord.reactionwaiter;
