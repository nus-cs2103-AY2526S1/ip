package echo.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import echo.Echo;
import echo.command.ByeCommand;
import echo.command.Command;
import echo.command.DeadlineCommand;
import echo.command.DeleteCommand;
import echo.command.EventCommand;
import echo.command.FindCommand;
import echo.command.ListCommand;
import echo.command.MarkCommand;
import echo.command.SortCommand;
import echo.command.ToDoCommand;
import echo.command.UnmarkCommand;
import echo.echoexception.EchoException;

/**
 * Represents action the user can enter for the command
 */
enum Action {
    BYE,
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    DELETE,
    FIND,
    SORT,
    DEFAULT
}

/**
 * Represents parser to interpret raw input strings to commands.
 */
public class Parser {
    /**
     * Parses the input from the user and returns the corresponding command
     *
     * @param input Input string to be parsed.
     * @return array A String array representing parsed commands and arguments.
     * @throws EchoException if description, date or time are missing for task creation.
     */
    public static Command parse(Echo echo, String input) throws EchoException {
        String[] parts = input.split(" ", 2);
        Action action = parseAction(parts[0]);
        int limit = echo.getTasklist().getSize();

        return switch (action) {
            case BYE -> new ByeCommand(echo);
            case LIST -> new ListCommand(echo);
            case TODO -> parseTodoCommand(echo, parts);
            case DEADLINE -> parseDeadlineCommand(echo, parts);
            case EVENT -> parseEventCommand(echo, parts);
            case MARK -> {
                int markIndex = parsePositiveIndex(parts, "mark", limit);
                yield new MarkCommand(echo, markIndex);
            }
            case UNMARK -> {
                int unmarkIndex = parsePositiveIndex(parts, "unmark", limit);
                yield new UnmarkCommand(echo, unmarkIndex);
            }
            case DELETE -> {
                int deleteIndex = parsePositiveIndex(parts, "delete", limit);
                yield new DeleteCommand(echo, deleteIndex);
            }
            case FIND -> parseFindCommand(echo, parts);
            case SORT -> new SortCommand(echo);
            default -> null;
        };
    }

    private static Action parseAction(String raw) {
        try {
            return Action.valueOf(raw.toUpperCase());
        } catch (IllegalArgumentException e) {
            return  Action.DEFAULT;
        }
    }

    private static String checkForArgs(String[] parts, String command) throws EchoException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new EchoException("Oops! The description cannot be EMPTY!!! The correct format is:\n "
                    + "  " + command  + " <description>\n"
                    + "Example: todo read");
        }
        return parts[1];
    }

    private static Command parseTodoCommand(Echo echo, String[] parts) throws EchoException {
        return new ToDoCommand(echo, checkForArgs(parts, "todo"));
    }

    private static Command parseDeadlineCommand(Echo echo, String[] parts) throws EchoException {
        String[] deadlineParts = checkForArgs(parts, "deadline").split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
            throw new EchoException("Wait a min! Your deadline cannot be EMPTY!!! The correct format is:\n "
                    + "  deadline <description> /by <DD/MM/YYYY HHmm>\n"
                    + "Example: deadline read book /by 18/9/2025 1000");
        }
        LocalDateTime by = parseDateTime(deadlineParts[1], "deadline");
        return new DeadlineCommand(echo, deadlineParts[0], by);
    }

    private static Command parseEventCommand(Echo echo, String[] parts) throws EchoException {
        String[] eventParts = checkForArgs(parts, "event").split(" /from");
        if (eventParts.length < 2 || eventParts[0].isEmpty()) {
            throw new EchoException("Hold up! Your to and from cannot be EMPTY!!! The correct format is:\n "
                    + "  event <description> /from <DD/MM/YYYY> HHmm /to <DD/MM/YYYY HHmm>\n"
                    + "Example: event read book /from 18/9/2025 1000 /to 19/9/2025 1000");
        }
        String[] fromToParts = eventParts[1].split(" /to ", 2);
        if (fromToParts.length < 2 || fromToParts[0].isEmpty() || fromToParts[1].isEmpty()) {
            throw new EchoException("Hold up! Your to and from cannot be EMPTY!!! The correct format is:\n "
                    + "  event <description> /from <DD/MM/YYYY> HHmm /to <DD/MM/YYYY HHmm>\n"
                    + "Example: event read book /from 18/9/2025 1000 /to 19/9/2025 1000");
        }
        LocalDateTime from = parseDateTime(fromToParts[0], "event");
        LocalDateTime to = parseDateTime(fromToParts[1], "event");
        return new EventCommand(echo, eventParts[0], from, to);
    }

    private static int parsePositiveIndex(String[] parts, String command, int limit) throws EchoException {
        String indexString = checkForArgs(parts, command);
        try {
            int index = Integer.parseInt(indexString);
            assert index > 0: "index of item to " + command + " must be greater than 0";
            if (index <= 0) {
                throw new EchoException("Index must POSITIVEEEEE!");
            }
            if (index > limit) {
                throw new EchoException("Index is TOO LARGEEEE!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new EchoException("Not a NUMBER! Not a NUMBER! Please enter a valid index. \n" +
                    "Example: " + command + " 1");
        }
    }

    private static Command parseFindCommand(Echo echo, String[] parts) throws EchoException {
        return new FindCommand(echo, checkForArgs(parts, "find"));
    }

    private static LocalDateTime parseDateTime(String dateTime, String command) throws EchoException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new EchoException("Invalid date-time format! Please provide BOTH date and time.\n" +
                    "Example: " + command + " read book /by 18/09/2025 1000");
        }
    }

}
