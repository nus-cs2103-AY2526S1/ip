package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abstract base class for all task types.
 */
public class Task {
    private final String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public Task(String taskName, boolean completed) {
        this.taskName = taskName;
        this.isCompleted = completed;
    }

    /**
     * @return "[X]" if done, "[ ]" if not
     */
    public String getStatus() {
        return this.isCompleted ? "X" : " ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        this.isCompleted = false;
    }

    /**
     * @return task description
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @return task type
     */
    public String getTaskType() {
        return "";
    }

    /**
     * @return task deadline
     */
    public LocalDate getDeadLine() {
        return null;
    }

    /**
     * @return task From
     */
    public LocalDateTime getStartDate() {
        return null;
    }

    /**
     * @return task To
     */
    public LocalDateTime getEndDate() {
        return null;
    }

    /**
     * @return true if task is marked done
     */
    public boolean isDone() {
        return isCompleted;
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.taskName;
    }

    /**
     * @return save format of the task
     */
    public String convertor() {
        return "T | " + (this.isCompleted ? 1 : 0) + " | " + this.taskName;
    }

    public String getAddMessage(int count) {
        return "Understood. I have added this task:\n  " + this
                + "\nYou now have " + count + " tasks in your list.";
    }
}
