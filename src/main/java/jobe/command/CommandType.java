package jobe.command;

import jobe.exception.JobeException;

/**
 * Represents the types of commands recognised by Jobe.
 */
public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    FIND,
    DELETE;
    
    /**
     * Returns a command type which corresponds to the input string.
     *
     * @param input Input command when parsing user's input
     * @return Command type corresponding to user's input.
     * @throws JobeException if user's input does not match any valid command types.
     */
    public static CommandType stringToCommand(String input) throws JobeException {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JobeException("OOPS!!!! I'm Sorry, but I do not recognise the command.");
        }
    }
}
