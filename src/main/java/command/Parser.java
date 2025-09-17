package command;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

import misc.PepeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Parser class is in charge of parsing all user inputs.
 */
public class Parser {

    private enum CommandType {
        LIST,
        BYE,
        MARK,
        UNMARK,
        EVENT,
        TODO,
        DEADLINE,
        DELETE,
        FIND,
        SORT,
    }
    /**
     * Parses a user-input into a command.
     * @param input The user-input
     * @return A command matching the input
     * @throws PepeException if user-input was invalid or unrecognized.
     */
    public static Command parse(String input) throws PepeException {
        if (input == null || input.isEmpty()) {
            throw new PepeException("Empty command");
        }
        String[] tokens = input.split(" ");
        CommandType commandType = getCommandType(tokens[0]);
        String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
        try {
            return switch(commandType) {
            case LIST -> new DisplayListCommand();
            case BYE -> new ByeCommand();
            case MARK -> MarkCommand.fromInput(arguments);
            case UNMARK -> UnmarkCommand.fromInput(arguments);
            case EVENT -> new AddToListCommand(Event.fromInput(arguments));
            case TODO -> new AddToListCommand(Todo.fromInput(arguments));
            case DEADLINE -> new AddToListCommand(Deadline.fromInput(arguments));
            case DELETE -> DeleteCommand.fromInput(arguments);
            case FIND -> FindCommand.fromInput(arguments);
            case SORT -> new SortCommand();
            };
        } catch (DateTimeParseException e) {
            throw new PepeException("Error parsing datetime: " + e.getMessage());
        }
    }

    private static CommandType getCommandType(String input) throws PepeException {
        // I really don't think this is good!
        // I am just doing this for the tag for A-Enums.
        if (input == null || input.isEmpty()) {
            throw new PepeException("Empty command");
        }
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new PepeException("Unknown command: " + input);
        }
    }

}
