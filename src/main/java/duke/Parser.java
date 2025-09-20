package duke;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Represents the different types of commands.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, SORT, INVALID
    }

    /**
     * Represents a parsed command with its type and relevant data.
     */
    public static class Command {
        private CommandType type;
        private String taskNumber;
        private String description;
        private String deadline;
        private String from;
        private String to;

        public Command(CommandType type) {
            this.type = type;
        }

        public CommandType getType() {
            return type;
        }

        public String getTaskNumber() {
            return taskNumber;
        }

        public String getDescription() {
            return description;
        }

        public String getDeadline() {
            return deadline;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }
    }

    /**
     * Parses the user input and returns a Command object.
     * @param input The user input string
     * @return A Command object representing the parsed command
     */
    public static Command parse(String input) {
        String trimmedInput = input.trim();

        if (isSimpleCommand(trimmedInput)) {
            return parseSimpleCommand(trimmedInput);
        } else if (isTaskNumberCommand(trimmedInput)) {
            return parseTaskNumberCommand(trimmedInput);
        } else if (isTodoCommand(trimmedInput)) {
            return parseTodoCommand(trimmedInput);
        } else if (isDeadlineCommand(trimmedInput)) {
            return parseDeadlineCommand(trimmedInput);
        } else if (isEventCommand(trimmedInput)) {
            return parseEventCommand(trimmedInput);
        } else if (isFindCommand(trimmedInput)) {
            return parseFindCommand(trimmedInput);
        } else {
            return new Command(CommandType.INVALID);
        }
    }

    /**
     * Checks if the input is a simple command (bye, list, sort).
     * @param input The trimmed user input
     * @return true if it's a simple command, false otherwise
     */
    private static boolean isSimpleCommand(String input) {
        return input.equals("bye") || input.equals("list") || input.equals("sort");
    }

    /**
     * Parses simple commands that don't require additional parameters.
     * @param input The trimmed user input
     * @return A Command object for the simple command
     */
    private static Command parseSimpleCommand(String input) {
        if (input.equals("bye")) {
            return new Command(CommandType.BYE);
        } else if (input.equals("list")) {
            return new Command(CommandType.LIST);
        } else if (input.equals("sort")) {
            return new Command(CommandType.SORT);
        }
        return new Command(CommandType.INVALID);
    }

    /**
     * Checks if the input is a task number command (mark, unmark, delete).
     * @param input The trimmed user input
     * @return true if it's a task number command, false otherwise
     */
    private static boolean isTaskNumberCommand(String input) {
        return input.startsWith("mark ") || input.startsWith("unmark ") || input.startsWith("delete ");
    }

    /**
     * Parses commands that require a task number.
     * @param input The trimmed user input
     * @return A Command object for the task number command
     */
    private static Command parseTaskNumberCommand(String input) {
        Command cmd;
        if (input.startsWith("mark ")) {
            cmd = new Command(CommandType.MARK);
            cmd.taskNumber = input.substring(5).trim();
        } else if (input.startsWith("unmark ")) {
            cmd = new Command(CommandType.UNMARK);
            cmd.taskNumber = input.substring(7).trim();
        } else if (input.startsWith("delete ")) {
            cmd = new Command(CommandType.DELETE);
            cmd.taskNumber = input.substring(7).trim();
        } else {
            return new Command(CommandType.INVALID);
        }
        return cmd;
    }

    /**
     * Checks if the input is a todo command.
     * @param input The trimmed user input
     * @return true if it's a todo command, false otherwise
     */
    private static boolean isTodoCommand(String input) {
        return input.startsWith("todo");
    }

    /**
     * Parses the todo command.
     * @param input The trimmed user input
     * @return A Command object for the todo command
     */
    private static Command parseTodoCommand(String input) {
        Command cmd = new Command(CommandType.TODO);
        cmd.description = input.length() > 4 ? input.substring(4).trim() : "";
        return cmd;
    }

    /**
     * Checks if the input is a deadline command.
     * @param input The trimmed user input
     * @return true if it's a deadline command, false otherwise
     */
    private static boolean isDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    /**
     * Parses the deadline command.
     * @param input The trimmed user input
     * @return A Command object for the deadline command
     */
    private static Command parseDeadlineCommand(String input) {
        String body = input.length() > 8 ? input.substring(8).trim() : "";
        int byPos = body.indexOf("/by");
        if (byPos != -1) {
            Command cmd = new Command(CommandType.DEADLINE);
            cmd.description = body.substring(0, byPos).trim();
            cmd.deadline = body.substring(byPos + 3).trim();
            return cmd;
        } else {
            return new Command(CommandType.INVALID);
        }
    }

    /**
     * Checks if the input is an event command.
     * @param input The trimmed user input
     * @return true if it's an event command, false otherwise
     */
    private static boolean isEventCommand(String input) {
        return input.startsWith("event");
    }

    /**
     * Parses the event command.
     * @param input The trimmed user input
     * @return A Command object for the event command
     */
    private static Command parseEventCommand(String input) {
        String body = input.length() > 5 ? input.substring(5).trim() : "";
        int fromPos = body.indexOf("/from");
        int toPos = body.indexOf("/to");
        if (fromPos != -1 && toPos != -1 && toPos > fromPos) {
            Command cmd = new Command(CommandType.EVENT);
            cmd.description = body.substring(0, fromPos).trim();
            cmd.from = body.substring(fromPos + 5, toPos).trim();
            cmd.to = body.substring(toPos + 3).trim();
            return cmd;
        } else {
            return new Command(CommandType.INVALID);
        }
    }

    /**
     * Checks if the input is a find command.
     * @param input The trimmed user input
     * @return true if it's a find command, false otherwise
     */
    private static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }

    /**
     * Parses the find command.
     * @param input The trimmed user input
     * @return A Command object for the find command
     */
    private static Command parseFindCommand(String input) {
        Command cmd = new Command(CommandType.FIND);
        cmd.description = input.length() > 4 ? input.substring(4).trim() : "";
        return cmd;
    }
}
