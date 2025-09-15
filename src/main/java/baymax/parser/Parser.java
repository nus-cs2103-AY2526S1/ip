package baymax.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import baymax.command.AddCommand;
import baymax.command.Command;
import baymax.command.ExitCommand;
import baymax.command.FindCommand;
import baymax.command.ListCommand;
import baymax.command.UpdateCommand;
import baymax.exception.BaymaxException;

/**
 * Parses raw user input into executable {@link Command} objects.
 * <p>
 * The parser interprets text-based commands entered by the user
 * and maps them to the corresponding command classes. It also validates
 * command arguments and throws exceptions for invalid inputs.
 * </p>
 */
public class Parser {

    /**
     * Parses the index argument from a command input.
     *
     * @param arr The array of command tokens.
     * @return The task index.
     * @throws NumberFormatException If the index cannot be parsed as an integer.
     *
     */
    private static int parseIndex(String[] arr) {
        assert arr.length >= 2 : "Index argument missing";
        return Integer.parseInt(arr[1]) - 1;
    }

    /**
     * Ensures that the given command has the required arguments.
     *
     * @param command The command being validated.
     * @param args The command arguments split from the input.
     * @return The same {@code args} array if valid.
     * @throws BaymaxException.MissingDescriptionException If no description is provided.
     */
    private static String[] requireArgs(String command, String... args) throws BaymaxException {
        if (args.length < 2 || args[1].trim().isEmpty()) {
            throw new BaymaxException.MissingDescriptionException(command);
        }
        args[1] = args[1].trim();
        return args;
    }

    /**
     * Parses a {@code todo} command input into an {@link AddCommand}.
     *
     * @param parts The user input split into the command word and arguments.
     * @return An {@link AddCommand} that creates a todo task with the given description.
     * @throws BaymaxException.MissingDescriptionException If no description is provided.
     */
    private static Command parseTodo(String[] parts) throws BaymaxException {
        String description = requireArgs("todo", parts)[1];
        return AddCommand.todo(description);
    }

    /**
     * Parses a {@code deadline} command input into an {@link AddCommand}.
     *
     * @param parts The user input split into the command word and arguments.
     * @return An {@link AddCommand} that creates a deadline task with the given description and due date.
     * @throws BaymaxException.MissingDescriptionException If no description is provided.
     * @throws BaymaxException.MissingDeadlineException If the {@code /by} portion or date is missing.
     * @throws BaymaxException.InvalidDateException If the date cannot be parsed into a {@link LocalDate}.
     */
    private static Command parseDeadline(String[] parts) throws BaymaxException {
        String[] str = requireArgs("deadline", parts)[1].split("/by", 2);

        if (str.length < 2 || str[1].trim().isEmpty()) {
            throw new BaymaxException.MissingDeadlineException();
        }

        String description = str[0].trim();

        if (description.isEmpty()) {
            throw new BaymaxException.MissingDescriptionException("deadline");
        }

        String date = str[1].trim();

        try {
            LocalDate deadline = LocalDate.parse(date);
            return AddCommand.deadline(description, deadline);
        } catch (DateTimeParseException e) {
            throw new BaymaxException.InvalidDateException();
        }
    }

    /**
     * Parses an {@code event} command input into an {@link AddCommand}.
     *
     * @param parts The user input split into the command word and arguments.
     * @return An {@link AddCommand} that creates an event task with the given description, start, and end times.
     * @throws BaymaxException.MissingDescriptionException If no description is provided.
     * @throws BaymaxException.MissingEventDetailsException If the {@code /from} or {@code /to} arguments are missing or blank.
     */
    private static Command parseEvent(String[] parts) throws BaymaxException {
        String[] str = requireArgs("event", parts)[1].split("/from | /to");

        if (str.length != 3 || str[1].trim().isEmpty()) {
            throw new BaymaxException.MissingEventDetailsException();
        }

        String description = str[0].trim();
        String start = str[1].trim();
        String end = str[2].trim();

        return AddCommand.event(description, start, end);
    }

    /**
     * Parses a {@code find} command input into a {@link FindCommand}.
     *
     * @param parts The user input split into the command word and arguments.
     * @return A {@link FindCommand} that searches for tasks matching the given keyword.
     * @throws BaymaxException.MissingDescriptionException If the keyword is missing or blank.
     */
    private static Command parseFind(String[] parts) throws BaymaxException {
        String keyword = requireArgs("find", parts)[1];
        return new FindCommand(keyword);
    }

    /**
     * Parses the user input and returns the corresponding {@link Command}.
     *
     * @param input The raw user input string.
     * @return A {@link Command} object that, when executed, performs the action described by the input.
     * @throws BaymaxException If the input is invalid, missing arguments, or contains
     *                         invalid dates or indices.
     */
    public static Command parse(String input) throws BaymaxException {
        String[] parts = input.strip().split("\\s+", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark", "unmark", "delete":
            if (parts.length < 2) {
                throw new BaymaxException.InvalidIndexException(command);
            }
            return new UpdateCommand(command, parseIndex(parts));
        case "todo":
            return parseTodo(parts);
        case "deadline":
            return parseDeadline(parts);
        case "event":
            return parseEvent(parts);
        case "find":
            return parseFind(parts);
        case "bye":
            return new ExitCommand();
        default:
            throw new BaymaxException.InvalidCommandException();
        }
    }
}
