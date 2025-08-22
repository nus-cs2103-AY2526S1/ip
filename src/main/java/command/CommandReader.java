package command;

import java.util.regex.*;
import task.InvalidTaskException;
import task.Task;
import task.TaskReader;

public class CommandReader {

    private static final Pattern MARK = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);

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
