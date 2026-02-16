package shadow.tasks;

/**
 * Represents a task with a name and a completion status.
 * A task can be marked as completed or unmarked if it is not completed.
 */
public class Task {
    private final String name;
    private boolean isMarked = false;

    /**
     * Constructs a new Task with the specified name.
     *
     * @param name the name or description of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as completed.
     * <p>
     * Sets the {@code isMarked} flag to {@code true} and prints a confirmation
     * message to the console, including the task's string representation.
     * </p>
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task (marks it as not completed).
     * <p>
     * Sets the {@code isMarked} flag to {@code false} and prints a confirmation
     * message to the console, including the task's string representation.
     * </p>
     */
    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.isMarked ? "X" : " ",
                this.name
        );
    }

    /**
     * Checks if this task's name contains the specified substring.
     *
     * @param match the substring to search for in the task's name
     * @return true if the name contains the substring; false otherwise
     */
    public boolean contains(String match) {
        return this.name.contains(match);
    }


}
