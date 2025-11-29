package seedu.duke.parser;

import seedu.duke.command.AddDeadlineCommand;
import seedu.duke.command.AddEventCommand;
import seedu.duke.command.AddTodoCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.UnmarkCommand;
import seedu.duke.exception.DukeException;

/**
 * Parses raw user input into {@link Command} objects.
 * <p>
 * Acts as the central command interpreter for Chatnius. Converts strings entered by the user into
 * concrete command objects for execution. Throws {@link DukeException} if the input does not match
 * any known command format.
 * </p>
 */
public final class Parser {

    /** String delimiter used to separate task name and deadline. */
    private static final String BY_DELIM = " /by ";

    /** String delimiter used to separate task name and event start time. */
    private static final String FROM_DELIM = " /from ";

    /** String delimiter used to separate event start and end times. */
    private static final String TO_DELIM = " /to ";

    /** Error message shown when the input is empty. */
    private static final String MSG_EMPTY = "Please enter a command.";

    /** Error message shown when a todo task has no description. */
    private static final String MSG_TODO_EMPTY = "Todo description cannot be empty!";

    /** Error message shown when a deadline command format is invalid. */
    private static final String MSG_DEADLINE_FMT =
            "Invalid deadline format! Use: deadline <task> /by <time>.";

    /** Error message shown when an event command format is invalid. */
    private static final String MSG_EVENT_FMT =
            "Invalid event format! Use: event <task> /from <start> /to <end>.";

    /** Error message template for commands requiring an integer argument. */
    private static final String MSG_NUM_REQUIRED =
            "Please provide a valid number after '%s'.";

    /** Error message shown when the command keyword is unrecognized. */
    private static final String MSG_UNKNOWN = "Unknown command.";

    /**
     * Private constructor to prevent instantiation.
     * <p>This class is a utility class and should not be instantiated.</p>
     */
    private Parser() {
        // Utility class.
    }

    /**
     * Parses arguments for a deadline command and returns an {@link AddDeadlineCommand}.
     *
     * @param args Arguments string following the {@code deadline} keyword.
     * @return An {@link AddDeadlineCommand} representing the parsed input.
     * @throws DukeException If the arguments are missing or in the wrong format.
     */
    private static Command parseDeadline(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException(MSG_DEADLINE_FMT);
        }
        final int pos = args.indexOf(BY_DELIM);
        if (pos < 0) {
            throw new DukeException(MSG_DEADLINE_FMT);
        }

        final String name = args.substring(0, pos).trim();
        final String by = args.substring(pos + BY_DELIM.length()).trim();
        if (name.isEmpty() || by.isEmpty()) {
            throw new DukeException(MSG_DEADLINE_FMT);
        }

        return new AddDeadlineCommand(name, by);
    }

    /**
     * Parses arguments for an event command and returns an {@link AddEventCommand}.
     *
     * @param args Arguments string following the {@code event} keyword.
     * @return An {@link AddEventCommand} representing the parsed input.
     * @throws DukeException If the arguments are missing or in the wrong format.
     */
    private static Command parseEvent(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException(MSG_EVENT_FMT);
        }
        final int posFrom = args.indexOf(FROM_DELIM);
        if (posFrom < 0) {
            throw new DukeException(MSG_EVENT_FMT);
        }
        final int posTo = args.indexOf(TO_DELIM, posFrom + FROM_DELIM.length());
        if (posTo < 0) {
            throw new DukeException(MSG_EVENT_FMT);
        }

        final String name = args.substring(0, posFrom).trim();
        final String from = args.substring(posFrom + FROM_DELIM.length(), posTo).trim();
        final String to = args.substring(posTo + TO_DELIM.length()).trim();
        if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException(MSG_EVENT_FMT);
        }

        return new AddEventCommand(name, from, to);
    }

    /**
     * Parses a line of user input into a {@link Command}.
     * <p>
     * Identifies the keyword of the command, extracts arguments if present, and delegates to
     * specific parsers when needed.
     * </p>
     *
     * @param line Raw user input (may be {@code null}).
     * @return A concrete {@link Command} to execute.
     * @throws DukeException If the command is invalid or arguments are missing.
     */
    public static Command parse(String line) throws DukeException {
        final String s = (line == null) ? "" : line.trim();
        if (s.isEmpty()) {
            throw new DukeException(MSG_EMPTY);
        }

        final int sp = s.indexOf(' ');
        final String cmd = (sp == -1) ? s : s.substring(0, sp);
        final String args = (sp == -1) ? "" : s.substring(sp + 1).trim();

        switch (cmd.toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (args.isEmpty()) {
                throw new DukeException(MSG_TODO_EMPTY);
            }
            return new AddTodoCommand(args);
        case "deadline":
            return parseDeadline(args);
        case "event":
            return parseEvent(args);
        case "mark":
            return new MarkCommand(parseIndex(args, "mark"));
        case "unmark":
            return new UnmarkCommand(parseIndex(args, "unmark"));
        case "delete":
            return new DeleteCommand(parseIndex(args, "delete"));
        case "find":
            final String keyword = args.trim();
            if (keyword.isEmpty()) {
                throw new DukeException("Keyword cannot be empty. Usage: find <keyword>.");
            }
            return new FindCommand(keyword);
        default:
            throw new DukeException(MSG_UNKNOWN);
        }
    }

    /**
     * Parses a single 1-based index argument for commands such as mark, unmark, or delete.
     *
     * @param arg   Raw argument string.
     * @param which Name of the command (for use in error messages).
     * @return Parsed index (1-based).
     * @throws DukeException If {@code arg} is not a valid integer.
     */
    private static int parseIndex(String arg, String which) throws DukeException {
        try {
            return Integer.parseInt(arg.trim());
        } catch (NumberFormatException e) {
            throw new DukeException(String.format(MSG_NUM_REQUIRED, which));
        }
    }
}
