package jimbot.command;

import jimbot.exception.InvalidDateTimeException;

/**
 * Represents the different commands that Jimbot can recognize.
 * Provides a utility method to parse a string input into a Commands value.
 *
 * @author limjimin-nus
 */
public enum Commands {
    HI, BYE, DATE, DEADLINE, DELETE, EVENT, FIND, HELP, LIST, MARK, TODAY, TODO, UNKNOWN, UNMARK;

    /**
     * Convert a string input into the corresponding Commands enum value.
     * Handles case-insensitive command strings and recognizes date inputs.
     *
     * @param input User input string to be converted into a command.
     * @return Corresponding Commands enum value.
     * @throws InvalidDateTimeException If the input only contains a date but does not match the date pattern.
     */
    public static Commands fromString(String input) {
        if (input == null || input.isEmpty()) {
            return UNKNOWN;
        }

        String cmd = input.toLowerCase();

        // Check for date input
        if (cmd.contains("/")) {
            return DATE;
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
