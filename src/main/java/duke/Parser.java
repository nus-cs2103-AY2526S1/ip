/**
 * Deals with making sense of the user command.
 */
package duke;
public class Parser {

    /**
     * Represents the different types of commands.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, INVALID
    }

    /**
     * Represents a parsed command with its type and relevant data.
     */
    public static class Command {
        public CommandType type;
        public String taskNumber;
        public String description;
        public String deadline;
        public String from;
        public String to;

        public Command(CommandType type) {
            this.type = type;
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
        } else {
            return new Command(CommandType.INVALID);
        }
    }
}
