package sage.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sage.command.AddDeadlineCommand;
import sage.command.AddEventCommand;
import sage.command.AddTodoCommand;
import sage.command.Command;
import sage.command.DeleteCommand;
import sage.command.ExitCommand;
import sage.command.FindCommand;
import sage.command.HelpCommand;
import sage.command.ListCommand;
import sage.command.MarkCommand;
import sage.command.UnmarkCommand;
import sage.exception.SageException;
import sage.util.CommandWords;
import sage.util.ErrorMessages;

/**
 * Deals with making sense of the user command.
 * Parses user input strings into Command objects and extracts relevant arguments.
 */
public class Parser {

    /**
     * Parses the full command string from the user and returns a corresponding Command object.
     * @param fullCommand The full command string entered by the user.
     * @return A Command object representing the parsed command.
     * @throws SageException If the command is unrecognized or has invalid format/arguments.
     */
    public static Command parse(String fullCommand) throws SageException {
        assert fullCommand != null && !fullCommand.trim().isEmpty() : "Command string cannot be null or empty";
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case CommandWords.BYE:
            return new ExitCommand();
        case CommandWords.LIST:
            return new ListCommand();
        case CommandWords.MARK:
            return parseMarkCommand(arguments);
        case CommandWords.UNMARK:
            return parseUnmarkCommand(arguments);
        case CommandWords.DELETE:
            return parseDeleteCommand(arguments);
        case CommandWords.TODO:
            return parseTodoCommand(arguments);
        case CommandWords.DEADLINE:
            return parseDeadlineCommand(arguments);
        case CommandWords.EVENT:
            return parseEventCommand(arguments);
        case CommandWords.FIND:
            return parseFindCommand(arguments);
        case CommandWords.HELP:
            return new HelpCommand();
        default:
            throw new SageException("I'm sorry, but I don't know what that means :-( Try 'help' to see available commands.");
        }
    }

    private static void ensureArgumentsNotEmpty(String arguments, String errorMessage) throws SageException {
        if (arguments.trim().isEmpty()) {
            throw new SageException(errorMessage);
        }
    }

    /**
     * Parses arguments for a 'mark' command and returns a MarkCommand object.
     * @param arguments The arguments string for the 'mark' command.
     * @return A MarkCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseMarkCommand(String arguments) throws SageException {
        assert arguments != null : "Arguments string cannot be null";
        ensureArgumentsNotEmpty(arguments, "Please specify the task number to mark.");
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new SageException("Please enter a valid task number.");
        }
    }

    /**
     * Parses arguments for an 'unmark' command and returns an UnmarkCommand object.
     * @param arguments The arguments string for the 'unmark' command.
     * @return An UnmarkCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseUnmarkCommand(String arguments) throws SageException {
        assert arguments != null : "Arguments string cannot be null";
        ensureArgumentsNotEmpty(arguments, "Please specify the task number to unmark.");
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new SageException("Please enter a valid task number.");
        }
    }

    /**
     * Parses arguments for a 'delete' command and returns a DeleteCommand object.
     * @param arguments The arguments string for the 'delete' command.
     * @return A DeleteCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseDeleteCommand(String arguments) throws SageException {
        assert arguments != null : "Arguments string cannot be null";
        ensureArgumentsNotEmpty(arguments, "Please specify the task number to delete.");
        try {
            int taskIndex = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new SageException("Please enter a valid task number.");
        }
    }

    /**
     * Parses arguments for a 'todo' command and returns an AddTodoCommand object.
     * @param arguments The arguments string for the 'todo' command.
     * @return An AddTodoCommand object.
     * @throws SageException If the arguments are invalid or missing.
     */
    private static Command parseTodoCommand(String arguments) throws SageException {
        assert arguments != null : "Arguments string cannot be null";
        ensureArgumentsNotEmpty(arguments, "The description of a todo cannot be empty.");
        return new AddTodoCommand(arguments.trim());
    }

    /**
     * Parses arguments for a 'deadline' command and returns an AddDeadlineCommand object.
     * @param arguments The arguments string for the 'deadline' command.
     * @return An AddDeadlineCommand object.
     * @throws SageException If the arguments are invalid or missing, or date format is incorrect.
     */
    private static Command parseDeadlineCommand(String arguments) throws SageException {
        assert arguments != null : "Arguments string cannot be null";
        ensureArgumentsNotEmpty(arguments, "The description of a deadline cannot be empty.");
        if (!arguments.contains(" /by ")) {
            throw new SageException(ErrorMessages.INVALID_DEADLINE_FORMAT);
        }
        String[] deadlineParts = arguments.split(" /by ", 2);
        if (deadlineParts.length != 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new SageException(ErrorMessages.INVALID_DEADLINE_FORMAT);
        }
        LocalDateTime by = parseDateTime(deadlineParts[1].trim());
        return new AddDeadlineCommand(deadlineParts[0].trim(), by);
    }

    /**
     * Parses arguments for an 'event' command and returns an AddEventCommand object.
     * @param arguments The arguments string for the 'event' command.
     * @return An AddEventCommand object.
     * @throws SageException If the arguments are invalid or missing, or date format is incorrect.
     */
    private static Command parseEventCommand(String arguments) throws SageException {
        assert arguments != null : "Arguments string cannot be null";
        ensureArgumentsNotEmpty(arguments, "The description of an event cannot be empty.");
        if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
            throw new SageException(ErrorMessages.INVALID_EVENT_FORMAT);
        }
        String[] eventParts = arguments.split(" /from ", 2);
        if (eventParts.length != 2) {
            throw new SageException(ErrorMessages.INVALID_EVENT_FORMAT);
        }
        String[] fromToParts = eventParts[1].split(" /to ", 2);
        if (fromToParts.length != 2 || eventParts[0].trim().isEmpty()
                || fromToParts[0].trim().isEmpty() || fromToParts[1].trim().isEmpty()) {
            throw new SageException(ErrorMessages.INVALID_EVENT_FORMAT);
        }
        LocalDateTime from = parseDateTime(fromToParts[0].trim());
        LocalDateTime to = parseDateTime(fromToParts[1].trim());
        return new AddEventCommand(eventParts[0].trim(), from, to);
    }

    /**
     * Parses the arguments for the find command and returns a FindCommand.
     *
     * @param arguments The arguments string containing the keyword to search for.
     * @return A FindCommand with the specified keyword.
     * @throws SageException If the keyword is empty.
     */
    private static Command parseFindCommand(String arguments) throws SageException {
        assert arguments != null : "Arguments string cannot be null";
        ensureArgumentsNotEmpty(arguments, "The keyword for find cannot be empty.");
        return new FindCommand(arguments.trim());
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     * Accepts flexible date formats and provides helpful error messages.
     *
     * @param dateTime The date and time string to parse (expected format: d/M/yyyy HHmm).
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws SageException If the date and time string is not in the expected format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws SageException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new SageException(ErrorMessages.INVALID_DATE_FORMAT);
        }
    }

}