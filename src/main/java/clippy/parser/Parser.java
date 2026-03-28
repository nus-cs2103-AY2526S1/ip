package clippy.parser;

import clippy.ClippyException;
import clippy.command.AddDeadlineCommand;
import clippy.command.AddEventCommand;
import clippy.command.AddTodoCommand;
import clippy.command.Command;
import clippy.command.DeleteCommand;
import clippy.command.ExitCommand;
import clippy.command.FindCommand;
import clippy.command.HelpCommand;
import clippy.command.ListCommand;
import clippy.command.MarkCommand;
import clippy.command.UnmarkCommand;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The Command object corresponding to the user input.
     * @throws ClippyException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String input) throws ClippyException {
        if (input == null || input.trim().isEmpty()) {
            throw new ClippyException("Input cannot be empty. Please enter a command.");
        }
        String[] parts = input.trim().split(" ", 2);
        String cmd = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1] : "";

        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new AddTodoCommand(arg);
        case "deadline":
            return new AddDeadlineCommand(arg);
        case "event":
            return new AddEventCommand(arg);
        case "mark":
            return new MarkCommand(parseIndex(arg));
        case "unmark":
            return new UnmarkCommand(parseIndex(arg));
        case "delete":
            return new DeleteCommand(parseIndex(arg));
        case "find":
            return new FindCommand(arg);
        case "help":
            return new HelpCommand();
        default:
            throw new ClippyException("Sorry, I don't understand that command.");
        }
    }

    /**
     * Parses the index from the argument string.
     *
     * @param arg The argument string containing the index.
     * @return The parsed index as an integer.
     * @throws ClippyException If the index is invalid or cannot be parsed.
     */
    private static int parseIndex(String arg) throws ClippyException {
        if (arg == null || arg.trim().isEmpty()) {
            throw new ClippyException("☹ OOPS!!! The index of a task cannot be empty.");
        }
        try {
            int idx = Integer.parseInt(arg.trim());
            if (idx < 1) {
                throw new NumberFormatException();
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new ClippyException("☹ OOPS!!! The task index must be a valid positive integer.");
        }
    }
}
