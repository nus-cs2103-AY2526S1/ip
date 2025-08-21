package task;
import java.util.regex.*;

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
     * @return the corresponding {@link Task} task, or null if the input
     *         does not match any known task format
     */
    public static Task read(String input) {
        Matcher m;

        m = TODO.matcher(input);
        if (m.matches()) {
            return new Todo(m.group(1).trim());
        }

        m = DEADLINE.matcher(input);
        if (m.matches()) {
            return new Deadline(m.group(1).trim(), m.group(2).trim());
        }

        m = EVENT.matcher(input);
        if (m.matches()) {
            return new Event(m.group(1).trim(), m.group(2).trim(), m.group(3).trim());
        }

        return null;
    }
}
