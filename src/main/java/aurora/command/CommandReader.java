package aurora.command;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aurora.task.InvalidTaskException;
import aurora.task.Task;
import aurora.task.TaskReader;

/**
 * Utility class for reading user input into {@link Command} objects.
 * Creates {@link AddCommand}, {@link HelpCommand}, {@link InvalidCommand},
 * {@link ListCommand} and {@link MarkCommand} commands based on input.
 */
public class CommandReader {

    // Regex for mark, delete, and find commands to extract the integer/string after the command
    private static final Pattern MARK = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE = Pattern.compile("^delete\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern FIND = Pattern.compile("^find\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TAG = Pattern.compile("^tag\\s+(\\d+)\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern UNTAG = Pattern.compile("^untag\\s+(\\d+)\\s+(.+)$", Pattern.CASE_INSENSITIVE);

    /**
     * Reads user input and returns a {@link Command} object.
     *
     * @param input input string from the user
     * @return the corresponding {@link Command} command, or {@link InvalidCommand}
     *         if the input does not match any known command format
     */
    public static Command read(String input) {
        // Refactored command parsing logic to reduce duplication and simplify the read() method.
        // Original logic was rewritten with helper methods for maintainability and cleaner code.
        // AI assistance used for code structure and helper function design.
        String lower = input.toLowerCase();

        if (lower.startsWith("list")) {
            return parseSimpleCommand(input, "list", new ListCommand());
        }

        if (lower.startsWith("help")) {
            return parseSimpleCommand(input, "help", new HelpCommand());
        }

        if (lower.startsWith("mark")) {
            return parseIndexedCommand(input, MARK, "mark <task number>", MarkCommand::new);
        }

        if (lower.startsWith("delete")) {
            return parseIndexedCommand(input, DELETE, "delete <task number>", DeleteCommand::new);
        }

        if (lower.startsWith("find")) {
            Matcher matcher = FIND.matcher(input);
            return matcher.matches()
                    ? new FindCommand(matcher.group(1))
                    : new InvalidCommand("Invalid find command.\nenter \"find <content>\" for correct usage.");
        }

        if (lower.startsWith("tag")) {
            return parseTaggedCommand(input, TAG, "tag <task number> <tag>", TagCommand::new);
        }

        if (lower.startsWith("untag")) {
            return parseTaggedCommand(input, UNTAG, "untag <task number> <tag>", UntagCommand::new);
        }

        try {
            return new AddCommand(TaskReader.read(input));
        } catch (InvalidTaskException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private static Command parseSimpleCommand(String input, String commandName, Command command) {
        return input.equalsIgnoreCase(commandName)
                ? command
                : new InvalidCommand("Invalid " + commandName + " command.\n"
                + "enter \"" + commandName + "\" for correct usage.");
    }

    private static Command parseIndexedCommand(String input, Pattern pattern, String usage, Function<Integer, Command> creator) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            return creator.apply(index);
        }
        return new InvalidCommand("Invalid command.\nenter \"" + usage + "\" for correct usage.");
    }

    private static Command parseTaggedCommand(String input, Pattern pattern, String usage, BiFunction<Integer, String, Command> creator) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            String tag = matcher.group(2);
            return creator.apply(index, tag);
        }
        return new InvalidCommand("Invalid command.\nenter \"" + usage + "\" for correct usage.");
    }
}
