package bro.tasks;

/**
 * Represents a todo task that has a description and a status.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo task with the given description and status.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the Todo task to a data string for storage.
     *
     * @return A string representation of the Todo task for storage.
     */
    public String toDataString() {
        return String.format("T|%d|%s", (isDone ? 1 : 0),
                description);
    }

    @Override
    public String toString() {
        return String.format("[T] [%s] %s",
                getStatusIcon(),
                description);
    }
}
