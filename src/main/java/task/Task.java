package task;
import java.time.LocalDate;

/**
 * @author Anand Bala
 * Outlines the properties of the Task object. Its subclasses have additional fields,
 * depending on the type of task that they are.
 */

public abstract class Task {
    protected final String taskName;
    protected boolean isDone;

    /**
     * Creates a new task with the given name, initially marked as not done.
     *
     * @param taskName human-readable name of the task
     */
    public Task(String taskName) {
        assert taskName != null : "Task name cannot be null";
        assert !taskName.trim().isEmpty() : "Task name cannot be empty";
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Creates a new task with the given name and explicit completion state.
     *
     * @param taskName human-readable name of the task
     * @param isDone   initial completion state ({@code true} if done)
     */
    public Task(String taskName, boolean isDone) {
        assert taskName != null : "Task name cannot be null";
        assert !taskName.trim().isEmpty() : "Task name cannot be empty";
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getName() {
        assert taskName != null : "Task name should never be null";
        return taskName;
    }

    /**
     * Marks the task as done.
     *
     * @return {@code true} if the state changed from not done to done; {@code false} if it was already done
     */
    public boolean markDone() {
        boolean oldState = isDone;
        if (isDone) {
            return false;
        }
        isDone = true;
        assert isDone != oldState : "Task state should have changed";
        return true;
    }

    /**
     * Marks the task as not done.
     *
     * @return {@code true} if the state changed from done to not done; {@code false} if it was already not done
     */
    public boolean markUndone() {
        boolean oldState = isDone;
        if (!isDone) {
            return false;
        }
        isDone = false;
        assert isDone != oldState : "Task state should have changed";
        return true;
    }

    /**
     * Produces a stable, single-line storage encoding for this task that can be parsed later.
     *
     * @return storage representation of this task (e.g., {@code "D | 1 | name | 2025-12-31"})
     */
    public abstract String toStorage();

    public abstract LocalDate getDate();

    /**
     * Returns a concise human-readable representation of this task with a status checkbox.
     * Uses {@code "[X] "} when completed and {@code "[] "} when not completed, followed by the task name.
     *
     * @return display string for this task
     */
    public String toString() {
        String doneMark = "[] ";
        if (isDone) {
            doneMark = "[X] ";
        }
        return doneMark + taskName;
    }
}
