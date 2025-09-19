package bytebot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bytebot.ByteException;
import bytebot.command.Command;
import bytebot.command.DeadlineCommand;
import bytebot.command.DeleteCommand;
import bytebot.command.EventCommand;
import bytebot.command.ExitCommand;
import bytebot.command.FindCommand;
import bytebot.command.ListCommand;
import bytebot.command.MarkCommand;
import bytebot.command.SortCommand;
import bytebot.command.TodoCommand;
import bytebot.command.UnmarkCommand;
import bytebot.task.Deadline;
import bytebot.task.Event;
import bytebot.task.Status;
import bytebot.task.Task;
import bytebot.task.Todo;

/**
 * Parses raw user input into Command instances.
 */
public class Parser {

    /**
     * Splits options into up to 3 segments
     * The first segment is considered free; subsequent segments follow each switch.
     *
     * @param options Tokenized input string
     * @return Array of up to 3 segments: description, first option, second option
     */
    private static String[] parseSegments(String[] options) {
        assert options != null : "Options cant be null";
        assert options.length > 0 : "Options must have at least the command keyword";
        String[] segments = new String[3];
        StringBuilder currentSegment = new StringBuilder();
        int segmentIndex = 0;

        for (String option : options) {
            if (option.startsWith("/")) {
                segments[segmentIndex] = currentSegment.toString().trim();
                segmentIndex++;
                currentSegment.setLength(0);
            } else {
                if (!currentSegment.isEmpty()) {
                    currentSegment.append(" ");
                }
                currentSegment.append(option);
            }
        }

        if (segmentIndex < segments.length) {
            segments[segmentIndex] = currentSegment.toString().trim();
        }

        return segments;
    }

    /**
     * Parses a user input line into a Command.
     *
     * @param input Raw user input
     * @return A concrete command corresponding to the input
     * @throws ByteException If the input is invalid
     */
    public static Command parse(String input) throws ByteException {
        assert input != null : "Input cannot be null";
        assert !input.trim().isEmpty() : "Input cannot be empty";
        String[] parts = input.split(" ", 2);
        String keyword = parts[0];
        switch (keyword) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return parseTodo(input);
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "mark":
            return parseMark(parts);
        case "unmark":
            return parseUnmark(parts);
        case "delete":
            return parseDelete(parts);
        case "find":
            return parseFind(parts);
        case "sort":
            return parseSort(parts);
        default:
            throw new ByteException("I dont know what that means!");
        }
    }

    private static Command parseTodo(String input) {
        String[] segment = parseSegments(input.split(" "));
        String description = getDescription(segment, "todo");
        return new TodoCommand(description);
    }

    private static Command parseDeadline(String input) {
        String[] segment = parseSegments(input.split(" "));
        String description = getDescription(segment, "deadline");
        String by = segment[1];
        return new DeadlineCommand(description, by);
    }

    private static Command parseEvent(String input) {
        String[] segment = parseSegments(input.split(" "));
        String description = getDescription(segment, "event");
        String from = segment[1];
        String to = segment[2];
        return new EventCommand(description, from, to);
    }

    /**
     * Extracts the description segment for a given command keyword.
     * Returns an empty string if the first segment is null, empty, or equals the keyword.
     *
     * @param segments Parsed segments from the input
     * @param keyword  Command keyword (e.g., "todo", "deadline", "event")
     * @return Cleaned description text
     */
    private static String getDescription(String[] segments, String keyword) {
        String first = (segments != null && segments.length > 0) ? segments[0] : null;
        if (first == null) {
            return "";
        }
        String trimmed = first.trim();
        if (trimmed.isEmpty() || trimmed.equals(keyword)) {
            return "";
        }
        return trimmed;
    }

    private static Command parseMark(String[] parts) throws ByteException {
        String arg = parts.length >= 2 ? parts[1].trim() : "";
        if (!arg.matches("\\d+")) {
            throw new ByteException("Input a valid task number.");
        }
        int index = Integer.parseInt(arg) - 1;
        return new MarkCommand(index);
    }

    private static Command parseUnmark(String[] parts) throws ByteException {
        String arg = parts.length >= 2 ? parts[1].trim() : "";
        if (!arg.matches("\\d+")) {
            throw new ByteException("Input a valid task number.");
        }
        int index = Integer.parseInt(arg) - 1;
        return new UnmarkCommand(index);
    }

    private static Command parseDelete(String[] parts) throws ByteException {
        String arg = parts.length >= 2 ? parts[1].trim() : "";
        if (arg.isEmpty()) {
            throw new ByteException("Specify the task number to delete");
        }
        if (!arg.matches("\\d+")) {
            throw new ByteException("Input a valid task number.");
        }
        int index = Integer.parseInt(arg) - 1;
        return new DeleteCommand(index);
    }

    private static Command parseFind(String[] parts) {
        String term = parts.length >= 2 ? parts[1].trim() : "";
        return new FindCommand(term);
    }

    private static Command parseSort(String[] parts) throws ByteException {
        String rest = parts.length >= 2 ? parts[1].trim().toLowerCase() : "";
        if (rest.equals("deadline") || rest.equals("deadlines")) {
            return new SortCommand(SortCommand.SortType.DEADLINE);
        }
        if (rest.equals("all")) {
            return new SortCommand(SortCommand.SortType.ALL);
        }
        throw new ByteException("Specify what to sort");
    }

    /**
     * Delegated from storage to keep storage focused on I/O.
     * Parses a task line from data storage file into a Task
     * (Helped to modularize method structure)
     *
     * @param line the raw line read from storage
     * @return a reconstructed with status applied
     * @throws ByteException if the line invalid
     */
    public static Task parseTaskFromString(String line) throws ByteException {
        assert line != null : "Input line cannot be null";
        assert !line.trim().isEmpty() : "Input line cannot be empty";
        String[] tokens = line.split(" \\| ");
        assert tokens.length >= 2 : "Serialized task line must contain status and body";

        Status status = Status.fromString(tokens[0]);
        String taskString = tokens[1];

        Task task = parseTaskBody(taskString);
        if (status == Status.DONE) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }

    /**
     * Parses the task body into a Task
     *
     * @param taskString the serialized task body string
     * @return a Task instance
     * @throws ByteException if the task type is unknown or content invalid
     */
    private static Task parseTaskBody(String taskString) throws ByteException {
        String type = taskString.substring(1, 2);
        switch (type) {
        case "T":
            return parseTodoFromStorage(taskString);
        case "D":
            return parseDeadlineFromStorage(taskString);
        case "E":
            return parseEventFromStorage(taskString);
        default:
            throw new ByteException("Unknown task type in string: " + taskString);
        }
    }

    /**
     * Parses a serialized Todo task body into a Todo
     *
     * @param taskString the serialized task body string for a Todo
     * @return a Todo with extracted description
     */
    private static Task parseTodoFromStorage(String taskString) {
        String description = taskString.substring(taskString.indexOf("] ") + 2);
        return new Todo(description);
    }

    /**
     * Parses a serialized Deadline task body into a Deadline.
     *
     * @param taskString the serialized task body string for a Deadline
     * @return a Deadline with description and parsed due datetime
     * @throws ByteException if the date segment cannot be parsed
     */
    private static Task parseDeadlineFromStorage(String taskString) throws ByteException {
        int byStart = taskString.indexOf("(by: ") + 5;
        int byEnd = taskString.indexOf(")");
        assert byStart >= 5 && byEnd > byStart : "Deadline must contain by time";

        String byDisplay = taskString.substring(byStart, byEnd);
        String description = taskString.substring(
                taskString.indexOf("] ") + 2,
                taskString.indexOf(" (by:"));
        String convertedBy = convertDisplayToInput(byDisplay);
        return new Deadline(description, convertedBy);
    }

    /**
     * Parses a serialized Event task body into an Event.
     *
     * @param taskString the serialized task body string for an Event
     * @return an Event with description and parsed from/to datetimes
     * @throws ByteException if either datetime segment cannot be parsed
     */
    private static Task parseEventFromStorage(String taskString) throws ByteException {
        int fromStart = taskString.indexOf("(from: ") + 7;
        int fromEnd = taskString.indexOf(" to:");
        int toStart = taskString.indexOf("to: ") + 4;
        int toEnd = taskString.indexOf(")");

        assert fromStart >= 7 && fromEnd > fromStart : "Event must contain from time";
        assert toStart >= 4 && toEnd > toStart : "Event must contain to time";

        String fromDisplay = taskString.substring(fromStart, fromEnd);
        String toDisplay = taskString.substring(toStart, toEnd);
        String description = taskString.substring(
                taskString.indexOf("] ") + 2,
                taskString.indexOf(" (from:"));

        String convertedFrom = convertDisplayToInput(fromDisplay);
        String convertedTo = convertDisplayToInput(toDisplay);
        return new Event(description, convertedFrom, convertedTo);
    }

    /**
     * Converts display format (MMM dd yyyy, h:mm a) back to input format (d/M/yyyy
     * HHmm).
     * This is needed when loading tasks from file.
     *
     * @param displayFormat The date string in display format
     * @return The date string in input format
     * @throws ByteException if the conversion fails
     */
    private static String convertDisplayToInput(String displayFormat) throws ByteException {
        assert displayFormat != null : "Display date must exist";
        assert !displayFormat.trim().isEmpty() : "Display date cannot be empty";
        try {
            DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            LocalDateTime dateTime = LocalDateTime.parse(displayFormat, displayFormatter);
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return dateTime.format(inputFormatter);
        } catch (Exception e) {
            throw new ByteException("Failed to parse date from storage: " + displayFormat);
        }
    }
}


