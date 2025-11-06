package yappy.task;

import java.time.LocalDateTime;

import yappy.exception.YappyException;


/**
 * Represents a task.
 * A <code>Task</code> object has a <code>isCompleted</code> field to
 * represent whether a task is completed and
 * a description field for the description of the task.
 *
 */
public class Task {
    private boolean isCompleted;
    private final String description;

    /**
     * Constructs a new <code>Task</code> task with the given description.
     * @param description Textual description of the task.
     * @param isCompleted <code>true</code> if the task has been completed,
     *                    <code>false</code>> otherwise.
     */
    public Task(String description, Boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Set the isCompleted field to true.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Set the isCompleted field to false.
     */
    public void setUncompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns string to be written to disk.
     *
     * @return An empty string
     */
    public String toFileString() {
        return "";
    }

    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a boolean for whether the task is completed or not.
     *
     * @return isCompleted field.
     */
    public Boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * This method is to be overridden by the <code>Event</code> class to
     * reschedule the event's start and end date and time.
     * @param newFrom New date an event starts from.
     * @param newTo New end date an events lasts to.
     * @return A message that states whether the task has been successfully rescheduled.
     * @throws YappyException If the task cannot be rescheduled.
     */
    public String reschedule(LocalDateTime newFrom, LocalDateTime newTo) throws YappyException {
        throw new YappyException("This task cannot be rescheduled!");
    }

    /**
     * This method is to be overridden by the <code>Deadline</code> class
     * to postpone the <code>Deadline</code> task's deadline.
     * @param newDeadline New deadline for a Deadline task.
     * @return A message that states whether the task has been successfully postponed.
     * @throws YappyException If the task cannot be postponed.
     */
    public String postpone(LocalDateTime newDeadline) throws YappyException {
        throw new YappyException("This task cannot be postponed");
    }
}
