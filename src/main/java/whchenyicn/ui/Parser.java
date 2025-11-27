package whchenyicn.ui;

import whchenyicn.command.*;
import whchenyicn.exceptions.WhchenyicnException;

/**
 * Class to parse user input
 */
public class Parser {
    public static boolean isExit(String input) {
        return input.trim().equalsIgnoreCase("bye");
    }

    /**
     * Parses the user input into command
     *
     * @param input User input.
     * @return Returns the String of the Command.
     */
    public static String getCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        return input.trim().split("\\s+", 2)[0];
    }

    /**
     * Parses the user input into details.
     *
     * @param input User input.
     * @return Returns the Details.
     */
    public static String getArg(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

    /**
     * Parses the user input into specific commands
     *
     * @param input User input.
     * @return Returns the Command.
     */
    public static Command parse(String input) throws WhchenyicnException {
        String command = getCommand(input);
        String arg = getArg(input);

        assert command != null : "Command string should never be null";
        assert arg != null : "Arg string should never be null";

        switch (command.toLowerCase()) {

        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(arg);
        case "unmark":
            return new UnmarkCommand(arg);
        case "delete":
            return new DeleteCommand(arg);
        case "todo":
            return new AddToDoCommand(arg);
        case "deadline":
            return new AddDeadlineCommand(arg);
        case "event":
            return new AddEventCommand(arg);
        case "find":
            return new FindCommand(arg);
        case "reminder":
            return new ReminderCommand(arg);
        default:
            throw new WhchenyicnException("Invalid whchenyicn.command: " + command);
        }
    }
}
