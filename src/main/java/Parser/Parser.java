package Parser;

import Command.Command;
import Command.ByeCommand;
import Command.DeadlineCommand;
import Command.DeleteCommand;
import Command.EventCommand;
import Command.FindCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.SortCommand;
import Command.ToDoCommand;
import Command.UnmarkCommand;

import JohnException.JohnException;

/**
 * Parses all inputs from user into the application
 * and calls the respective commands to handle them.
 */
public class Parser {
    /**
     * Parses raw user input and creates an appropriate command.
     *
     * @param input The entire line typed by the user.
     * @return Command object ready to execute.
     * @throws JohnException If the command is unknown or arguments are invalid.
     */
    public static Command parse(String input) throws JohnException {
        String trimmed = normalize(input);
        String command = readKeyword(trimmed);
        String args = readArgs(trimmed);

        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            if (args.isBlank()) {
                throw new JohnException("Usage: todo <description>");
            }
            return new ToDoCommand(args);
        case "deadline":
            String[] p = args.split("/by ", 2);
            if (p.length < 2) {
                throw new JohnException("Usage: deadline <desc> /by <yyyy-MM-dd>");
            }
            return new DeadlineCommand(args);
        case "event":
            String[] i = args.split("/from ", 2);
            String[] j = args.split("/to", 2);
            if (i.length < 2 || j.length < 2) {
                throw new JohnException("Usage: event <desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
            }
            return new EventCommand(args);
        case "mark":
            return new MarkCommand(parseIndex(args));
        case "unmark":
            return new UnmarkCommand(parseIndex(args));
        case "delete":
            return new DeleteCommand(parseIndex(args));
        case "find": {
            if (args.isBlank()) {
                throw new JohnException("Usage: find <keyword>");
            }
            return new FindCommand(args);
        }
        case "sort": {
            return new SortCommand();
        }
        case "bye":
            return new ByeCommand();
        default:
            throw new JohnException("Unknown command: " + command);
        }
    }

    /**
     * Ensures that input string is valid and trims it.
     *
     * @param input String containing command followed by relevant description.
     * @return Trimmed input string.
     */
    private static String normalize(String input) throws JohnException {
        if (input.trim().isEmpty()) {
            throw new JohnException("Input cannot be empty.");
        }
        assert !input.trim().isEmpty(): "Input cannot be empty"; // For GUI
        return input.trim();
    }

    /**
     * Retrieves command keyword from input.
     *
     * @param trimmed Input from user.
     * @return Command keyword from input.
     */
    private static String readKeyword(String trimmed) {
        int sp = trimmed.indexOf(' ');
        return (sp < 0) ? trimmed : trimmed.substring(0, sp);
    }

    /**
     * Retrieves command arguments from input.
     *
     * @param trimmed Input from user.
     * @return Command arguments from input.
     */
    private static String readArgs(String trimmed) {
        int sp = trimmed.indexOf(' ');
        return (sp < 0) ? "" : trimmed.substring(sp + 1).trim();
    }

    /**
     * Retrieves any integers within the given string.
     *
     * @param s Input line.
     * @return Integer representing the index of the item.
     */
    private static int parseIndex(String s) throws JohnException {
        assert !s.trim().isEmpty() : "Parser: index token must not be blank";
        try {
            int i = Integer.parseInt(s.trim());
            if (i <= 0) {
                throw new JohnException("Index must be positive number");
            }
            return i - 1;
        } catch (NumberFormatException e) {
            throw new JohnException("Index must be a valid number.");
        }
    }
}
