package command;

import amogus.AmogusException;

/* ChatGPT suggested the use of enum rather than
 * using magic strings.
 */

/**
 * Represents the type of command to be executed.
 */
public enum CommandType {
    LIST, MARK, UNMARK, DELETE, FIND, TAG, TODO, DEADLINE, EVENT, BYE;

    /**
     * Takes in the user input for the command, returning the
     * command in string to be executed.
     * @param input command from user
     * @return command type in string
     * @throws AmogusException for any unknown commands.
     */
    public static CommandType fromString(String input) throws AmogusException {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AmogusException("Unknown command: " + input);
        }
    }
}
