package john.model;

/**
 * Task representing a simple to-do without any date-time fields.
 * <p>
 * Format responsibilities:
 * - toString(): renders as "[T]{base}", for example "[T][ ] read book".
 * - serialise(): emits a pipe-delimited record via Task.baseSerialize:
 * T | done(0|1) | title
 * <p>
 * Notes:
 * - Completion state is handled by the Task base class.
 */
public class Todo extends Task {
    /**
     * Creates a to-do task with the given title.
     *
     * @param title task description; must not be null
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Returns a human-readable representation of the to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Serialises this task to a pipe-delimited record suitable for FileStorage.
     * Format: T | done(0|1) | title
     *
     * @return the serialised line
     */
    @Override
    public String serialise() {
        // If you prefer no trailing field, you can use baseSerialize("T") instead.
        return baseSerialize("T", "");
    }
}
