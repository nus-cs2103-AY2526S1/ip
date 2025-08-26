package rafayel.command;

/**
 * Parser deals with making sense of the user command
 */

public class Parser {
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

        public static Command parseCommand(String input) {
            if (input.equals("bye"))
                return BYE;
            if (input.equals("list"))
                return LIST;
            if (input.startsWith("mark"))
                return MARK;
            if (input.startsWith("unmark"))
                return UNMARK;
            if (input.startsWith("todo"))
                return TODO;
            if (input.startsWith("deadline"))
                return DEADLINE;
            if (input.startsWith("event"))
                return EVENT;
            if (input.startsWith("delete"))
                return DELETE;
            return UNKNOWN;
        }
    }

    public static Command parse(String input) {
        return Command.parseCommand(input);
    }
}
