package waddles.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Base Task class for all types of tasks.
 */
public abstract class Task {
    public static final String INPUT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(INPUT_DATETIME_FORMAT);
    public static final String OUTPUT_DATETIME_FORMAT = "MMM dd yyyy HH:mm";
    public static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern(OUTPUT_DATETIME_FORMAT);

    private final String description;
    private final Tags tags;
    private boolean isDone;

    /**
     * Constructs a new Task object.
     */
    public Task(String description, boolean isDone, Tags tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Splits the given serialization of some Task into its fields.
     * E.g. <code>"isDone | description"</code> becomes <code>[isDone, description]</code>.
     */
    public static String[] splitSerialized(String serialized) {
        return Arrays.stream(serialized.split("\\|")).map(String::strip).toArray(String[]::new);
    }

    public String getDescription() {
        return description;
    }

    public Tags getTags() {
        return this.tags;
    }

    /**
     * Marks this task as done / undone.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a serialization of this task.
     * Serialization format: <code>isDone | description | tags</code>.
     */
    public String toSerializedString() {
        return String.format("%d | %s | %s", isDone ? 1 : 0, description, tags.toSerializedString());
    }

    @Override
    public String toString() {
        char doneMarker = isDone ? 'X' : ' ';
        String tagString = tags.toString();
        return String.format("[%c] %s (%s)", doneMarker, description, tagString);
    }
}
