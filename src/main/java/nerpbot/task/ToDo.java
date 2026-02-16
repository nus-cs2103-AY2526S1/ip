package nerpbot.task;

/**
 * Represents a todo task without any date.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the todo task to a string format suitable for saving to a file.
     *
     * @return The string representation of the todo task for saving.
     */
    @Override
    public String saveFormat() {
        return "T | " + super.saveFormat();
    }

    /**
     * Returns the string representation of the todo task for display.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
