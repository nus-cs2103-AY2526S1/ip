package command;

import java.util.regex.*;
import task.Task;
import task.TaskReader;

public class CommandReader {

    private static final Pattern MARK = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);

    public static Command read(String input) {
        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        Matcher matcher = MARK.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            return new MarkCommand(index);
        }

        Task task = TaskReader.read(input);
        if (task != null) {
            return new AddCommand(task);
        }
        return new InvalidCommand();
    }
}
