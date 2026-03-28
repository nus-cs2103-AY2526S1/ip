package sanbot.task;

/**
 * Parent class for all task types with name and completion status.
 */
public class Task {
    /** The task description */
    private final String name;
    /** Whether the task is completed */
    private boolean isDone;

    /**
     * Creates a new task with specified name, initially not done.
     *
     * @param name the task description
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns formatted string representation for display.
     * Format: "[X] task description" if done, "[ ] task description" if not done
     *
     * @return formatted string with completion status and description
     */
    @Override
    public String toString() {
        String check = this.isDone ? "[X] " : "[ ] ";
        return check + this.name;
    }

    /**
     * Returns formatted string for file storage.
     * Format: "| 1 | task description" if done, "| 0 | task description" if not done
     *
     * @return formatted string for saving to file
     */
    public String toSaveString() {
        int check = this.isDone ? 1 : 0;
        return String.format("| %d | %s", check, this.name);
    }
}