package cate.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cate.command.AddCommand;
import cate.command.Command;
import cate.command.DeleteCommand;
import cate.command.ExitCommand;
import cate.command.FindCommand;
import cate.command.ListCommand;
import cate.command.MarkCommand;
import cate.command.UndoCommand;
import cate.command.UnmarkCommand;
import cate.exception.CateException;
import cate.exception.InvalidDeadlineException;
import cate.exception.InvalidEventException;
import cate.exception.InvalidIndexException;
import cate.exception.MissingDescriptionException;
import cate.exception.UnknownCommandException;
import cate.task.Deadline;
import cate.task.Event;
import cate.task.TaskList;
import cate.task.Todo;
import cate.ui.Cate;

/**
 * Handles parsing and execution of user commands in the Cate application.
 * <p>
 * The {@code Parser} interprets user input, updates the {@link TaskList}, and
 * produces the corresponding {@link Command} objects. It throws specific
 * subclasses of {@link CateException} for different types of user input errors.
 * </p>
 *
 * <p>Exceptions thrown include:</p>
 * <ul>
 *   <li>{@link UnknownCommandException} - if the input command is not recognised</li>
 *   <li>{@link InvalidIndexException} - if a numeric index argument is missing or invalid</li>
 *   <li>{@link MissingDescriptionException} - if a task description is missing</li>
 *   <li>{@link InvalidDeadlineException} - if a deadline task is missing or has invalid date/time</li>
 *   <li>{@link InvalidEventException} - if an event task is missing /from or /to times</li>
 * </ul>
 */
public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a raw user input string into a corresponding {@link Command}.
     *
     * @param input the raw user input
     * @param cate the {@link Cate} instance providing command manager access
     * @return a {@link Command} representing the parsed input
     * @throws UnknownCommandException if the command is not recognised
     * @throws InvalidIndexException if a required index is missing or invalid
     * @throws MissingDescriptionException if a task description is missing
     * @throws InvalidDeadlineException if a deadline is missing or invalid
     * @throws InvalidEventException if an event is missing /from or /to
     */
    public static Command parse(String input, Cate cate) throws CateException {
        String[] tokens = input.trim().split(" ", 2);
        String command = tokens[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIndex(tokens));
        case "unmark":
            return new UnmarkCommand(parseIndex(tokens));
        case "delete":
            return new DeleteCommand(parseIndex(tokens));
        case "find":
            return new FindCommand(requireArgument(tokens, "find"));
        case "undo":
            return new UndoCommand(cate.getCommandManager());
        case "todo":
            return parseTodo(tokens);
        case "deadline":
            return parseDeadline(tokens);
        case "event":
            return parseEvent(tokens);
        default:
            throw new UnknownCommandException(command);
        }
    }

    /**
     * Extracts and parses a 1-based index from a token array.
     *
     * @param tokens the split user input tokens, where the second token should contain an integer index
     * @return a zero-based index corresponding to the parsed number
     * @throws InvalidIndexException if the index is missing or cannot be parsed as a positive integer
     */
    private static int parseIndex(String[] tokens) throws InvalidIndexException {
        if (tokens.length < 2) {
            throw new InvalidIndexException();
        }
        int val = Integer.parseInt(tokens[1]) - 1;
        if (val < 0) {
            throw new InvalidIndexException();
        }
        return val;
    }

    /**
     * Ensures that a command requiring arguments has a non-empty argument string.
     *
     * @param tokens the split user input tokens
     * @param cmd the name of the command (used in error messages)
     * @return the non-empty argument string
     * @throws MissingDescriptionException if the argument is missing or empty
     */
    private static String requireArgument(String[] tokens, String cmd) throws MissingDescriptionException {
        if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
            throw new MissingDescriptionException(cmd);
        }
        return tokens[1].trim();
    }

    /**
     * Parses a {@code todo} command into an {@link AddCommand} with a {@link Todo}.
     *
     * @param tokens the split user input tokens
     * @return an {@link AddCommand} containing a {@link Todo} task
     * @throws MissingDescriptionException if the description is missing
     */
    private static Command parseTodo(String[] tokens) throws CateException {
        String description = requireArgument(tokens, "todo");
        return new AddCommand(new Todo(description));
    }

    /**
     * Parses a {@code deadline} command into an {@link AddCommand} with a {@link Deadline}.
     *
     * @param tokens the split user input tokens
     * @return an {@link AddCommand} containing a {@link Deadline} task
     * @throws MissingDescriptionException if the description is missing
     * @throws InvalidDeadlineException if the deadline is missing or invalid
     */
    private static Command parseDeadline(String[] tokens) throws InvalidDeadlineException, MissingDescriptionException {
        String text = requireArgument(tokens, "deadline");
        String[] parts = text.split(" /by ", 2);
        if (parts.length < 2) {
            throw new InvalidDeadlineException("Missing /by for deadline: " + text);
        }

        try {
            LocalDateTime deadline = LocalDateTime.parse(parts[1], formatter);
            return new AddCommand(new Deadline(parts[0], deadline));
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException("Deadline date/time should be in YYYY-MM-DD HHMM format!");
        }
    }

    /**
     * Parses an {@code event} command into an {@link AddCommand} with an {@link Event}.
     *
     * @param tokens the split user input tokens
     * @return an {@link AddCommand} containing an {@link Event} task
     * @throws MissingDescriptionException if the description is missing
     * @throws InvalidEventException if the /from or /to times are missing or invalid
     */
    private static Command parseEvent(String[] tokens) throws InvalidEventException, MissingDescriptionException {
        String text = requireArgument(tokens, "event");
        String[] parts = text.split(" /from ", 2);
        if (parts.length < 2) {
            throw new InvalidEventException();
        }
        String[] time = parts[1].split(" /to ", 2);
        if (time.length < 2) {
            throw new InvalidEventException();
        }
        return new AddCommand(new Event(parts[0], time[0], time[1]));
    }
}
