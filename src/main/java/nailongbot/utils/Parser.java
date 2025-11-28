package nailongbot.utils;

import nailongbot.command.AddDeadlineCommand;
import nailongbot.command.AddEventCommand;
import nailongbot.command.AddTodoCommand;
import nailongbot.command.Command;
import nailongbot.command.DeleteCommand;
import nailongbot.command.ExitCommand;
import nailongbot.command.FindCommand;
import nailongbot.command.ListCommand;
import nailongbot.command.MarkCommand;
import nailongbot.command.ShowCommand;
import nailongbot.command.SortCommand;
import nailongbot.command.UnknownCommand;
import nailongbot.command.UnmarkCommand;
import nailongbot.exception.EmptyDescriptionException;
import nailongbot.exception.JkBotException;

/**
 * The {@code Parser} class is responsible for interpreting user input
 * and converting it into a specific {@link Command} that the bot can execute.
 * It reads the first word of the input to determine the type of command,
 * validates any required arguments, and then constructs the appropriate
 * {@link Command} object
 * If the input does not match any known command, an {@link UnknownCommand}
 * is returned. If the input is empty or missing required arguments, and
 * {@link EmptyDescriptionException} is thrown.
 * This class also provides a private helper method {@code validateNotEmpty}
 * to ensure that arguments are not missing for commands that require them.
 *
 * @see Command
 * @see JkBotException
 * @see EmptyDescriptionException
 */
public class Parser {
    /**
     * Reads the users input based on the first word of input and executes the
     * respective command
     *
     * @param input the users input
     * @return a specific Command to execute
     * @throws JkBotException if there is an error while parsing the input
     */
    public static Command parse(String input) throws JkBotException {
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyDescriptionException("Empty command");
        }

        // Normalize whitespace to support multiple spaces/tabs between command and args
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(parseIndex(arguments, "mark"));

        case "unmark":
            return new UnmarkCommand(parseIndex(arguments, "unmark"));

        case "todo":
            validateNotEmpty(arguments, "todo [description]");
            return new AddTodoCommand(arguments);

        case "deadline":
            validateNotEmpty(arguments, "deadline [description] /by [time]");
            return new AddDeadlineCommand(arguments);

        case "event":
            validateNotEmpty(arguments, "event [description] /from [start] /to [end]");
            return new AddEventCommand(arguments);

        case "delete":
            return new DeleteCommand(parseIndex(arguments, "delete"));

        case "show":
            validateNotEmpty(arguments, "show d/M/yyyy");
            return new ShowCommand(arguments);

        case "find":
            validateNotEmpty(arguments, "find [key words]");
            return new FindCommand(arguments);

        case "sort":
            return new SortCommand();

        default:
            return new UnknownCommand();
        }
    }

    /**
     * Reads the users input based on the first word of input and executes the
     * respective command
     *
     * @param arguments the words after the command word in the input
     * @param format    the supposed format for input based on the inputs first word
     * @throws EmptyDescriptionException if the description of the input is empty
     */
    private static void validateNotEmpty(String arguments, String format) throws EmptyDescriptionException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("Error: Please specify - " + format);
        }
    }

    /**
     * Parses a 1-based index from arguments, validates it, and returns 0-based
     * index.
     *
     * @param arguments   the argument string expected to contain a number
     * @param commandName the command name for error messages
     * @return zero-based index
     * @throws EmptyDescriptionException if missing or invalid number
     */
    private static int parseIndex(String arguments, String commandName) throws EmptyDescriptionException {
        validateNotEmpty(arguments, commandName + " [number]");
        try {
            int oneBased = Integer.parseInt(arguments.trim());
            if (oneBased <= 0) {
                throw new EmptyDescriptionException("Error: Please specify a valid number for " + commandName);
            }
            return oneBased - 1;
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException("Error: Please specify a valid number for " + commandName);
        }
    }
}
