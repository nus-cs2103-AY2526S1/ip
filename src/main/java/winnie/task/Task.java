package winnie.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public abstract class Task {

    private String description;
    private boolean isDone;
    private TaskEnum taskType;
    private LocalDateTime snoozeUntil;

    /**
     * Creates a task.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task.
     */
    public Task(String description, TaskEnum taskType) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.snoozeUntil = null;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets the type icon of the task.
     *
     * @return The type icon of the task.
     */
    public String getTypeIcon() {
        return taskType.getIcon();
    }

    /**
     * Gets the task type of the task.
     *
     * @return The task type of the task.
     */
    public TaskEnum getTaskType() {
        return taskType;
    }

    /**
     * Snoozes the task until the specified date and time.
     *
     * @param snoozeUntil The date and time until when to snooze the task.
     */
    public void snooze(LocalDateTime snoozeUntil) {
        assert snoozeUntil != null : "Snooze time cannot be null";
        this.snoozeUntil = snoozeUntil;
    }

    /**
     * Removes the snooze from the task.
     */
    public void unsnooze() {
        this.snoozeUntil = null;
    }

    /**
     * Checks if the task is currently snoozed.
     *
     * @return True if the task is snoozed, false otherwise.
     */
    public boolean isSnoozed() {
        return snoozeUntil != null && LocalDateTime.now().isBefore(snoozeUntil);
    }

    /**
     * Gets the snooze until date and time.
     *
     * @return The snooze until date and time, or null if not snoozed.
     */
    public LocalDateTime getSnoozeUntil() {
        return snoozeUntil;
    }

    @Override
    public String toString() {
        String baseString = getTypeIcon() + getStatusIcon() + " " + getDescription();
        if (isSnoozed()) {
            return baseString + " [SNOOZED]";
        }
        return baseString;
    }
}
