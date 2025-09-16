package tinkerton.task;

import tinkerton.util.Date;

/**
 * Represents a ToDo task with a name and completion status. A ToDo does not have any associated
 * date.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task.
     *
     * @param name The name of the task.
     * @param isCompleted True if the task is completed, false otherwise.
     */
    public ToDo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    /**
     * Returns the string representation of the ToDo task for display.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the ToDo task for file storage.
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "T | " + completed + " | " + this.name();
    }

    /**
     * Checks if the ToDo task occurs on the given date. Always returns false as ToDo tasks do not
     * have dates.
     *
     * @param date The date to check.
     * @return false
     */
    @Override
    public boolean onDate(Date date) {
        return false;
    }
}
