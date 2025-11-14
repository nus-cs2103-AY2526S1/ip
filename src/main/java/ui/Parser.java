package ui;

import command.*;
import exception.BaymaxException;

/**
 * The Parser class is responsible for converting user input strings
 * into executable {@link Command} objects for the Baymax application.
 */
public class Parser {

    /**
     * Parses a raw input string into a corresponding {@link Command}.
     *
     * @param input the raw input from the user
     * @return a Command object corresponding to the input
     * @throws BaymaxException if the input is invalid or the command is unknown
     */
    public static Command parse(String input) throws BaymaxException {
        assert input != null : "Input should never be null";

        String command = getCommand(input);
        String args = getArgs(input);

        return switch (command) {
            case "bye" -> new ByeCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(parseIndex(args));
            case "unmark" -> new UnmarkCommand(parseIndex(args));
            case "delete" -> new DeleteCommand(parseIndex(args));
            case "todo" -> new TodoCommand(parseTodo(args));
            case "deadline" -> {
                String[] parts = parseDeadline(args);
                yield new DeadlineCommand(parts[0], parts[1]);
            }
            case "event" -> {
                String[] parts = parseEvent(args);
                yield new EventCommand(parts[0], parts[1], parts[2]);
            }
            case "find" -> new FindCommand(args.trim());
            default -> throw new BaymaxException("I'm confused... what is this command: " + command);
        };
    }

    /**
     * Extracts the command keyword from the raw input
     */
    public static String getCommand(String input) {
        assert input != null : "Input should never be null";
        String[] parts = input.trim().split(" ", 2);
        return parts[0];
    }

    /**
     * Extracts everything after the command as arguments
     */
    public static String getArgs(String input) {
        assert input != null : "Input should never be null";
        String[] parts = input.trim().split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    /**
     * Parses a todo command
     */
    public static String parseTodo(String args) throws BaymaxException {
        assert args != null : "Input should never be null";
        if (args.isEmpty()) {
            throw new BaymaxException("On a scale of 1-10, your task description is currently 0!");
        }
        return args;
    }

    /**
     * Parses a deadline command into description and by date
     */
    public static String[] parseDeadline(String args) throws BaymaxException {
        assert args != null : "Args should not be null";

        int byIndex = args.indexOf("/by");
        if (byIndex == -1) throw new BaymaxException("I'm confused... task.Deadline must have /by!");

        String desc = args.substring(0, byIndex).trim();
        String by = args.substring(byIndex + 3).trim();

        if (desc.isEmpty() || by.isEmpty())
            throw new BaymaxException("I'm confused... the description of the deadline is invalid!");

        return new String[]{desc, by};
    }

    /**
     * Parses an event command into description, from, and to
     */
    public static String[] parseEvent(String args) throws BaymaxException {
        assert args != null : "Args should not be null";

        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex)
            throw new BaymaxException("I'm confused... the description of the event is invalid!");

        String desc = args.substring(0, fromIndex).trim();
        String from = args.substring(fromIndex + 5, toIndex).trim();
        String to = args.substring(toIndex + 3).trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty())
            throw new BaymaxException("I'm confused... the description of the event is invalid!");

        return new String[]{desc, from, to};
    }

    /**
     * Parses index from mark/unmark/delete commands
     */
    public static int parseIndex(String arg) throws BaymaxException {
        assert arg != null : "Arg should not be null";

        try {
            return Integer.parseInt(arg.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BaymaxException("I'm confused... please specify a task number!");
        }
    }
}
