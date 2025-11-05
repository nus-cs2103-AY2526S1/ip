package sunoo.task;

/**
 * Represents a todo task.
 * A todo task does not have a specific deadline or time.
 */
public class ToDo extends Task {

    /**
     * Creates a new todo task.
     *
     * @param isDone Whether the task is completed.
     * @param description Description of the task.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Compares this ToDo with another object.
     *
     * @param o Object to compare with this ToDo.
     * @return true If both ToDo's have the same completion state and description, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ToDo other) {
            return (isDone == other.isDone && description.equals(other.description));
        }
        return false;
    }

    /**
     * Returns a string representation of the todo task.
     * Overrides the parent method to prepend the "[T]" icon.
     *
     * @return "[T]" followed by the task's status icon and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for saving to a text file.
     * Overrides the parent method to prepend "T".
     *
     * @return "T" followed by the base task text representation.
     */
    @Override
    public String getTxtRepresentation() {
        return "T" + super.getTxtRepresentation();
    }
}
