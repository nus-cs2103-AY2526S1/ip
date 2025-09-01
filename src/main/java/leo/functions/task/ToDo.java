package leo.functions.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Instantiates a ToDo task.
     *
     * @param description The description of a ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the formatted string of the task to be written to a file.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String appendToFile() {
        return String.format(
                "ToDo | "
                + this.getStatusIcon()
                + " | " + this.description
        );
    }

    /**
     * Returns a formatted string of the task to be written to the UI.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String toString() {
        return String.format(
                "[T]["
                + this.getStatusIcon()
                + "] " + this.description
        );
    }
}
