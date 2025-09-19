package jimmy.task;

/**
 * Represents a ToDo task
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo object
     *
     * @param description The description of the task.
     */
    public ToDo(String description, boolean completed, String tag) {
        super(description, completed, tag);
    }

    /**
     * Returns the formatted string to be used for storing in the hard disk.
     *
     * @return Formatted string.
     */
    public String toStorageString() {
        return String.format("%s|TODO|%s|%s", super.toStorageString(), this.getDescription(), this.getCompleted());
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        String type = "T";
        return String.format("[%s]%s", type, super.toString());
    }
}
