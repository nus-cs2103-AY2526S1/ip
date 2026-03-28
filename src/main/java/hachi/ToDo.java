package hachi;

/**
 * Represents a to-do task that the user needs to complete. This is a subclass of {@link Task}.
 * A to-do task has a description and a completion status, and can be marked as completed or incomplete.
 */
public class ToDo extends Task {

    /**
     * Creates a new to-do task with the specified description. The task is initially marked as incomplete.
     *
     * @param description the description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new to-do task with the specified description and completion status.
     *
     * @param description the description of the to-do task
     * @param status      the completion status of the task (true for completed, false for incomplete)
     */
    public ToDo(String description, boolean status) {
        super(description, status);
    }

    /**
     * Returns a string representation of this to-do task in the format "[T][status] description".
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
