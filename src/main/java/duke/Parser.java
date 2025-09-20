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

        if (trimmedInput.equals("bye")) {
            return new Command(CommandType.BYE);
        } else if (trimmedInput.equals("list")) {
            return new Command(CommandType.LIST);
        } else if (trimmedInput.startsWith("mark ")) {
            Command cmd = new Command(CommandType.MARK);
            cmd.taskNumber = trimmedInput.substring(5).trim();
            return cmd;
        } else if (trimmedInput.startsWith("unmark ")) {
            Command cmd = new Command(CommandType.UNMARK);
            cmd.taskNumber = trimmedInput.substring(7).trim();
            return cmd;
        } else if (trimmedInput.startsWith("delete ")) {
            Command cmd = new Command(CommandType.DELETE);
            cmd.taskNumber = trimmedInput.substring(7).trim();
            return cmd;
        } else if (trimmedInput.startsWith("todo")) {
            Command cmd = new Command(CommandType.TODO);
            cmd.description = trimmedInput.length() > 4 ? trimmedInput.substring(4).trim() : "";
            return cmd;
        } else if (trimmedInput.startsWith("deadline")) {
            String body = trimmedInput.length() > 8 ? trimmedInput.substring(8).trim() : "";
            int byPos = body.indexOf("/by");
            if (byPos != -1) {
                Command cmd = new Command(CommandType.DEADLINE);
                cmd.description = body.substring(0, byPos).trim();
                cmd.deadline = body.substring(byPos + 3).trim();
                return cmd;
            } else {
                return new Command(CommandType.INVALID);
            }
        } else if (trimmedInput.startsWith("event")) {
            String body = trimmedInput.length() > 5 ? trimmedInput.substring(5).trim() : "";
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
        } else if (trimmedInput.startsWith("find")) {
            Command cmd = new Command(CommandType.FIND);
            cmd.description = trimmedInput.length() > 4 ? trimmedInput.substring(4).trim() : "";
            return cmd;
        } else if (trimmedInput.equals("sort")) {
            return new Command(CommandType.SORT);
        } else {
            return new Command(CommandType.INVALID);
        }
    }
}
