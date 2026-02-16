// parser/Parser.java
package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.CommandType;
import command.Command;
import command.AddTodoCommand;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SortCommand;
import exceptions.AlfredException;

import task.TaskList;

/**
 * @author Anand Bala
 * The Parser class provides a static method to be able to read and clean raw user input, such that it
 * can be translated into commands and subsequently be executed.
 */

public final class Parser {

    /**
     * Parses a raw user input line into a concrete {@link command.Command}.
     * The first token is matched case-insensitively to a {@link command.CommandType}.
     * Arguments are split using required delimiters (e.g., {@code /by}, {@code /from}, {@code /to}).
     * Dates must be in ISO format {@code YYYY-MM-DD}. Task indices are read as positive integers
     * (1-based from the user) and converted to 0-based for internal use.
     *
     * @param line Raw input line; may contain leading/trailing or extra internal whitespace.
     * @return A concrete {@link command.Command} to execute.
     * @throws exceptions.AlfredException If the input is empty, the command is unknown, a required
     *                                    delimiter is missing, the task index is invalid, or a date
     *                                    is not in {@code YYYY-MM-DD} format.
     */
    public static Command parse(String line) throws AlfredException {
        if (line == null || line.trim().isEmpty()) {
            throw new AlfredException("Empty input, Master Bruce.");
        }

        String[] parts = line.trim().split("\\s+", 2);
        String word = parts[0].toUpperCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        CommandType type;
        try {
            type = CommandType.valueOf(word);
        } catch (IllegalArgumentException e) {
            throw new AlfredException("I don’t understand that command: \"" + parts[0] + "\"");
        }

        switch (type) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            return new MarkCommand(parseIndex(args), "mark");

        case UNMARK:
            return new MarkCommand(parseIndex(args), "unmark");

        case TODO:
            if (args.isEmpty()) throw new AlfredException("Usage: todo <description>");
            return new AddTodoCommand(args);

        case DEADLINE:
            String[] p = splitOnce(args, "/by");
            LocalDate by = parseDate(p[1], "Usage: deadline <desc> /by YYYY-MM-DD");
            return new AddDeadlineCommand(p[0].trim(), by);

        case EVENT:
            String[] pFrom = splitOnce(args, "/from");
            String[] pTo   = splitOnce(pFrom[1], "/to");
            LocalDate from = parseDate(pTo[0], "Usage: event <desc> /from YYYY-MM-DD /to YYYY-MM-DD");
            LocalDate to   = parseDate(pTo[1], "Usage: event <desc> /from YYYY-MM-DD /to YYYY-MM-DD");
            return new AddEventCommand(pFrom[0].trim(), from, to);

        case FIND:
            if (args.isEmpty()) {
                throw new AlfredException("Usage: find <keyword>");
            }
            return new FindCommand(args);
        case DELETE:
            return new DeleteCommand(parseIndex(args));
        case SORT:
            return new SortCommand();
        default:
            throw new AlfredException("Good Evening, Master Bruce. Please use the commands to continue.");
        }
    }

    // --- helpers ---
    private static int parseIndex(String arg) throws AlfredException {
        if (arg.isEmpty()) throw new AlfredException("Please specify a task number, e.g., \"mark 1\".");
        try {
            int idx1 = Integer.parseInt(arg.trim());
            if (idx1 <= 0) throw new NumberFormatException();
            return idx1 - 1; // convert to 0-based
        } catch (NumberFormatException e) {
            throw new AlfredException("Task number must be a positive integer.");
        }
    }

    private static String[] splitOnce(String src, String delimiter) throws AlfredException {
        String[] p = src.split("\\s*" + java.util.regex.Pattern.quote(delimiter) + "\\s*", 2);
        if (p.length < 2) {
            throw new AlfredException("Missing " + delimiter + ". Example: " + delimiter + " YYYY-MM-DD");
        }
        return p;
    }

    private static LocalDate parseDate(String raw, String usage) throws AlfredException {
        try {
            return LocalDate.parse(raw.trim());
        } catch (DateTimeParseException e) {
            throw new AlfredException(usage + " (date must be YYYY-MM-DD)");
        }
    }
}
