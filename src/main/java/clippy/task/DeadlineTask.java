package clippy.task;

import clippy.ClippyException;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private DateTime by;

    /**
     * Constructs a DeadlineTask with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline time of the task.
     * @throws ClippyException If the description is null or empty, or if by is null or empty.
     */
    public DeadlineTask(String description, String by) throws ClippyException {
        super(validateDescription(description));
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        if (by == null || by.trim().isEmpty()) {
            throw new ClippyException("A deadline must have a set deadline(use '/by <deadline time'>).");
        }
        this.by = new DateTime(by);
        assert this.by != null : "DateTime for deadline should not be null";
    }

    /**
     * Validates the description of the deadline task.
     *
     * @param description The description to validate.
     * @return The validated description.
     * @throws ClippyException If the description is null or empty.
     */
    private static String validateDescription(String description) throws ClippyException {
        if (description == null || description.trim().isEmpty()) {
            throw new ClippyException("A deadline task must have a description.");
        }
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        return description;
    }

    /**
     * Gets the deadline time of the task.
     *
     * @return The deadline time as a DateTime object.
     */
    public DateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
