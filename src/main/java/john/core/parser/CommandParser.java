package john.core.parser;

import john.core.command.*;
import john.core.exception.ParseException;
import john.util.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Parses raw user input into executable Command instances.
 * <p>
 * Responsibilities:
 * - Tokenize the input line into a verb and the remainder.
 * - Dispatch to verb-specific parsers that validate arguments and build commands.
 * - Produce helpful usage errors via ParseException when input is invalid.
 * <p>
 * Supported commands and usage:
 * - bye
 * - list
 * - mark <task-number>
 * - unmark <task-number>
 * - delete <task-number>
 * - todo <description>
 * - deadline <description> /by <when>
 * - event <description> /from <start> /to <end>
 * - find <substring>
 * <p>
 * Date/time format expected by deadline and event:
 * dd/MM/yyyy HH:mm:ss
 */
public final class CommandParser {
    public CommandParser() {
    }

    /**
     * Parses a full input line into a Command.
     * <p>
     * Splits on whitespace into: verb and the rest (verbatim remainder).
     * Dispatches to a verb-specific parser.
     *
     * @param line raw user input
     * @return a concrete Command ready to execute
     * @throws ParseException if the verb is unknown or arguments are invalid
     */
    public static Command parse(String line) throws ParseException {
        if (line == null || line.isBlank()) {
            throw new ParseException("Empty command.");
        }
        String[] head = line.trim().split("\\s+", 2);
        String verb = head[0].toLowerCase();
        String rest = head.length > 1 ? head[1].trim() : "";

        return switch (verb) {
            case "bye" -> new ByeCommand();
            case "list" -> new ListCommand();
            case "mark" -> parseMark(rest);
            case "unmark" -> parseUnmark(rest);
            case "delete" -> parseDelete(rest);
            case "todo" -> parseTodo(rest);
            case "deadline" -> parseDeadline(rest);
            case "event" -> parseEvent(rest);
            case "find" -> parseFind(rest);
            case "postpone" -> parsePostpone(rest);

            default ->
                    throw new ParseException("Unknown command: " + verb +
                            ". Try: list, mark, unmark, delete, todo, deadline, event, find, bye, postpone");
        };
    }

    /**
     * Parses: mark <task-number>
     *
     * @param rest remainder after the verb
     * @return a MarkCommand
     * @throws ParseException if the task number is missing or not an integer
     */
    private static Command parseMark(String rest) throws ParseException {
        if (rest.isEmpty()) {
            throw new ParseException("Usage: mark <task-number>");
        }
        int oneBased;
        try {
            oneBased = Integer.parseInt(rest);
        } catch (NumberFormatException e) {
            throw new ParseException("Usage: mark <task-number>");
        }
        return new MarkCommand(oneBased);
    }

    /**
     * Parses: unmark <task-number>
     *
     * @param rest remainder after the verb
     * @return an UnmarkCommand
     * @throws ParseException if the task number is missing or not an integer
     */
    private static Command parseUnmark(String rest) throws ParseException {
        if (rest.isEmpty()) {
            throw new ParseException("Usage: unmark <task-number>");
        }
        int oneBased;
        try {
            oneBased = Integer.parseInt(rest);
        } catch (NumberFormatException e) {
            throw new ParseException("Usage: unmark <task-number>");
        }
        return new UnmarkCommand(oneBased);
    }

    /**
     * Parses: delete <task-number>
     *
     * @param rest remainder after the verb
     * @return a DeleteCommand
     * @throws ParseException if the task number is missing or not an integer
     */
    private static Command parseDelete(String rest) throws ParseException {
        if (rest.isEmpty()) {
            throw new ParseException("Usage: delete <task-number>");
        }
        int oneBased;
        try {
            oneBased = Integer.parseInt(rest);
        } catch (NumberFormatException e) {
            throw new ParseException("Usage: delete <task-number>");
        }
        return new DeleteCommand(oneBased);
    }

    /**
     * Parses: todo <description>
     *
     * @param rest task description
     * @return an AddTodoCommand
     * @throws ParseException if description is empty
     */
    private static Command parseTodo(String rest) throws ParseException {
        if (rest.isEmpty()) {
            throw new ParseException("Usage: todo <description>");
        }
        return new AddTodoCommand(rest);
    }

    /**
     * Parses: deadline <description> /by <when>
     * <p>
     * The <when> must be in the DateTimeParser expected format (dd/MM/yyyy HH:mm:ss).
     *
     * @param rest remainder containing description and by value
     * @return an AddDeadlineCommand
     * @throws ParseException if tokens are missing or the date-time is invalid
     */
    private static Command parseDeadline(String rest) throws ParseException {
        int byPos = rest.lastIndexOf(" /by ");
        if (byPos == -1) {
            throw new ParseException("Usage: deadline <description> /by <when>");
        }
        String desc = rest.substring(0, byPos).trim();
        String when = rest.substring(byPos + 5).trim();
        if (desc.isEmpty() || when.isEmpty()) {
            throw new ParseException("Usage: deadline <description> /by <when>");
        }
        LocalDateTime by;
        try {
            by = DateTimeParser.parseDateTime(when);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date/time. Expected: " + DateTimeParser.HUMAN_PATTERN);
        }
        return new AddDeadlineCommand(desc, by);
    }

    /**
     * Parses: event <description> /from <start> /to <end>
     * <p>
     * Both <start> and <end> must be in the DateTimeParser expected format (dd/MM/yyyy HH:mm:ss),
     * and end must not be before start.
     *
     * @param rest remainder containing description, start and end tokens
     * @return an AddEventCommand
     * @throws ParseException if tokens are missing, date-time values are invalid, or end < start
     */
    private static Command parseEvent(String rest) throws ParseException {
        int fromPos = rest.lastIndexOf(" /from ");
        int toPos = rest.lastIndexOf(" /to ");
        if (fromPos == -1 || toPos == -1 || toPos <= fromPos) {
            throw new ParseException("Usage: event <description> /from <start> /to <end>");
        }
        String desc = rest.substring(0, fromPos).trim();
        String start = rest.substring(fromPos + 7, toPos).trim();
        String end = rest.substring(toPos + 5).trim();
        if (desc.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new ParseException("Usage: event <description> /from <start> /to <end>");
        }
        LocalDateTime from, to;
        try {
            from = DateTimeParser.parseDateTime(start);
            to = DateTimeParser.parseDateTime(end);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date/time. Expected: " + DateTimeParser.HUMAN_PATTERN);
        }
        if (to.isBefore(from)) {
            throw new ParseException("End time must be after start time.");
        }
        return new AddEventCommand(desc, from, to);
    }

    /**
     * Parses: find <substring>
     * <p>
     * Rules:
     * - Requires a non-empty substring.
     * - Matching is performed by FindCommand (case-insensitive).
     *
     * @param rest the substring to search for
     * @return a FindCommand configured with the substring
     * @throws ParseException if the substring is missing
     */
    private static Command parseFind(String rest) throws ParseException {
        if (rest.isEmpty()) {
            throw new ParseException("Usage: find <substring>");
        }
        return new FindCommand(rest);
    }

    private static Command parsePostpone(String rest) throws ParseException {
        // Allowed:
        // 1) postpone <idx> /by <when>
        // 2) postpone <idx> /from <start> /to <end>
        final String USAGE = "Usage: postpone <task-number> /by <when> | postpone <task-number> /from <start> /to <end>";

        if (rest.isEmpty()) {
            throw new ParseException(USAGE);
        }

        String[] head = rest.split("\\s+", 2);
        int oneBased;
        try {
            oneBased = Integer.parseInt(head[0]);
        } catch (NumberFormatException e) {
            throw new ParseException(USAGE);
        }
        String tail = (head.length > 1) ? head[1].trim() : "";
        if (tail.isEmpty()) {
            throw new ParseException(USAGE);
        }

        // Case 1: /by <when>
        if (tail.startsWith("/by ")) {
            String byStr = tail.substring(4).trim();
            // no extra tags allowed
            if (byStr.isEmpty() || byStr.contains(" /from ") || byStr.contains(" /to ")) {
                throw new ParseException(USAGE);
            }
            try {
                LocalDateTime by = DateTimeParser.parseDateTime(byStr);
                return new PostponeCommand(oneBased, by, null, null);
            } catch (java.time.format.DateTimeParseException ex) {
                throw new ParseException("Invalid date/time. Expected: " + DateTimeParser.HUMAN_PATTERN);
            }
        }

        // Case 2: /from <start> /to <end>
        if (tail.startsWith("/from ")) {
            int toPos = tail.indexOf(" /to ");
            if (toPos < 0) {
                throw new ParseException(USAGE);
            }

            String fromStr = tail.substring(6, toPos).trim();
            String toStr   = tail.substring(toPos + 5).trim();
            if (fromStr.isEmpty() || toStr.isEmpty()) {
                throw new ParseException(USAGE);
            }

            try {
                LocalDateTime from = DateTimeParser.parseDateTime(fromStr);
                LocalDateTime to   = DateTimeParser.parseDateTime(toStr);
                return new PostponeCommand(oneBased, null, from, to);
            } catch (java.time.format.DateTimeParseException ex) {
                throw new ParseException("Invalid date/time. Expected: " + DateTimeParser.HUMAN_PATTERN);
            }
        }

        throw new ParseException(USAGE);
    }
}