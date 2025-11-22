package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exception.GenieweenieException;

/**
 * Parses user input into commands.
 */
public class Parser {

    /**
     * Parses user input.
     *
     * @param fullCommand full user input
     * @return Command object
     * @throws GenieweenieException if command is invalid
     */
    public static Command parse(String fullCommand) throws GenieweenieException {
        String trimmedCommand = fullCommand.trim();
        String[] parts = trimmedCommand.split(" ", 2); // command + arguments
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "add":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new GenieweenieException("☹ OOPS!!! The description for add cannot be empty.");
            }
            return new AddCommand(parts[1].trim());

        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new GenieweenieException("☹ OOPS!!! The keyword for find cannot be empty.");
            }
            return new FindCommand(parts[1].trim());

        case "mark":
            return parseIndexCommand(parts, "mark");

        case "unmark":
            return parseIndexCommand(parts, "unmark");

        case "delete":
            return parseIndexCommand(parts, "delete");

        default:
            // If the user input starts with todo/deadline/event, treat as add
            return new AddCommand(trimmedCommand);
        }
    }

    /**
     * Parses commands that require an integer index (mark/unmark/delete).
     *
     * @param parts array of command and argument
     * @param commandType "mark", "unmark", or "delete"
     * @return the appropriate Command
     * @throws GenieweenieException if invalid
     */
    private static Command parseIndexCommand(String[] parts, String commandType) throws GenieweenieException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new GenieweenieException("eep! I need a task number after '" + commandType
                    + "'. Try: " + commandType + " 1");
        }
        try {
            int index = Integer.parseInt(parts[1].trim());
            switch (commandType) {
            case "mark":
                return new MarkCommand(index);
            case "unmark":
                return new UnmarkCommand(index);
            case "delete":
                return new DeleteCommand(index);
            default:
                throw new GenieweenieException("Unknown command type: " + commandType);
            }
        } catch (NumberFormatException e) {
            throw new GenieweenieException("eep! This is not a valid number. Try: " + commandType + " 1");
        }
    }
}
