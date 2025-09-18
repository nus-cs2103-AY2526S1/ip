package jackbot.task;

/**
 * Simple task with only a description and done/undone state.
 * <p>
 * Persistence (line format):
 * <pre>{@code
 * T|0|<description>
 * T|1|<description>
 * }</pre>
 * The second field is {@code 1} if done, {@code 0} otherwise.
 *
 */
public class Todo extends Task {
    /**
     * Creates a new {@code Todo} with the given description.
     *
     * @param description human-readable description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the display form used by the UI, prefixed with {@code [T]}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Serializes this task into {@code T|<done>|<description>} format
     * compatible with {@link #deserialize(String)} in {@link Task}.
     *
     * @return single-line wire format (no newline)
     */
    @Override
    public String serialize() {
        return "T" + "|" + (this.isDone() ? "1" : "0") + "|" + this.getDescription();
    }
}
