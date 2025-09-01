package aurora.task;

import java.time.temporal.Temporal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aurora.util.DateUtil;


/**
 * Utility class for reading user input into {@link Task} objects.
 * Creates {@link Todo}, {@link Deadline}, and {@link Event} tasks
 * based on input format.
 */
public class TaskReader {
    private static final Pattern TODO = Pattern.compile(
            "^todo\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE = Pattern.compile(
            "^deadline\\s+(.+)\\s+/by:\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT = Pattern.compile(
            "^event\\s+(.+)\\s+/from:\\s+(.+)\\s+/to:\\s+(.+)$", Pattern.CASE_INSENSITIVE);

    /**
     * Reads user input and returns a {@link Task} object.
     *
     * @param input input string from the user
     * @return the corresponding {@link Task} task
     * @throws InvalidTaskException if input does not match any known task format
     */
    public static Task read(String input) {
        Matcher m;

        if (input.toLowerCase().startsWith("todo")) {
            m = TODO.matcher(input);
            if (m.matches()) {
                return new Todo(m.group(1).trim(), false);
            } else {
                throw new InvalidTaskException("Invalid todo format.\n"
                        + "Please enter \"todo <description>\"");
            }
        }

        if (input.toLowerCase().startsWith("deadline")) {
            m = DEADLINE.matcher(input);
            if (m.matches()) {
                return new Deadline(m.group(1).trim(), false, toDate(m.group(2).trim()));
            } else {
                throw new InvalidTaskException("Invalid deadline format.\n"
                        + "Please enter \"deadline <description> /by: <deadline>\"");
            }
        }

        if (input.toLowerCase().startsWith("event")) {
            m = EVENT.matcher(input);
            if (m.matches()) {
                return new Event(m.group(1).trim(), false,
                        toDate(m.group(2).trim()), toDate(m.group(3).trim()));
            } else {
                throw new InvalidTaskException("Invalid event format.\n"
                        + "Please enter \"event <description> /from: <start> /to: <end>\"");
            }
        }

        throw new InvalidTaskException("Invalid task input. enter \"help\" to see command format.");
    }

    public static Task fromText(String text) {
        String[] values = text.split("\\|", -1);
        if (values.length < 3) {
            throw new InvalidTaskException("Not enough fields in text line: " + text);
        }
        boolean isDone = values[1].equals("true");
        Task result = switch (values[0]) {
        case "T" -> new Todo(values[2], isDone);
        case "D" -> new Deadline(values[2], isDone, toDate(values[3]));
        case "E" -> new Event(values[2], isDone, toDate(values[3]), toDate(values[4]));
        default -> null;
        };

        if (result == null) {
            throw new InvalidTaskException("Invalid task text format.");
        }

        return result;
    }

    private static Temporal toDate(String dateString) {
        try {
            return DateUtil.readDate(dateString);
        } catch (IllegalArgumentException e) {
            throw new InvalidTaskException("Invalid date format. Please try yyyy-MM-dd.");
        }
    }
}
