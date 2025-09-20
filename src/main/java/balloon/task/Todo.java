package balloon.task;

/**
 * Represents a Todo task.
 * <p>
 * A Todo is a type of {@link Task} that has only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the given description.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this task for displaying to the user.
     * <p>
     * Format: "[T][status] description"
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of this task for saving to a file.
     * <p>
     * Format: "TODO | status | description"
     *
     * @return a string representing the task in save-file format
     */
    @Override
    public String toSaveFormat() {
        return "TODO | " + getDoneStatusIndicator() + " | " + description;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Todo) {
            Todo other = (Todo) object;
            return this.description.equals(other.description);
        }
        return false;
    }
}
