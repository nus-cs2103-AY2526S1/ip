package jone;

/**
 * Represents a simple todo task.
 * A {@code Todo} has only a description and a completion status.
 */
public class Todo extends Task {
    private static final String TYPE = "T";

    /**
     * Constructs a new {@code Todo} with the given description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} with description and completion status.
     *
     * @param description The description of the todo.
     * @param done Whether the todo has been completed.
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Returns the string representation of this todo.
     *
     * @return A formatted string showing task type, status, and description.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", TYPE, status(), getDescription());
    }

    /**
     * Converts this todo into a string suitable for saving to file.
     *
     * @return Save format string for this todo.
     */
    @Override
    public String toSaveFormat() {
        return TYPE + " | " + (isDone() ? 1 : 0) + " | " + getDescription();
    }
}
