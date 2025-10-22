/**
 * Parses raw user input into command words and arguments.
 */
package larry.parser;

import java.util.Map;
import java.util.Objects;

public class Parser {

    private static final Map<String, String> ALIASES = Map.ofEntries(
            Map.entry("t", "todo"),
            Map.entry("dl", "deadline"),
            Map.entry("ev", "event"),
            Map.entry("ls", "list"),
            Map.entry("mk", "mark"),
            Map.entry("um", "unmark"),
            Map.entry("del", "delete"),
            Map.entry("f", "find"),
            Map.entry("q", "bye"),
            Map.entry("h", "help")
    );

    /** Returns the lower-cased command word at the start of {@code input}. */
    public static String commandWord(String input) {
        Objects.requireNonNull(input, "input must not be null");
        String trimmed = input.trim();
        int sp = trimmed.indexOf(' ');
        String head = (sp == -1) ? trimmed.toLowerCase() : trimmed.substring(0, sp).toLowerCase();
        return ALIASES.getOrDefault(head, head);
    }

    /** Returns the remainder of {@code input} after the first word actually typed. */
    public static String argTail(String input, String cmd) {
        Objects.requireNonNull(input, "input must not be null");
        String trimmed = input.trim();
        int sp = trimmed.indexOf(' ');
        if (sp == -1) {
            return "";
        }
        return trimmed.substring(sp + 1).trim();
    }

    /** Parses a 1-based index; returns -1 if invalid. */
    public static int parseIndex(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
