package balloon.task;

/**
 * Represents a task that has a description and a deadline.
 * <p>
 * This class extends {@link Task} and adds a {@link StringDateTime} field which
 * represents the deadline by which the task should be completed.
 */
public class Deadline extends Task {

    private StringDateTime by;

    /**
     * Constructs a {@code Deadline} task with the given description and deadline.
     *
     * @param description the task description
     * @param byInput the deadline in string form
     */
    public Deadline(String description, String byInput) {
        super(description);
        by = new StringDateTime(byInput);
    }


    /**
     * Returns a string representation of this task for displaying to the user.
     * <p>
     * Format: "[D][status] description (by: deadline)"
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getOutputString() + ")";
    }

    /**
     * Returns the string representation of this task for saving to a file.
     * <p>
     * Format: "DEADLINE | status | description | deadline"
     *
     * @return a string representing the task in save-file format
     */
    @Override
    public String toSaveFormat() {
        return "DEADLINE | " + getDoneStatusIndicator() + " | " + description
                + " | " + by.getAsRawString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline other = (Deadline) object;
            return this.description.equals(other.description)
                    && this.by.equals(other.by);
        }
        return false;
    }
}
