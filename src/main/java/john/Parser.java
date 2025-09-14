package john;

/**
 * A class to parse user inputs.
 */
public class Parser {
    /**
     * Parses a user input and returns the correct command.
     */
    public static Command parseCommand(String prompt) {
        String lower = prompt.toLowerCase();
        if (lower.matches("^mark\\s\\d+$")) {
            return Command.MARK;
        }
        if (lower.matches("^unmark\\s\\d+$")) {
            return Command.UNMARK;
        }
        if (lower.equals("list")) {
            return Command.LIST;
        }
        if (lower.matches("^todo( |$).*")) {
            return Command.TODO;
        }
        if (lower.matches("^deadline( |$).*")) {
            return Command.DEADLINE;
        }
        if (lower.matches("^event( |$).*")) {
            return Command.EVENT;
        }
        if (lower.matches("^delete\\s\\d+$")) {
            return Command.DELETE;
        }
        if (lower.matches("^find( |$).*")) {
            return Command.FIND;
        }
        if (lower.equals("bye")) {
            return Command.BYE;
        }
        if (lower.equals("undo")) {
            return Command.UNDO;
        }
        return Command.WRONG;
    }
}
