package itachi.command;

import itachi.ItachiException;

/**
 * Represents all valid commands that the Itachi bot can process.
 * Each enum constant corresponds to a command keyword the user can enter.
 */
public enum Commands {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    BYE,
    MARK,
    UNMARK,
    DELETE;

    /**
     * Converts a string input into the corresponding Commands enum constant.
     *
     * This method takes a user-entered string, converts it to lowercase,
     * and returns the respective Commands enum. If the input does not match
     * any of the defined commands, an {@link ItachiException} is thrown.
     *
     * @param input the command string entered by the user
     * @return the corresponding {@link Commands} enum constant
     * @throws ItachiException if the input does not match any valid command
     */
    public static Commands convertStringToEnum(String input) throws ItachiException {
        assert input != null : "Input command string should never be null";
        switch (input.toLowerCase()) {
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "list":
            return LIST;
        case "bye":
            return BYE;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "delete":
            return DELETE;
        default: throw new ItachiException("This is an invalid command!");
        }
    }
}
