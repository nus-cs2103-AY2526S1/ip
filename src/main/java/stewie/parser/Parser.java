package stewie.parser;

import stewie.command.ByeCommand;
import stewie.command.Command;
import stewie.command.CommandType;
import stewie.command.DeadlineCommand;
import stewie.command.DeleteCommand;
import stewie.command.EventCommand;
import stewie.command.FindCommand;
import stewie.command.ListCommand;
import stewie.command.MarkCommand;
import stewie.command.ToDoCommand;
import stewie.command.UnmarkCommand;
import stewie.command.UpdateCommand;
import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.exceptions.UnknownCommandException;
import stewie.util.Helper;

/**
 * Parses user input into executable commands.
 */
public class Parser {
    /**
     * Parses the given input string into a Command object.
     *
     * @param input The user input string to parse.
     * @return The corresponding Command object.
     * @throws CommandException If the input is invalid or command is unknown.
     */
    public static Command parseCommand(String input) throws CommandException {
        if (input == null || input.isBlank()) {
            throw new InvalidCommandException("Type something!");
        }

        String[] cmdNArgs = input.trim().split("\\s+", 2);
        CommandType commandType = Helper.parseCommandType(cmdNArgs[0]);
        String args = cmdNArgs.length > 1 ? cmdNArgs[1] : "";

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(args);
        case UNMARK:
            return new UnmarkCommand(args);
        case TODO:
            return new ToDoCommand(args);
        case DEADLINE:
            return new DeadlineCommand(args);
        case EVENT:
            return new EventCommand(args);
        case DELETE:
            return new DeleteCommand(args);
        case FIND:
            return new FindCommand(args);
        case UPDATE:
            return new UpdateCommand(args);
        case BYE:
            return new ByeCommand();
        default:
            throw new UnknownCommandException("\tlist, mark <i>, unmark <i>, todo <desc>, deadline <desc> /by <time>,"
                 + "\n\tevent <desc> /from <start> /to <end>, delete <i>, find <desc>, update <i> <task> <desc>, bye");
        }
    }
}
