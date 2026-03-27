package marvin.ui;

/**
 * Contains related constants and methods to use for
 * outputting colored terminal output.
 */
public enum Color {
    RED("\u001B[31m"),
    YELLOW("\u001B[33m");

    private static final String RESET_TEXT_COLOR_SEQUENCE = "\u001B[0m";

    public final String sequence;

    Color(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Returns formatted, colored, text that is ready to be printed in a terminal.
     *
     * @param text  The text that is intended to be colored.
     * @param color The color that the text is desired to be printed in.
     */
    public static String getColoredTextString(String text, Color color) {
        return color.sequence + text + RESET_TEXT_COLOR_SEQUENCE;
    }
}
