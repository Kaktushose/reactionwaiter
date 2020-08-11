package de.kaktushose.discord.reactionwaiter;

/**
 * Some emotes that might be frequently used.
 *
 * @author Kaktushose
 * @version 1.0.0
 * @since 1.0.0
 *
 */

public enum EmoteType {


    /**
     * Thumbs Up <br>
     * code point: U+1F44D
     */
    THUMBSUP("\uD83D\uDC4D"),

    /**
     * Thumbs Down <br>
     * code point: U+1F44E
     */
    THUMBSDOWN("\uD83D\uDC4E"),

    /**
     * Star <br>
     * code point: U+2B50
     */
    STAR("\u2B50"),

    /**
     * Check Mark <br>
     * code point: U+2705
     */
    CHECKMARK("\u2705"),

    /**
     * Cross Mark <br>
     * code point: U+274C
     */
    CROSSMARK("\u274C"),

    /**
     * Digit 1 <br>
     * code point: U+0031
     */
    ONE("\u0031"),

    /**
     * Digit 2 <br>
     * code point: U+0032
     */
    TWO("\u0032"),

    /**
     * Digit 3 <br>
     * code point: U+0033
     */
    THREE("\u0033"),

    /**
     * Triangular flag on post <br>
     * code point: U+1F6A9
     */
    FLAG("\uD83D\uDEA9");

    /**
     * the escaped code point of the emote
     */
    public final String unicode;

    EmoteType(String unicode) {
        this.unicode = unicode;
    }

    /**
     * Returns the suitable emote for the given number. Only supports Integers in range from 0 to 2, inclusive.
     *
     * @param i the Integer that will be represented as an emote
     * @return the EmoteType representing the number
     */

    public static EmoteType getNumber(int i) {
        switch (i) {
            case 0:
                return ONE;
            case 1:
                return TWO;
            case 2:
                return THREE;
            default:
                return null;
        }
    }

}

