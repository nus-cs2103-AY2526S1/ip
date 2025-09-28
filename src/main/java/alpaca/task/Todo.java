package alpaca.task;

/**
 * Represents a Todo task, which has only a description and no specific date/time.
 * Extends {@link Task}.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the task; must not be null or empty.
     * @throws IllegalArgumentException if description is null or blank.
     */
    public Todo(String description) {
        super(description);
        // No extra assertions needed; Task constructor handles null/blank check
    }

    /**
     * Returns the string representation for saving the Todo to a file.
     *
     * @return The save format string.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a user-friendly string representation of the Todo task.
     *
     * @return The formatted string for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
