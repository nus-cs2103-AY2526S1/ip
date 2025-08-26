package rafayel.command;

/**
 * Parser deals with making sense of the user command.
 * Parser parse and understands the user's commands and determines the corresponding command type.
 */
public class Parser {

    /**
     * Enumeration of all possible commands that can be recognised by the application.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

        /**
         * Parses the input string to determine the corresponding command type.
         *
         * @param input input string to be parsed.
         * @return the corresponding Command enum value.
         */
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

    /**
     * Parses the user input and returns the corresponding command type.
     *
     * @param input user input string to be parsed.
     * @return the corresponding Command enum value.
     */
    public static Command parse(String input) {
        return Command.parseCommand(input);
    }
}
