package aries.command;

import aries.AriesException;

/**
 * Parses user input into commands.
 */
public class CommandParser {
    /**
     * Parses the input string and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The corresponding Command object.
     * @throws AriesException If the input is empty or invalid.
     */
    public static Command parse(String input) throws AriesException {
        String line = input.trim();
        if (line.isEmpty()) {
            throw new AriesException("Please enter a command.");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String description = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(description);
        case "unmark":
            return new UnmarkCommand(description);
        case "todo":
            return new ToDoCommand(description);
        case "deadline":
            return new DeadlineCommand(description);
        case "event":
            return new EventCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "find":
            return new FindCommand(description);
        case "help":
            return new HelpCommand();
        default:
            return new UnknownCommand();
        }
    }
}
