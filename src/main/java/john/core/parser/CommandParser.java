package john.core.parser;

import john.core.command.*;
import john.core.exception.ParseException;
import john.util.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public final class CommandParser {
    public CommandParser() {
    }

    public static Command parse(String line) throws ParseException {
        if (line == null || line.isBlank()) throw new ParseException("Empty command.");
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
            default -> throw new ParseException("Unknown command: " + verb +
                    ". Try: list, mark, unmark, delete, todo, deadline, event, bye");
        };
    }

    private static Command parseMark(String rest) throws ParseException {
        if (rest.isEmpty()) throw new ParseException("Usage: mark <task-number>");
        int oneBased;
        try {
            oneBased = Integer.parseInt(rest);
        } catch (NumberFormatException e) {
            throw new ParseException("Usage: mark <task-number>");
        }
        return new MarkCommand(oneBased);
    }

    private static Command parseUnmark(String rest) throws ParseException {
        if (rest.isEmpty()) throw new ParseException("Usage: unmark <task-number>");
        int oneBased;
        try {
            oneBased = Integer.parseInt(rest);
        } catch (NumberFormatException e) {
            throw new ParseException("Usage: unmark <task-number>");
        }
        return new UnmarkCommand(oneBased);
    }

    private static Command parseDelete(String rest) throws ParseException {
        if (rest.isEmpty()) throw new ParseException("Usage: delete <task-number>");
        int oneBased;
        try {
            oneBased = Integer.parseInt(rest);
        } catch (NumberFormatException e) {
            throw new ParseException("Usage: delete <task-number>");
        }
        return new DeleteCommand(oneBased);
    }

    private static Command parseTodo(String rest) throws ParseException {
        if (rest.isEmpty()) throw new ParseException("Usage: todo <description>");
        return new AddTodoCommand(rest);
    }

    private static Command parseDeadline(String rest) throws ParseException {
        int byPos = rest.lastIndexOf(" /by ");
        if (byPos == -1) throw new ParseException("Usage: deadline <description> /by <when>");
        String desc = rest.substring(0, byPos).trim();
        String when = rest.substring(byPos + 5).trim();
        if (desc.isEmpty() || when.isEmpty())
            throw new ParseException("Usage: deadline <description> /by <when>");
        LocalDateTime by;
        try {
            by = DateTimeParser.parseDateTime(when);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date/time. Expected: " + DateTimeParser.HUMAN_PATTERN);
        }
        return new AddDeadlineCommand(desc, by);
    }

    private static Command parseEvent(String rest) throws ParseException {
        int fromPos = rest.lastIndexOf(" /from ");
        int toPos = rest.lastIndexOf(" /to ");
        if (fromPos == -1 || toPos == -1 || toPos <= fromPos)
            throw new ParseException("Usage: event <description> /from <start> /to <end>");

        String desc = rest.substring(0, fromPos).trim();
        String start = rest.substring(fromPos + 7, toPos).trim();
        String end = rest.substring(toPos + 5).trim();
        if (desc.isEmpty() || start.isEmpty() || end.isEmpty())
            throw new ParseException("Usage: event <description> /from <start> /to <end>");

        LocalDateTime from, to;
        try {
            from = DateTimeParser.parseDateTime(start);
            to = DateTimeParser.parseDateTime(end);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date/time. Expected: " + DateTimeParser.HUMAN_PATTERN);
        }
        if (to.isBefore(from))
            throw new ParseException("End time must be after start time.");
        return new AddEventCommand(desc, from, to);
    }
}