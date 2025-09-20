package eve.parser;

import java.time.LocalDateTime;
import java.util.Optional;

import eve.util.DateTimeUtil;

public final class Parser {
    private Parser() {
    }

    public enum Command {
        HELP, LIST, TODO, FIND, DEADLINE, EVENT, MARK, PERIOD, UNMARK, DELETE, BYE
    }

    public static Command parseCommand(String full) {
        assert full != null && !full.trim().isEmpty() : "Input should not be null or empty";
        String trimmed = full.trim();
        String head = trimmed.split("\\s+", 2)[0].toLowerCase();
        switch (head) {
            case "help":
                return Command.HELP;
            case "list":
                return Command.LIST;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "delete":
                return Command.DELETE;
            case "bye":
                return Command.BYE;
            case "find":
                return Command.FIND;
            case "period":
                return Command.PERIOD;
            default:
                return null;
        }
    }

    public static String args(String full) {
        String[] parts = full.trim().split("\\s+", 2);
        return parts.length > 1 ? parts[1].trim() : "";
    }

    public static String parseTodoDesc(String args) throws EveException {
        if (args == null || args.trim().isEmpty()) {
            throw new EveException("Oops, I need more info. Usage: todo <description>");
        }
        return args.trim();
    }

    public static int parseIndex(String args, boolean toDone) throws EveException {
        if (args == null || args.trim().isEmpty()) {
            throw new EveException(
                    "Please provide a task number (e.g., \"" + (toDone ? "mark 2" : "unmark 2") + "\").");
        }
        if (!args.matches("\\d+")) {
            throw new EveException("Use a number only, e.g., \"" + (toDone ? "mark 2" : "unmark 2") + "\".");
        }
        return Integer.parseInt(args);
    }

    public static String parseFind(String args) throws EveException {
        if (args == null) {
            args = "";
        }
        String q = args.trim();
        if (q.isEmpty()) {
            throw new EveException("Oops, I need more info. Usage: find <keyword>");
        }
        return q;
    }

    public static int parseDeleteIndex(String args) throws EveException {
        if (args == null || args.trim().isEmpty()) {
            throw new EveException("Use a number only, e.g., \"delete 3\".");
        }
        if (!args.matches("\\d+")) {
            throw new EveException("Use a number only, e.g., \"delete 3\".");
        }
        return Integer.parseInt(args);
    }

    public static DeadlineParts parseDeadline(String args) throws EveException {
        String[] parts = args.trim().split("(?i)\\s*/by\\s+", 2);
        if (parts.length < 2) {
            throw new EveException("Oops, I need more info. Usage: deadline <description> /by <when>");
        }
        String desc = parts[0].trim();
        String when = parts[1].trim();
        if (desc.isEmpty() || when.isEmpty()) {
            throw new EveException("Oops, I need more info. Usage: deadline <description> /by <when>");
        }

        // Extra validation for invalid dates
        if (eve.util.DateTimeUtil.parseDateTime(when).isEmpty()) {
            throw new EveException(
                    "I couldn’t understand that date. Try yyyy-MM-dd or d/M/yyyy HH:mm ♡");
        }

        return new DeadlineParts(desc, when);
    }

    public static EventParts parseEvent(String args) throws EveException {
        String[] first = args.trim().split("(?i)\\s*/from\\s+", 2);
        if (first.length < 2) {
            throw new EveException("Oops, I need more info. Usage: event <description> /from <start> /to <end>");
        }
        String desc = first[0].trim();
        String[] second = first[1].split("(?i)\\s*/to\\s+", 2);
        if (second.length < 2) {
            throw new EveException("Oops, I need more info. Usage: event <description> /from <start> /to <end>");
        }
        String from = second[0].trim();
        String to = second[1].trim();
        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EveException("Oops, I need more info. Usage: event <description> /from <start> /to <end>");
        }

        Optional<LocalDateTime> f = DateTimeUtil.parseDateTime(from);
        Optional<LocalDateTime> t = DateTimeUtil.parseDateTime(to);
        if (f.isPresent() && t.isPresent() && f.get().isAfter(t.get())) {
            throw new EveException("Sorry, that time range looks invalid: start is after end.");
        }

        return new EventParts(desc, from, to);
    }

    public static final class DeadlineParts {
        public final String desc;
        public final String when;

        public DeadlineParts(String d, String w) {
            this.desc = d;
            this.when = w;
        }
    }

    public static final class EventParts {
        public final String desc;
        public final String from;
        public final String to;

        public EventParts(String d, String f, String t) {
            this.desc = d;
            this.from = f;
            this.to = t;
        }
    }

    public static class PeriodParts {
        public final String desc;
        public final String start;
        public final String end;

        public PeriodParts(String d, String s, String e) {
            desc = d;
            start = s;
            end = e;
        }
    }

    public static PeriodParts parsePeriod(String args) {
        String[] parts = args.split("/from|/to");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Usage: period DESC /from START /to END");
        }

        String desc = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new PeriodParts(desc, from, to);
    }
}
