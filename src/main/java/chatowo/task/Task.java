package chatowo.task;

/**
 * Abstract base class for all task types.
 * Provides common functionality for tasks in the system.
 */
public abstract class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Updates the isDone status to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Updates the isDone status to false.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a string format suitable for storage.
     *
     * @return String representation for data storage
     */
    public String toDataString() {
        if (this.isDone) {
            return " | 1 | " + name;
        } else {
            return " | 0 | " + name;
        }
    }

    /**
     * Creates a string representation of the task.
     *
     * @return Formatted string showing task status and details
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
