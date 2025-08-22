package command;

import java.util.regex.*;

import task.InvalidTaskException;
import task.Task;
import task.TaskReader;

public class CommandReader {

    private static final Pattern MARK = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);

    public static Command read(String input) {
        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        }

        Matcher matcher = MARK.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            return new MarkCommand(index);
        }

        try {
            Task task = TaskReader.read(input);
            return new AddCommand(task);
        } catch (InvalidTaskException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
