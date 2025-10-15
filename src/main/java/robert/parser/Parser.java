package robert.parser;

import robert.command.*;
import robert.exception.RobertException;
import robert.task.Deadline;
import robert.task.Event;
import robert.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user input commands for the Robert chatbot.
 */
public class Parser {
    /**
     * Parses the user input and returns the appropriate Command.
     *
     * @param input The full user input string.
     * @return The Command to execute.
     * @throws RobertException If the input is invalid.
     */
    public static Command parse(String input) throws RobertException {
        String commandWord = extractCommandWord(input);
        
        switch (commandWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskIndex(input));
        case "unmark":
            return new UnmarkCommand(parseTaskIndex(input));
        case "delete":
            return new DeleteCommand(parseTaskIndex(input));
        case "todo":
            return new AddTodoCommand(parseTodo(input));
        case "deadline":
            return new AddDeadlineCommand(parseDeadline(input));
        case "event":
            return new AddEventCommand(parseEvent(input));
        case "find":
            return new FindCommand(parseFind(input));
        default:
            throw new RobertException(
                    "Unknown command. Try: list, todo, deadline, event, mark, unmark, delete, find, bye");
        }
    }

    private static String extractCommandWord(String input) {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return "";
        }
        return trimmed.split("\\s+")[0].toLowerCase();
    }

    private static int parseTaskIndex(String input) throws RobertException {
        String[] parts = input.trim().split("\\s+");
        if (parts.length != 2) {
            throw new RobertException("Please provide a valid task number.");
        }
        
        try {
            int index = Integer.parseInt(parts[1]);
            return index - 1;
        } catch (NumberFormatException e) {
            throw new RobertException("Please provide a valid task number.");
        }
    }

    private static Todo parseTodo(String input) throws RobertException {
        String description = extractDescription(input, "todo", 4);
        return new Todo(description);
    }

    private static Deadline parseDeadline(String input) throws RobertException {
        String[] parts = splitByDelimiter(input, "deadline", "/by");
        validateDeadlineParts(parts);
        
        try {
            LocalDateTime by = parseDateTime(parts[1].trim());
            return new Deadline(parts[0].trim(), by);
        } catch (DateTimeParseException e) {
            throw new RobertException(
                    "Invalid date/time format. Use 'yyyy-MM-dd HHmm', e.g., '2019-12-02 1800'.");
        }
    }

    private static Event parseEvent(String input) throws RobertException {
        String[] descAndTimes = splitByDelimiter(input, "event", "/from");
        validateEventDescriptionParts(descAndTimes);
        
        String[] timeParts = descAndTimes[1].split(" /to ", 2);
        validateEventTimeParts(timeParts);
        
        try {
            LocalDateTime from = parseDateTime(timeParts[0].trim());
            LocalDateTime to = parseEndTime(timeParts[1].trim(), from);
            return new Event(descAndTimes[0].trim(), from, to);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new RobertException(
                    "Invalid date/time format. Use 'yyyy-MM-dd HHmm', e.g., '2019-12-02 1400'.");
        }
    }

    private static String parseFind(String input) throws RobertException {
        return extractDescription(input, "find", 4);
    }

    private static String extractDescription(String input, String command, int prefixLength) 
            throws RobertException {
        String trimmed = input.trim();
        if (trimmed.length() <= prefixLength || !trimmed.startsWith(command)) {
            throw new RobertException("The description of a " + command + " cannot be empty.");
        }
        
        String description = trimmed.substring(prefixLength).trim();
        if (description.isEmpty()) {
            throw new RobertException("The description of a " + command + " cannot be empty.");
        }
        
        return description;
    }

    private static String[] splitByDelimiter(String input, String command, String delimiter) 
            throws RobertException {
        int commandLength = command.length();
        if (input.length() <= commandLength) {
            throw new RobertException("Please provide a valid " + command + " description.");
        }
        
        return input.substring(commandLength).split(" " + delimiter + " ", 2);
    }

    private static void validateDeadlineParts(String[] parts) throws RobertException {
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new RobertException(
                    "Please provide a valid description and deadline, " 
                    + "e.g., 'deadline return book /by 2019-12-02 1800'.");
        }
    }

    private static void validateEventDescriptionParts(String[] parts) throws RobertException {
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new RobertException(
                    "Please provide a valid description and event time, " 
                    + "e.g., 'event project meeting /from 2019-12-02 1400 /to 1600'.");
        }
    }

    private static void validateEventTimeParts(String[] timeParts) throws RobertException {
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new RobertException(
                    "Please provide both start and end times, " 
                    + "e.g., 'event project meeting /from 2019-12-02 1400 /to 1600'.");
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    private static LocalDateTime parseEndTime(String timeStr, LocalDateTime from) {
        if (timeStr.contains("-")) {
            return parseDateTime(timeStr);
        } else {
            int hour = Integer.parseInt(timeStr.substring(0, 2));
            int minute = Integer.parseInt(timeStr.substring(2));
            return from.withHour(hour).withMinute(minute);
        }
    }
}
