package de.kaktushose.discord.reactionwaiter;

/**
 * Some emotes that might be frequently used.
 *
 * @author Kaktushose
 * @version 2.0.0
 * @since 1.0.0
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
     * Digit 0 <br>
     * code point: U+0030
     */
    ZERO("\u0030"),

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
     * Digit 4 <br>
     * code point: U+0034
     */
    FOUR("\u0034"),

    /**
     * Digit 5 <br>
     * code point: U+0035
     */
    FIVE("\u0035"),

    /**
     * Digit 6 <br>
     * code point: U+0036
     */
    SIX("\u0036"),

    /**
     * Digit 7 <br>
     * code point: U+0037
     */
    SEVEN("\u0037"),

    /**
     * Digit 8 <br>
     * code point: U+0038
     */
    EIGHT("\u0038"),

    /**
     * Digit 9 <br>
     * code point: U+0039
     */
    NINE("\u0039"),

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
     * Returns the suitable emote for the given number.
     *
     * @param i the Integer that will be represented as an emote, supports numbers von 0 to 9
     * @return the EmoteType representing the number
     */

    public static EmoteType getNumber(int i) {
        switch (i) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
            case 9:
                return NINE;
            default:
                return null;
        }
    }

}

