package alpaca;

import alpaca.command.*;

/**
 * Parses user input strings and converts them into corresponding {@link Command} objects.
 */
public class Parser {

    /**
     * Parses the given user input into a {@link Command}.
     *
     * @param input The raw user input string.
     * @return The {@link Command} representing the user's request.
     *         Returns {@link UnknownCommand} if the input is unrecognized.
     * @throws IllegalArgumentException if input is null or blank.
     */
    public static Command parse(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        String[] words = input.trim().split(" ", 2);
        String commandWord = words[0].toLowerCase();
        String args = words.length > 1 ? words[1].trim() : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(args, true);
        case "unmark":
            return new MarkCommand(args, false);
        case "delete":
            return new DeleteCommand(args);
        case "todo":
            return new AddCommand("todo", args);
        case "deadline":
            return new AddCommand("deadline", args);
        case "event":
            return new AddCommand("event", args);
        case "find":
            return new FindCommand(args);
        case "help":
            return new HelpCommand();
        case "within":
            return new AddCommand("within", args);
        default:
            return new UnknownCommand();
        }
    }
}
