package steven.task;

/**
 * Represents a task that can be marked as done or not done.
 * This class provides methods to update and check the
 * completion status of a task. Each task also has a description.
 *
 * @author w3njii
 */
public class Task {
    private final String description;
    private boolean isDone = false;

    /**
     * Creates a new task with the given description.
     *
     * @param description a description of the task
     */
    public Task(String description) {
        assert description != null : "Task description must not be null";
        this.description = description;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task in a local save format.
     *
     * @return the string representation of the task in a local save format
     */
    public String convertToSaveFormat() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns a string representation of this task, showing whether it
     * is completed and its description.
     *
     * <p>The format is:</p>
     * <ul>
     *   <li>{@code [X] description} if the task is done</li>
     *   <li>{@code [ ] description} if the task is not done</li>
     * </ul>
     *
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
