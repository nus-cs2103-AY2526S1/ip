package paul.task;

/**
 * A simple To Do task for Paul.
 */
public class ToDo extends Task {

    /**
     * Creates a To Do task from the given description.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a To Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of a To Do for saving into a file.
     *
     * @return The To Do task string for saving.
     */
    @Override
    public String toSaveString() {
        return "T" + super.toSaveString();
    }

    /**
     * Checks if the given object is equal to a To Do.
     * They are equal if they have the same description.
     *
     * @param obj the reference object with which to compare.
     * @return true if this is equal to the To Do.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof ToDo)) {
            return false;
        }
        ToDo other = (ToDo) obj;
        return this.description.equalsIgnoreCase(other.description);
    }
}
