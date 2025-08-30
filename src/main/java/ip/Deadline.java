package ip;

/**
 * Represents a task with a deadline.
 * A Deadline has a description and a due date or time.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description the description of the task
     * @param by the due date or time for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date or time of this Deadline.
     *
     * @return the due date or time as a String
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the type icon of this task.
     *
     * @return "D" for Deadline
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns a string representation of the Deadline task, including
     * its type, status, description, and due date.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
}
