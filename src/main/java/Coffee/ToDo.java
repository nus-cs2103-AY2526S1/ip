package Coffee;

/**
 * Represents a to-do task without any time component.
 * A {@code ToDo} carries a textual description and a completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} with the given description.
     *
     * @param description description of the to-do task.
     * @throws IllegalArgumentException if {@code description} is blank.
     */
    public ToDo(String description) throws IllegalArgumentException {
        super(description);
        if (description.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructs a {@code ToDo} with the given description and initial done state.
     *
     * @param description description of the to-do task.
     * @param isDone      initial completion status; if {@code true}, the task is marked done.
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns the user-facing string representation of this to-do, prefixed with {@code [T]} and
     * including the inherited status icon and description.
     *
     * @return string representation suitable for display in listings.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the canonical file serialization of this to-do in the form
     * {@code T | <status> | <description>}, where {@code <status>} is a single
     * space for not done or {@code X} for done.
     *
     * @return pipe-delimited serialization used by storage.
     */
    public String toFileString() {
        return "T | " + super.getStatusIcon() + " | " + description;
    }
}
