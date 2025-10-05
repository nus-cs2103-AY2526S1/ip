package sage.task;

/**
 * Represents a Todo task.
 * A Todo task has a description and can be marked as done or not done.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including its type icon.
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task suitable for saving to a file.
     * The format is: T | <status> | <description>
     * @return A file-formatted string representing the todo task.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
