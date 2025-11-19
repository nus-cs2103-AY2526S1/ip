package goober.helper;

import java.util.List;

/**
 * Utility class to help format Strings.
 */
public class Formatter {
    /**
     * Converts list to string while adding number and new lines for each element.
     */
    public static String toNumberList(List<?> ls) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ls.size(); i++) {
            sb.append((i + 1)).append(". ").append(ls.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Converts list to string while adding number and new lines for each element.
     * Add a message with a new line at the start.
     */
    public static String toNumberList(List<?> ls, String msg) {
        StringBuilder sb = new StringBuilder(msg).append("\n");
        for (int i = 0; i < ls.size(); i++) {
            sb.append((i + 1)).append(". ").append(ls.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
