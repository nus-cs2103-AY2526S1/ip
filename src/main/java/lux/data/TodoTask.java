package lux.data;

/**
 * Simple task representing a todo (no date/time information).
 */
public class TodoTask extends Task {
    /**
     * Create a new todo task with the given description.
     *
     * @param description text describing the todo
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
