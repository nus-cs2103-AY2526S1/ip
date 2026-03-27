package pepe.task;

/**
 * Represents a generic task.
 * <p>
 * Each task has a name and a isMarked status indicating whether it is completed.
 * Subclasses must implement specific behavior for due dates and file formatting.
 */
public abstract class Task {
    /** The name or description of the task. */
    private final String name;

    /** Whether the task is isMarked as done (true) or not (false). */
    private boolean isMarked;

    /**
     * Constructs a new Task with the given name.
     * The task is initially unmarked (not done).
     *
     * @param name the name or description of the task
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }
    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isMarked = true;
    }
    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {

        this.isMarked = false;
    }
    /**
     * Returns the name of the task.
     *
     * @return the task's name
     */
    public String getName() {

        return this.name;
    }

    /**
     * Returns an integer representing whether the task is isMarked.
     *
     * @return 1 if the task is isMarked as done, 0 if unmarked
     */
    public int checkMarked() {
        return this.isMarked ? 1 : 0;
    }

    /**
     * Returns a string representation of the task for display.
     * <p>
     * Format: [X] taskName if isMarked, [ ] taskName if unmarked.
     *
     * @return a human-readable string representing the task
     */
    @Override
    public String toString() {
        String markSymbol = this.isMarked ? "X" : " ";
        return "[" + markSymbol + "] " + this.name;
    }

    /**
     * Returns a string representing the task in a file-friendly format.
     * <p>
     * Subclasses must implement this to match the storage file format.
     *
     * @return the task formatted for saving to a file
     */
    public abstract String toFileFormat();
}
