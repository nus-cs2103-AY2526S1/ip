package geni.task;

/**
 * Represents a simple Todo task without any date or time.
 * Inherits from Task and only stores a description and completion status.
 */

public class Todo extends Task {
    /**
     * Creates a Todo task with the given description.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns a string representation of the Todo task for display.
     *
     * @return formatted Todo string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation suitable for saving to a file.
     *
     * @return formatted save string
     */

    @Override
    public String toSaveFormat() {
        return "T | " + (super.getStatusIcon().equals("X") ? "1" : "0") + " | " + super.getDescription();
    }

}
