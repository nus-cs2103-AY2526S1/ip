package finixx.task;

/**
 * Represents a to-do task with a description, completion status, and optional notes.
 * Extends the abstract Task class by providing specific implementations for to-do tasks.
 * Stores the description, completion status, and notes of the to-do task.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask with the specified description, completion status, and notes.
     *
     * @param description Description of the task
     * @param isDone Completion status of the task
     * @param note Note associated with the task
     */
    public TodoTask(String description, boolean isDone, String note) {
        super(description, isDone, note);
    }

    /**
     * Returns a string representation of this TodoTask for display.
     *
     * @return String representation of the TodoTask
     */
    @Override
    public String toString() {
        return "[T]" + super.toString()
                + (getNote().isEmpty() ? "" : " <" + getNote() + ">");
    }

    /**
     * Converts this TodoTask into a string suitable for saving to a file.
     *
     * @return String representing the TodoTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "T | " + (isDone() ? "1" : "0") + " | "
                + getDescription() + " | "
                + getNote();
    }
}
