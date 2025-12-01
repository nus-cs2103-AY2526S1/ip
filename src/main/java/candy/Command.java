package candy;

import exceptions.InvalidInputException;

/**
 * Represents the list of valid commands
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    EDIT("edit");

    private String keyword;

    /**
     * Initialises the command keyword
     *
     * @param keyword the keyword user types
     */
    Command(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the keyword from text user inputs
     *
     * @param input the string of text user input
     */
    public static Command parseCommand(String input) {
        for (Command command : values()) {
            //check if there is a valid command
            if (input.startsWith(command.keyword)) {
                return command;
            }
        }
        throw new InvalidInputException();
    }
}
