package phuc.model;


import java.time.LocalDateTime;

/**
 * Abstract base class representing a task in the task management system.
 * Provides common functionality for all task types.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not completed.
     *
     * @param description the task description
     */
    public Task(String description) {
        assert description != null;
        assert !description.trim().isEmpty();
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with specified description and completion status.
     *
     * @param description the task description
     * @param isDone whether the task is completed
     */
    public Task(String description, boolean isDone) {
        assert description != null;
        assert !description.trim().isEmpty();
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon representing completion state.
     *
     * @return "X" if completed, " " if not completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "   ");
    }

    /**
     * Converts the task to a string format suitable for file storage.
     * Subclasses should override this method.
     *
     * @return a string representation for file storage
     */
    public String writeToFile() {
        return description;
    }

    /**
     * Gets the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the date-time for sorting purposes.
     * For Todo tasks, returns a very distant future date.
     * For Deadline tasks, returns the deadline.
     * For Event tasks, returns the start date.
     *
     * @return the date-time used for sorting
     */
    public abstract LocalDateTime getSortDateTime();

    /**
     * Compares tasks based on their date-time for sorting.
     * Tasks without dates (Todo) are placed after dated tasks.
     *
     * @param other the other task to compare to
     * @return negative if this task comes before, positive if after, 0 if equal
     */
    @Override
    public int compareTo(Task other) {
        return this.getSortDateTime().compareTo(other.getSortDateTime());
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return a formatted string showing task status and description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
