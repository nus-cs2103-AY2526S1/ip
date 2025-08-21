package task;
import java.util.regex.*;

public class TaskReader {
    private static final Pattern TODO = Pattern.compile(
            "^todo\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE = Pattern.compile(
            "^deadline\\s+(.+)\\s+/by:\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT = Pattern.compile(
            "^event\\s+(.+)\\s+/from:\\s+(.+)\\s+/to:\\s+(.+)$", Pattern.CASE_INSENSITIVE);

    public static Task read(String input) {
        Matcher m;

        m = TODO.matcher(input);
        if (m.matches()) {
            return new Todo(m.group(1));
        }

        m = DEADLINE.matcher(input);
        if (m.matches()) {
            return new Deadline(m.group(1), m.group(2));
        }

        m = EVENT.matcher(input);
        if (m.matches()) {
            return new Event(m.group(1), m.group(2), m.group(3));
        }

        return null;
    }
}
