package timmy;

/**
 * Represents a simple task with no auxiliary details.
 */
public class ToDo extends Task {

    /**
     * Creates a simple 'To-Do' task.
     *
     * @param desc description of the task.
     */
    ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns the task as a formatted string for Timmy to output.
     *
     * @return task as a formatted string to output on UI.
     */
    public String toCompleteString() {
        return "[T]" + super.toStringWithStatusIcon();
    }

    /**
     * Returns the task as a formatted string for Timmy to save.
     *
     * @return task as a formatted string to save in storage.
     */
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + super.toString();
    }
}
