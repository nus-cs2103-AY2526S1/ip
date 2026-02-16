package peppa.task;

import java.time.LocalDateTime;

public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isMatch(String toFind) {
        return this.description.contains(toFind);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public abstract String toSaveFileFormat();

    /**
     * Gets the date/time associated with this task for sorting purposes.
     * Returns null for tasks without specific dates (like ToDo).
     * 
     * @return the primary date/time for this task, or null if none exists
     */
    public abstract LocalDateTime getDateTime();

    /**
     * Compares this task with another task for ordering.
     * Each subclass should implement its own comparison logic.
     * 
     * @param other the task to be compared
     * @return a negative integer, zero, or a positive integer as this task
     *         is less than, equal to, or greater than the specified task
     */
    public abstract int compareTo(Task other);

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
