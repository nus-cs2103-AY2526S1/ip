package chatbot.task;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
/**
 * Represents an abstract task in the Harry chatbot application.
 * A {@code Task} has a description and a completion status, and
 * may include additional attributes depending on the specific subclass
 * (e.g., {@link ToDo}, {@link Deadline}, {@link Event}).
 * <p>
 * The {@code Task} class also provides factory logic via
 * {@link #fromFile(String)} to reconstruct tasks from their
 * serialized representation.
 */
public abstract class Task {
    private static final Map<String, Function<String[], Task>> registeredTasks = new HashMap<>() {
        {
            put("chatbot.task.ToDo", parsed -> new ToDo(parsed[1].equals("X"), parsed[2]));
            put("chatbot.task.Deadline", parsed -> new Deadline(parsed[1].equals("X"), parsed[2], parsed[3]));
            put("chatbot.task.Event", parsed -> new Event(parsed[1].equals("X"), parsed[2], parsed[3], parsed[4]));
        }
    };
    protected String description;
    protected boolean isDone;

    public void complete() {
        isDone = true;
    }

    public void uncomplete() {
        isDone = false;
    }

    public void snooze() {
    }

    public void change_completion() {
        isDone = !isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }
    /**
     * Reconstructs a {@code Task} from its serialized file representation.
     * <p>
     * The serialized format is expected to be a string with fields
     * separated by {@code "||"}, where the first field identifies the
     * task type by its fully qualified class name.
     *
     * @param line The serialized representation of the task
     * @return The reconstructed {@code Task} object
     */
    public static Task fromFile(String line) {
        String[] parsed = line.split("\\|\\|");
        return registeredTasks.get(parsed[0]).apply(parsed);
    }
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    public abstract String toFile();
}
