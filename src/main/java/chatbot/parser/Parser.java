package chatbot.parser;

import chatbot.command.AddCommand;
import chatbot.command.Command;
import chatbot.command.DeleteCommand;
import chatbot.command.ExitCommand;
import chatbot.command.FindCommand;
import chatbot.command.HelpCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.ResetCommand;
import chatbot.command.SaveCommand;
import chatbot.exception.EmptyArgumentException;
import chatbot.exception.InvalidArgumentException;
import chatbot.exception.InvalidCommandException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Todo;

/**
 * Parses user input strings into corresponding Command objects.
 * The parser handles commands such as todo, deadline, event, delete, mark, unmark, save, list, and bye.
 */
public class Parser {

    /**
     * Enumeration of all supported chatbot commands.
     */
    public enum Commands {
        LIST,
        BYE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        HELP,
        RESET,
        SAVE;
    }

    /**
     * Parses a full command string from the user and returns a corresponding Command object.
     *
     * @param fullCommand the complete input string from the user
     * @return a Command object corresponding to the input
     * @throws InvalidCommandException if the command is unrecognized
     * @throws EmptyArgumentException if arguments are missing
     */
    public static Command parse(String fullCommand)
            throws EmptyArgumentException, InvalidCommandException {

        if (fullCommand == null || fullCommand.isBlank()) {
            throw new EmptyArgumentException("Please enter a command.");
        }

        String[] parts = fullCommand.trim().split("\\s+", 2);
        String token = parts[0];
        String args = (parts.length > 1) ? parts[1].trim() : "";

        Commands command = parseCommand(token, fullCommand);

        switch (command) {
        case TODO:
            requireArgs(args, "The description of a todo cannot be empty.");
            return new AddCommand(new Todo(args));

        case DEADLINE:
            requireArgs(args, "The description of a deadline cannot be empty.");
            return new AddCommand(Deadline.parse(args));

        case EVENT:
            requireArgs(args, "The description of an event cannot be empty.");
            return new AddCommand(Event.parse(args));

        case DELETE:
            return new DeleteCommand(indexFrom(args, "Provide the index of the task to delete."));

        case MARK:
            return new MarkCommand(indexFrom(args, "Provide the index of the task to mark."), true);

        case UNMARK:
            return new MarkCommand(indexFrom(args, "Provide the index of the task to unmark."), false);

        case SAVE:
            return new SaveCommand();

        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case FIND:
            requireArgs(args, "Provide a keyword to find.");
            return new FindCommand(args);

        case HELP:
            return new HelpCommand();

        case RESET:
            return new ResetCommand();

        default:
            throw new InvalidCommandException(fullCommand);
        }
    }

    private static Commands parseCommand(String token, String fullCommand)
            throws InvalidCommandException {
        try {
            return Commands.valueOf(token.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("DEBUG unknown command token: " + token.toUpperCase());
            throw new InvalidCommandException(fullCommand);
        }
    }

    private static void requireArgs(String args, String message)
            throws EmptyArgumentException {
        if (args == null || args.isBlank()) {
            throw new EmptyArgumentException(message);
        }
    }

    /** Parses a 1-based index string to a 0-based index, with basic validation. */
    private static int indexFrom(String args, String emptyMessage)
            throws EmptyArgumentException {
        requireArgs(args, emptyMessage);
        int idx0 = Integer.parseInt(args) - 1;
        if (idx0 < 0) {
            throw new InvalidArgumentException("Index must be >= 1");
        }
        return idx0;
    }
}
