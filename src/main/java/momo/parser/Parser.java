package momo.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import momo.command.AddDeadlineCommand;
import momo.command.AddEventCommand;
import momo.command.AddTodoCommand;
import momo.command.Command;
import momo.command.DeleteCommand;
import momo.command.ExitCommand;
import momo.command.FindCommand;
import momo.command.HelpCommand;
import momo.command.ListCommand;
import momo.command.MarkCommand;
import momo.command.UnmarkCommand;
import momo.exception.MomoException;
import momo.task.Deadline;
import momo.task.Event;
import momo.task.Task;
import momo.task.Todo;

/**
 * Parses user input and saved task strings into corresponding {@link Command} objects
 * or {@link Task} objects for the Momo application.
 */
public class Parser {

    /**
     * Converts a string representation of date and time into {@link LocalDateTime}.
     *
     * @param localDateTime the date and time string in "yyyy-MM-dd HHmm" format.
     * @return the parsed {@link LocalDateTime}.
     */
    private static LocalDateTime parseToLocalDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Parses the "help" command into a {@link HelpCommand}.
     *
     * @return a new {@link HelpCommand}.
     */
    private static Command parseHelp() {
        return new HelpCommand();
    }

    /**
     * Parses the "bye" command into an {@link ExitCommand}.
     *
     * @return a new {@link ExitCommand}.
     */
    private static Command parseBye() {
        return new ExitCommand();
    }

    /**
     * Parses the "list" command into a {@link ListCommand}.
     *
     * @return a new {@link ListCommand}.
     */
    private static Command parseList() {
        return new ListCommand();
    }

    /**
     * Parses a "todo" command into an {@link AddTodoCommand}.
     *
     * @param components the user input split into command and description.
     * @return a new {@link AddTodoCommand}.
     * @throws MomoException if the description is missing.
     */
    private static Command parseTodo(String[] components) throws MomoException {
        if (components.length < 2) {
            String errorDetail = "The description of the todo is empty!";
            String errorFix = "Fix: Try \"todo <description>\" instead!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        return new AddTodoCommand(components[1]);
    }

    /**
     * Parses a "find" command into a {@link FindCommand}.
     *
     * @param components the user input split into command and keyword.
     * @return a new {@link FindCommand}.
     * @throws MomoException if the keyword is missing.
     */
    private static Command parseFind(String[] components) throws MomoException {
        if (components.length < 2) {
            String errorDetail = "The keyword to find is missing!";
            String errorFix = "Fix: Try \"find <keyword>\" instead!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        return new FindCommand((components[1]));
    }

    /**
     * Parses a "deadline" command into an {@link AddDeadlineCommand}.
     *
     * @param components the user input split into command and arguments.
     * @return a new {@link AddDeadlineCommand}.
     * @throws MomoException if the description, date, or "/by" is missing, or the date format is invalid.
     */
    private static Command parseDeadline(String[] components) throws MomoException {
        String errorFix = "Fix: Try \"deadline <description> /by <yyyy-MM-dd HHmm>\" instead!";
        if (components.length < 2) {
            String errorDetail = "The description of the deadline is empty!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        String[] parsedDeadline = components[1].split(" /by ", 2);
        if (parsedDeadline.length < 2) {
            String errorDetail = "The deadline is missing \"/by\"!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        try {
            String description = parsedDeadline[0];
            LocalDateTime by = parseToLocalDateTime(parsedDeadline[1]);
            return new AddDeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            String errorDetail = "The format of date and time entered is invalid!";
            String example = "Example: deadline return book /by 2025-04-07 1805";
            throw new MomoException(errorDetail + "\n" + errorFix + "\n" + example);
        }
    }

    /**
     * Parses an "event" command into an {@link AddEventCommand}.
     *
     * @param components the user input split into command and arguments.
     * @return a new {@link AddEventCommand}.
     * @throws MomoException if the description, "/from", or "/to" is missing, or the date format is invalid.
     */
    private static Command parseEvent(String[] components) throws MomoException {
        String errorFix = "Fix: Try \"event <description> /from <yyyy-MM-dd HHmm>"
                + " /to <yyyy-MM-dd HHmm>\" instead!";
        if (components.length < 2) {
            String errorDetail = "The description of the event is empty!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        String[] parsedEvent = components[1].split(" /from ", 2);
        if (parsedEvent.length < 2) {
            String errorDetail = "The event is missing \"/from\"!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        String[] parsedStartEndTime = parsedEvent[1].split(" /to ", 2);
        if (parsedStartEndTime.length < 2) {
            String errorDetail = "The event is missing \"/to\"!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        try {
            String description = parsedEvent[0];
            LocalDateTime from = parseToLocalDateTime(parsedStartEndTime[0]);
            LocalDateTime to = parseToLocalDateTime(parsedStartEndTime[1]);
            return new AddEventCommand(description, from, to);
        } catch (DateTimeParseException e) {
            String errorDetail = "The format of date and time entered is invalid!";
            String example = "Example: event project meeting /from 2025-04-07 1230 /to 2025-04-07 1330";
            throw new MomoException(errorDetail + "\n" + errorFix + "\n" + example);
        }
    }


    /**
     * Parses the task index from a command input string.
     *
     * @param components the user input split into command and index.
     * @param command the command name (for error messages).
     * @return the zero-based task index.
     * @throws MomoException if the index is missing or not a valid integer.
     */
    private static int parseTaskIndex(String[] components, String command) throws MomoException {
        if (components.length < 2) {
            String errorDetail = "The task number to " + command + " is not provided!";
            String errorFix = "Fix: Try \"" + command + " <task number>\" instead!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
        try {
            return Integer.parseInt(components[1]) - 1;
        } catch (NumberFormatException e) {
            String errorDetail = "The task number provided is not an integer!";
            String errorFix = "Fix: Try \"" + command + " <integer>\" instead!";
            throw new MomoException(errorDetail + "\n" + errorFix);
        }
    }

    /**
     * Parses a "delete" command into a {@link DeleteCommand}.
     *
     * @param components the user input split into command and index.
     * @return a new {@link DeleteCommand}.
     * @throws MomoException if the index is missing or invalid.
     */
    private static Command parseDelete(String[] components) throws MomoException {
        return new DeleteCommand(parseTaskIndex(components, "delete"));
    }

    /**
     * Parses a "mark" command into a {@link MarkCommand}.
     *
     * @param components the user input split into command and index.
     * @return a new {@link MarkCommand}.
     * @throws MomoException if the index is missing or invalid.
     */
    private static Command parseMark(String[] components) throws MomoException {
        return new MarkCommand(parseTaskIndex(components, "mark"));
    }

    /**
     * Parses an "unmark" command into an {@link UnmarkCommand}.
     *
     * @param components the user input split into command and index.
     * @return a new {@link UnmarkCommand}.
     * @throws MomoException if the index is missing or invalid.
     */
    private static Command parseUnmark(String[] components) throws MomoException {
        return new UnmarkCommand(parseTaskIndex(components, "unmark"));
    }

    /**
     * Throws an error for an unrecognized command.
     *
     * @param trimmedInput the trimmed user input.
     * @return never returns; always throws {@link MomoException}.
     * @throws MomoException indicating the command is invalid.
     */
    private static Command parseInvalid(String trimmedInput) throws MomoException {
        String errorDetail = "\"" + trimmedInput + "\" is not a valid command!";
        String errorFix = "Fix: Type 'help' to see the list of valid commands!";
        throw new MomoException(errorDetail + "\n" + errorFix);
    }

    /**
     * Converts a trimmed user input string into the corresponding {@link Command}.
     * Throws {@link MomoException} for invalid input or format errors.
     *
     * @param trimmedInput the trimmed user input string.
     * @return the {@link Command} corresponding to the user input.
     * @throws MomoException if the input is invalid, incomplete, or formatted incorrectly.
     */
    public static Command parseToCommand(String trimmedInput) throws MomoException {
        String[] components = trimmedInput.split(" ", 2);
        String command = components[0];

        switch (command) {
        case "help":
            return parseHelp();
        case "bye":
            return parseBye();
        case "list":
            return parseList();
        case "todo":
            return parseTodo(components);
        case "find":
            return parseFind(components);
        case "deadline":
            return parseDeadline(components);
        case "event":
            return parseEvent(components);
        case "delete":
            return parseDelete(components);
        case "mark":
            return parseMark(components);
        case "unmark":
            return parseUnmark(components);
        default:
            return parseInvalid(trimmedInput);
        }
    }

    /**
     * Converts a saved task string into a {@link Task} object.
     *
     * @param line the saved task string in the format used by storage.
     * @return the {@link Task} object represented by the line.
     * @throws MomoException if the task string is malformed or contains invalid data.
     */
    public static Task parseToTask(String line) throws MomoException {
        try {
            String[] components = line.split(" \\| ");
            String taskType = components[0];
            boolean isDone = components[1].equals("1");
            String description = components[2];

            switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String by = components[3];
                return new Deadline(description, parseToLocalDateTime(by), isDone);
            case "E":
                String from = components[3];
                String to = components[4];
                return new Event(description, parseToLocalDateTime(from), parseToLocalDateTime(to), isDone);
            default:
                throw new MomoException("Invalid task type found!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MomoException("Missing data from tasks!");
        } catch (DateTimeParseException e) {
            throw new MomoException("Format of date and time is invalid!");
        }
    }
}
