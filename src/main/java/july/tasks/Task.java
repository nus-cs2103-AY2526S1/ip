package july.tasks;

import july.datetime.StringTime;

/**
 * Abstract base class representing a generic task in the July task management system.
 * This class provides common functionality for all task types including description,
 * completion status, and basic display formatting.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially set as not done.
     *
     * @param description the description text for this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing the completion state of this task.
     *
     * @return "X" if the task is done, " " (space) if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of this task.
     * Shows the completion status icon in brackets followed by the task description.
     *
     * @return a formatted string displaying the task status and description
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Sets the completion status of this task.
     *
     * @param done true to mark the task as done, false to mark as not done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the save format representation of this task.
     * This base implementation returns an empty string and should be
     * overridden by subclasses to provide appropriate save formats.
     *
     * @return an empty string (to be overridden by subclasses)
     */
    public String toSave() {
        return "";
    }

    /**
     * Checks if this task is relevant for the specified date.
     * This base implementation returns false and should be overridden
     * by subclasses to provide appropriate date-checking logic.
     *
     * @param o the StringTime object representing the date to check against
     * @return false (to be overridden by subclasses with specific logic)
     */
    public boolean check(StringTime o) {
        return false;
    }

    public boolean checkDescription(String s) {
        return description.contains(s);
    }
}
