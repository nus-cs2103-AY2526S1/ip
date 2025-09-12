package aurora.command;

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
        if (input.toLowerCase().startsWith("list")) {
            if (input.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else {
                return new InvalidCommand("Invalid list command.\n"
                        + "enter \"list\" to see your added tasks.");
            }
        }

        if (input.toLowerCase().startsWith("help")) {
            if (input.equalsIgnoreCase("help")) {
                return new HelpCommand();
            } else {
                return new InvalidCommand("Invalid help command.\n"
                        + "enter \"help\" to see all existing commands.");
            }
        }

        if (input.toLowerCase().startsWith("mark")) {
            Matcher matcher = MARK.matcher(input);
            if (matcher.matches()) {
                int index = Integer.parseInt(matcher.group(1));
                return new MarkCommand(index);
            } else {
                return new InvalidCommand("Invalid mark command.\n"
                        + "enter \"mark <task number>\" to mark tasks as complete.");
            }
        }

        if (input.toLowerCase().startsWith("delete")) {
            Matcher matcher = DELETE.matcher(input);
            if (matcher.matches()) {
                int index = Integer.parseInt(matcher.group(1));
                return new DeleteCommand(index);
            } else {
                return new InvalidCommand("Invalid delete command.\n"
                        + "enter \"delete <task number>\" to remove task from list.");
            }
        }

        if (input.toLowerCase().startsWith("find")) {
            Matcher matcher = FIND.matcher(input);
            if (matcher.matches()) {
                String search = matcher.group(1);
                return new FindCommand(search);
            } else {
                return new InvalidCommand("Invalid find command.\n"
                        + "enter \"find <content>\" to find task from list.");
            }
        }

        if (input.toLowerCase().startsWith("tag")) {
            Matcher matcher = TAG.matcher(input);
            if (matcher.matches()) {
                int index = Integer.parseInt(matcher.group(1));
                String tag = matcher.group(2);
                return new TagCommand(index, tag);
            } else {
                return new InvalidCommand("Invalid tag command.\n"
                        + "enter \"tag <task number> <tag>\" to tag a task in the list.");
            }
        }

        if (input.toLowerCase().startsWith("untag")) {
            Matcher matcher = UNTAG.matcher(input);
            if (matcher.matches()) {
                int index = Integer.parseInt(matcher.group(1));
                String tag = matcher.group(2);
                return new UntagCommand(index, tag);
            } else {
                return new InvalidCommand("Invalid untag command.\n"
                        + "enter \"untag <task number> <tag>\" to untag a task in the list.");
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
