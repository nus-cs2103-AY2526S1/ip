package v.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import v.command.AddDeadlineCommand;
import v.command.AddEventCommand;
import v.command.AddTodoCommand;
import v.command.Command;
import v.command.DeleteCommand;
import v.command.ExitCommand;
import v.command.FindCommand;
import v.command.HelpCommand;
import v.command.ListCommand;
import v.command.MarkCommand;
import v.command.SortCommand;
import v.command.UnmarkCommand;
import v.task.DukeException;

/**
 * Parses user input into commands for the application.
 */
public class Parser {
    // Command constants
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_SORT = "sort";
    private static final String COMMAND_HELP = "help";

    // Error messages
    private static final String ERROR_INVALID_COMMAND = "A curious utterance. I do not recognize it. "
            + "Try: todo, deadline, event, list, mark, unmark, sort, help, bye";
    private static final String ERROR_EMPTY_DESCRIPTION = "The description cannot be empty.";
    private static final String ERROR_INVALID_TASK_NUMBER = "Please provide a valid task number.";
    private static final String ERROR_INVALID_DATE_FORMAT = "Please use the format: yyyy-MM-dd for dates.";
    private static final String EMPTY_INPUT = "Even silence speaks volumes. But I need words to understand you.";
    // Constants for better code quality
    private static final int USER_INDEX_OFFSET = 1; // Convert 1-based user input to 0-based array index
    private static final int SPLIT_LIMIT = 2; // Split input into command and arguments

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The parsed command.
     * @throws DukeException If the input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        // Assertion: input should not be null
        assert input != null : "Input cannot be null";
        if (input.trim().isEmpty()) {
            throw new DukeException(EMPTY_INPUT);
        }
        String[] parts = input.split(" ", SPLIT_LIMIT);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";
        // Assertion: command should not be empty after processing
        assert !command.isEmpty() : "Command should not be empty after processing";

        switch (command) {
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_BYE:
            return new ExitCommand();
        case COMMAND_MARK:
            return parseMarkCommand(arguments);
        case COMMAND_UNMARK:
            return parseUnmarkCommand(arguments);
        case COMMAND_TODO:
            return parseTodoCommand(arguments);
        case COMMAND_DEADLINE:
            return parseDeadlineCommand(arguments);
        case COMMAND_EVENT:
            return parseEventCommand(arguments);
        case COMMAND_DELETE:
            return parseDeleteCommand(arguments);
        case COMMAND_FIND:
            return parseFindCommand(arguments);
        case COMMAND_SORT:
            return parseSortCommand(arguments);
        case COMMAND_HELP:
            return parseHelpCommand(arguments);
        default:
            throw new DukeException(ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Parses a task index from arguments and converts from 1-based to 0-based indexing.
     *
     * @param arguments The arguments containing the task index.
     * @return The 0-based task index.
     * @throws DukeException If the arguments cannot be parsed as a valid task number.
     */
    private static int parseTaskIndex(String arguments) throws DukeException {
        try {
            int userIndex = Integer.parseInt(arguments);
            return userIndex - USER_INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_INVALID_TASK_NUMBER);
        }
    }

    private static MarkCommand parseMarkCommand(String arguments) throws DukeException {
        int index = parseTaskIndex(arguments);
        return new MarkCommand(index);
    }

    private static UnmarkCommand parseUnmarkCommand(String arguments) throws DukeException {
        int index = parseTaskIndex(arguments);
        return new UnmarkCommand(index);
    }

    private static AddTodoCommand parseTodoCommand(String arguments) throws DukeException {
        // Assertion: arguments should not be null
        assert arguments != null : "Arguments cannot be null";
        if (arguments.isEmpty()) {
            throw new DukeException("Even ideas need words. The description of a todo cannot be empty.");
        }
        // Assertion: arguments should not be empty after validation
        assert !arguments.isEmpty() : "Arguments should not be empty after validation";
        return new AddTodoCommand(arguments);
    }

    private static AddDeadlineCommand parseDeadlineCommand(String arguments) throws DukeException {
        // Check if arguments start with /by (missing description)
        if (arguments.trim().startsWith("/by")) {
            throw new DukeException("Time waits for no one, but words are required. "
                    + "The description of a deadline cannot be empty.");
        }

        String[] parts = arguments.split("\\s+/by\\s+", 2);
        if (parts.length < 2) {
            throw new DukeException("A deadline requires a /by. Example: deadline return book /by 2025-02-02");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new DukeException("Time waits for no one, but words are required. "
                    + "The description of a deadline cannot be empty.");
        }

        try {
            LocalDate date = LocalDate.parse(by);
            return new AddDeadlineCommand(description, date);
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_INVALID_DATE_FORMAT);
        }
    }

    private static AddEventCommand parseEventCommand(String arguments) throws DukeException {
        // Check if arguments start with /from (missing description)
        if (arguments.trim().startsWith("/from")) {
            throw new DukeException("Every revolution needs a purpose. The description of an event cannot be empty.");
        }

        String[] parts = arguments.split("\\s+/from\\s+", 2);
        if (parts.length < 2) {
            throw new DukeException("An event requires /from and /to. "
                    + "Example: event meet /from 2025-01-01 /to 2025-02-02");
        }

        String description = parts[0].trim();
        String[] timeParts = parts[1].split("\\s+/to\\s+", 2);

        if (timeParts.length < 2) {
            throw new DukeException("Please specify both start and end times using /from and /to");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty()) {
            throw new DukeException("Every revolution needs a purpose. The description of an event cannot be empty.");
        }

        // Parse dates like deadline does
        try {
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            return new AddEventCommand(description, fromDate, toDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_INVALID_DATE_FORMAT);
        }
    }

    private static DeleteCommand parseDeleteCommand(String arguments) throws DukeException {
        int index = parseTaskIndex(arguments);
        return new DeleteCommand(index);
    }

    private static FindCommand parseFindCommand(String arguments) throws DukeException {
        String rest = arguments.trim();
        if (rest.isEmpty()) {
            throw new DukeException("Please provide a keyword to search for.");
        }
        return new FindCommand(rest);
    }

    private static SortCommand parseSortCommand(String arguments) throws DukeException {
        String criteria = arguments.trim();
        if (criteria.startsWith("by ")) {
            criteria = criteria.substring(3).trim();
        }
        return new SortCommand(criteria);
    }

    private static HelpCommand parseHelpCommand(String arguments) throws DukeException {
        String topic = arguments.trim();
        return new HelpCommand(topic);
    }
}
