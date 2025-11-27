package mikey.parser;

import mikey.exception.MikeyException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public enum Command { LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, FIND, TAG }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static class Arguments {
        public String description;
        public Integer index;
        public LocalDateTime byRaw;
        public LocalDateTime fromRaw;
        public LocalDateTime toRaw;
        public String keyword;
        public String label;
    }

    public static class ParseResult {
        public Command command;
        public Arguments arguments;
        public boolean isError;
        public String errorMessage;
    }

    private static ParseResult error(String msg) {
        ParseResult e = new ParseResult();
        e.isError = true;
        e.errorMessage = msg;
        return e;
    }

    /**
     * Returns a parsed version of the input
     * If input is null, return an error
     *
     * @param input user input
     * @return parse result
     */
    public ParseResult parse(String input) {
        // Handle null input gracefully instead of assertion
        if (input == null) {
            return error("Please input a valid command");
        }

        // Normalize input
        String normalizedInput = normalizeInput(input);

        ParseResult r = new ParseResult();
        if (normalizedInput == null || normalizedInput.isEmpty()) {
            return error("Please input a valid command");
        }
        String[] parts = normalizedInput.split("\\s+", 2);
        String command = parts[0].toLowerCase(); // Make case insensitive
        String args = (parts.length == 2) ? parts[1] : "";
        if (!command.isEmpty()) {
            switch (command) {
            case "bye":
                handleBye(r, command, args);
                break;
            case "list":
                handleList(r, command, args);
                break;
            case "todo":
                try {
                    handleTodo(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            case "deadline":
                if (args.isEmpty()) {
                    return error("The description of a deadline cannot be empty! " + "Use: deadline <desc> /by <time>");
                }
                try {
                    handleDeadline(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            case "event":
                try {
                    handleEvent(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            case "mark":
                try {
                    handleMark(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    handleUnmark(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            case "delete":
                try {
                    handleDelete(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            case "find":
                try {
                    handleFind(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            case "tag":
                try {
                    handleTag(r, command, args);
                } catch (MikeyException e) {
                    return error(e.getMessage());
                }
                break;
            default:
                return error("Unknown command: '" + command + "'");
            }
            if (r.command == null) {
                return error("Please input a valid command");
            }
        }
        return r;
    }

    private void handleBye(ParseResult r, String command, String args) {
        r.command = Command.BYE;
    }

    private void handleList(ParseResult r, String command, String args) {
        r.command = Command.LIST;
        r.arguments = new Arguments();
    }

    /**
     * Validates and normalizes input string
     */
    //Claude AI was used to form the idea of this method
    private String normalizeInput(String input) {
        if (input == null) {
            return null;
        }
        // Remove leading/trailing whitespace and normalize multiple spaces
        return input.trim().replaceAll("\\s+", " ");
    }

    private void handleTodo(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("Todo description cannot be empty! Use: todo <description>");
        }

        if (args.length() > 200) {
            throw new MikeyException("Todo description is too long (max 200 characters)");
        }

        r.command = Command.TODO;
        r.arguments = new Arguments();
        r.arguments.description = args;
    }

    private void handleDeadline(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("The description of a deadline cannot be empty! "
                    + "Use: deadline <desc> /by <time>");
        }

        // Check for multiple /by keywords
        String[] byParts = args.split("/by");
        if (byParts.length > 2) {
            throw new MikeyException("Multiple '/by' keywords found. Use only one '/by' in the command.");
        }

        r.command = Command.DEADLINE;
        r.arguments = new Arguments();
        String[] descDeadline = args.split("\\s*/by\\s+", 2);
        if (descDeadline.length < 2) {
            throw new MikeyException("A deadline needs '/by <time>'. Use: deadline <desc> /by <time>");
        }
        try {
            LocalDateTime by = LocalDateTime.parse(descDeadline[1].trim(), FORMATTER);
            r.arguments.description = descDeadline[0].trim();
            r.arguments.byRaw = by;
        } catch (DateTimeException e) {
            throw new MikeyException("Please use format D/M/YYYY HHMM (e.g. 2/12/2019 1800)");
        }
    }

    private void handleEvent(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("The description of an event cannot be empty! "
                    + "Use: event <desc> /from <start> /to <end>");
        }
        r.command = Command.EVENT;
        r.arguments = new Arguments();
        String[] descEvent = args.split("\\s*/from\\s+", 2);
        if (descEvent.length < 2) {
            throw new MikeyException("An event needs '/from <start>. " + "Use: event <desc> /from <start> /to <end>");
        }
        String[] timesEvent = descEvent[1].split("\\s*/to\\s+", 2);
        if (timesEvent.length < 2) {
            throw new MikeyException("An event needs a start time and end time. "
                    + "Use: event <desc> /from <start> /to <end>");
        }
        try {
            LocalDateTime from = LocalDateTime.parse(timesEvent[0].trim(), FORMATTER);
            LocalDateTime to = LocalDateTime.parse(timesEvent[1].trim(), FORMATTER);

            // Validate that end time is after start time
            if (!to.isAfter(from)) {
                throw new MikeyException("Event end time must be after start time");
            }

            r.arguments.description = descEvent[0].trim();
            r.arguments.fromRaw = from;
            r.arguments.toRaw = to;
        } catch (DateTimeException e) {
            throw new MikeyException("Please use format D/M/YYYY HHMM (e.g. 2/12/2019 1800)");
        }
    }

    private void handleMark(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("Provide a valid task number! E.g. mark 1");
        }
        r.command = Command.MARK;
        r.arguments = new Arguments();
        try {
            r.arguments.index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new MikeyException("Provide a valid task number! E.g. mark 1");
        }
    }

    private void handleUnmark(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("Provide a valid task number! E.g. unmark 1");
        }
        r.command = Command.UNMARK;
        r.arguments = new Arguments();
        try {
            r.arguments.index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new MikeyException("Provide a valid task number! E.g. mark 1");
        }
    }

    private void handleDelete(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("Provide a valid task number! E.g. delete 1");
        }
        r.command = Command.DELETE;
        r.arguments = new Arguments();
        try {
            r.arguments.index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new MikeyException("Provide a valid task number! E.g. delete 1");
        }
    }

    private void handleFind(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("Provide a keyword! E.g. find book");
        }
        r.command = Command.FIND;
        r.arguments = new Arguments();
        String[] words = args.split("\\s+");
        if (words.length > 1) {
            throw new MikeyException("Provide only ONE keyword! E.g. find book");
        }
        r.arguments.keyword = words[0];
    }

    private void handleTag(ParseResult r, String command, String args) throws MikeyException {
        if (args.isEmpty()) {
            throw new MikeyException("Provide a tag number! E.g. tag 1 lesson");
        }
        r.command = Command.TAG;
        r.arguments = new Arguments();
        String[] arr = args.split("\\s+", 2);
        if (arr.length < 2) {
            throw new MikeyException("Provide a tag label! E.g. tag 1 lesson");
        }
        if (arr.length > 2) {
            throw new MikeyException("Tag label can only be ONE word! E.g. tag 1 lesson");
        }
        try {
            r.arguments.index = Integer.parseInt(arr[0]);
            r.arguments.label = arr[1];
        } catch (NumberFormatException e) {
            throw new MikeyException("Provide a valid task number! E.g. tag 1 lesson");
        }
    }
}
