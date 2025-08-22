package command;

import java.util.regex.*;

import task.*;

/**
 * Utility class for reading user input into {@link Command} objects.
 * Creates {@link AddCommand}, {@link HelpCommand}, {@link InvalidCommand},
 * {@link ListCommand} and {@link MarkCommand} commands based on input.
 */
public class CommandReader {

    private static final Pattern MARK = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);

    /**
     * Reads user input and returns a {@link Command} object.
     *
     * @param input input string from the user
     * @return the corresponding {@link Command} command, or {@link InvalidCommand}
     *         if the input does not match any known command format
     */
    public static Command read(String input) {
        if (input.toLowerCase().startsWith("list")) {
            if (input.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else {
                return new InvalidCommand("Invalid list command.\n" +
                        "enter \"list\" to see your added tasks.");
            }
        }

        if (input.toLowerCase().startsWith("help")) {
            if (input.equalsIgnoreCase("help")) {
                return new HelpCommand();
            } else {
                return new InvalidCommand("Invalid help command.\n" +
                        "enter \"help\" to see all existing commands.");
            }
        }

        if (input.toLowerCase().startsWith("mark")) {
            Matcher matcher = MARK.matcher(input);
            if (matcher.matches()) {
                int index = Integer.parseInt(matcher.group(1));
                return new MarkCommand(index);
            } else {
                return new InvalidCommand("Invalid mark command.\n" +
                        "enter \"mark <task number>\" to mark tasks as complete.");
            }
        }

        try {
            Task task = TaskReader.read(input);
            return new AddCommand(task);
        } catch (InvalidTaskException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
