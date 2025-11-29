package cate.task;

/**
 * Represents a simple to-do task with only a description and completion status.
 * A {@code Todo} does not have additional date or time attributes.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts this {@code Todo} task into a machine-readable string
     * for saving to a file. The format is:
     * <pre>
     * T,{doneFlag},{description}
     * </pre>
     * where {@code doneFlag} is 1 if completed, 0 if not.
     *
     * @return The string representation of this task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("T,%d,%s", isDone ? 1 : 0, description);
    }

    /**
     * Returns a human-readable string representation of this {@code Todo}.
     * The format is:
     * <pre>
     * [T] {taskString}
     * </pre>
     * where {@code taskString} comes from {@link Task#toString()}.
     *
     * @return A string representation of this task suitable for display.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
