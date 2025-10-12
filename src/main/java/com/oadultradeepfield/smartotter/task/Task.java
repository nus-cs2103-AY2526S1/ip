package com.oadultradeepfield.smartotter.task;

/**
 * Represents a task with a name and completion status.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Creates a new task with the given name, initially not done.
     *
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone {@code true} if the task is done, {@code false} otherwise
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a string representation of the task, showing its completion status in the format:
     *
     * <pre>[X] TaskName</pre>
     *
     * <p>or
     *
     * <pre>[ ] TaskName</pre>
     *
     * <p>.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "%s %s".formatted(getIcon(), taskName);
    }

    /**
     * Converts a task to its line representation for saving as text file
     *
     * @return a line representation of a task
     */
    public String convertToLine() {
        return "%s | %s".formatted(isDone ? "1" : "0", taskName);
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if this task is scheduled for today.
     *
     * <p>Normal tasks always return false. Deadline tasks return true if the deadline is today. Event
     * tasks return true if today is between the start and end dates.
     *
     * @return true if the task occurs today, false otherwise
     */
    public boolean isToday() {
        return false;
    }
}
