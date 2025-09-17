package evansbot.task;

import java.time.LocalDate;

/**
 * Represents a task that has a description and can be completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * Initially a Task is always not done yet.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description.
     * Set the Task to either be Done or not.
     *
     * @param description Description of the task.
     * @param isDone Tracks whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return String description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task in "X" or " ".
     *
     * @return String status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a boolean.
     *
     * @return boolean status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the status of the task if it on date or not.
     * Default to false since Todo and generic task do not have a date.
     *
     * @return boolean status of the task being on date.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }
    /**
     * Returns the date of the task.
     * Default to null since there is no start date
     *
     * @return LocalDate date of the task if there is a date.
     */
    public LocalDate getStartDate() {
        return null;
    }
    /**
     * Returns the string representation of the task, including its description and status.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
