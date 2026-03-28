package bugsbunny.parsers;

import bugsbunny.commands.AddDeadlineCommand;
import bugsbunny.commands.AddEventCommand;
import bugsbunny.commands.AddToDoCommand;
import bugsbunny.commands.Command;
import bugsbunny.commands.DeleteCommand;
import bugsbunny.commands.DueCommand;
import bugsbunny.commands.ExitCommand;
import bugsbunny.commands.FindCommand;
import bugsbunny.commands.HelpCommand;
import bugsbunny.commands.ListCommand;
import bugsbunny.commands.MarkCommand;
import bugsbunny.commands.UnmarkCommand;
import bugsbunny.exception.BugsBunnyException;


/**
 * Parses raw user input into typed {@link bugsbunny.commands.Command} objects.
 */
public class Parser {
    /**
     * Parses the given input string into a concrete command.
     *
     * @param input Raw user input, e.g., {@code "deadline submit /by 2025-08-30 1300"}.
     * @return A command instance ready to execute.
     * @throws BugsBunnyException If the input is malformed or uses unknown commands.
     */
    public static Command parse(String input) throws BugsBunnyException {
        assert input != null : "input should not be null";

        String[] parts = splitInput(input);
        String commandType = parts[0].trim().toLowerCase();
        String args = parts[1].trim();

        switch (commandType) {
        case "bye":
            return new ExitCommand(args);
        case "help":
            return new HelpCommand(args);
        case "list":
            return new ListCommand(args);
        case "find":
            return new FindCommand(args);
        case "due":
            return new DueCommand(args);
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "todo":
            return new AddToDoCommand(args);
        case "deadline":
            return new AddDeadlineCommand(args);
        case "event":
            return new AddEventCommand(args);
        default: throw new BugsBunnyException(String.format("I don't understand this command: %s\n"
                + "You can type 'help' for the list of available commands", input));
        }
    }

    /**
     * Attempts to parse a string to an integer.
     * @param s raw string (may contain spaces)
     * @return the parsed integer
     * @throws BugsBunnyException if the input cannot be converted to an integer
     */
    public static int parseInteger(String s) throws BugsBunnyException {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            throw new BugsBunnyException("'" + s + "' is not a valid task number. Please try again.");
        }
    }


    private static String[] splitInput(String input) throws BugsBunnyException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new BugsBunnyException("Your input cannot be empty");
        }
        String[] parts = input.split("\\s+", 2);
        if (parts.length == 1) {
            return new String[] { parts[0], "" };
        }
        return parts;
    }
}
