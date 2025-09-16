package tinkerton.util;

import tinkerton.command.*;
import tinkerton.core.TinkertonException;

/**
 * Parses user input commands and returns the corresponding Command object.
 */
public class Parser {
    /**
     * Parses the full command string and returns the appropriate Command object.
     *
     * @param fullCommand The full user input command string.
     * @return The corresponding Command object.
     * @throws TinkertonException If the command is unknown or invalid.
     */
    public static Command parse(String fullCommand) throws TinkertonException {
        String[] parts = fullCommand.split(" ");
        CommandType command = CommandType.command(parts[0]);

        switch (command) {
        case BYE:
            return new ByeCommand(fullCommand);

        case LIST:
            return new ListCommand(fullCommand);

        case SHOW:
            return new ShowCommand(fullCommand);

        case MARK:
            return new MarkCommand(fullCommand);

        case UNMARK:
            return new UnmarkCommand(fullCommand);

        case TODO:
            return new ToDoCommand(fullCommand);

        case DEADLINE:
            return new DeadlineCommand(fullCommand);

        case EVENT:
            return new EventCommand(fullCommand);

        case DELETE:
            return new DeleteCommand(fullCommand);

        case FIND:
            return new FindCommand(fullCommand);
        case UNKNOWN:
        default:
            throw new TinkertonException("Erm... What are you saying?");
        }
    }
}
