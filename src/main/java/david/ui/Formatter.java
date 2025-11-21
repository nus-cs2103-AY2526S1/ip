package david.ui;

/**
 * Standardizes the output message.
 */
public class Formatter {
    private static final String s = "_";
    public static final String NEWLINE = s.repeat(100);

    /**
     * Formats the output lines.
     *
     * @param output Printed string to be formatted.
     * @return Output with standardized indentation and lines.
     */
    public static String format(String output) {
        String msg = NEWLINE + "\n " + output + "\n" + NEWLINE + "\n";
        return msg.indent(4);
    }
}
