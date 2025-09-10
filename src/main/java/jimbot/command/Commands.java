package jimbot.command;

import java.util.regex.Pattern;

import jimbot.exception.InvalidDateTimeException;

public enum Commands {
    HI, BYE, DATE, DEADLINE, DELETE, EVENT, FIND, HELP, LIST, MARK, TODAY, TODO, UNKNOWN, UNMARK;

    // Regex to match dd/MM/yyyy
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

    /**
     * Convert a string into a Commands enum value.
     * Handles case-insensitivity and unknown inputs.
     */
    public static Commands fromString(String input) throws InvalidDateTimeException {
        if (input == null || input.isEmpty()) {
            return UNKNOWN;
        }

        String cmd = input.toLowerCase();

        // Check for date input
        if (cmd.contains("/")) {
            if (DATE_PATTERN.matcher(input).matches()) {
                return DATE;
            } else {
                throw new InvalidDateTimeException();
            }
        }

        return switch (cmd) {
        case "hi" -> HI;
        case "bye", "goodbye" -> BYE;
        case "deadline" -> DEADLINE;
        case "delete" -> DELETE;
        case "event" -> EVENT;
        case "find" -> FIND;
        case "help" -> HELP;
        case "list" -> LIST;
        case "mark" -> MARK;
        case "today" -> TODAY;
        case "todo" -> TODO;
        case "unmark" -> UNMARK;
        default -> UNKNOWN;
        };
    }
}
