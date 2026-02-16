package core;

/**
 * Abstract class representing a Task.
 */
public abstract class Task implements Comparable<Task> {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task class.
     * @param description Description of the task
     * @param isDone Whether the task is completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * @return String description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Boolean indicating if the task is done.
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
     * @return Abstract method to convert the task to a storage string.
     */
    public abstract String toStorageString();

    /**
     * Parses a task from a storage string and returns the corresponding Task object.
     * @param line Task string from storage file.
     * @return Task object corresponding to the storage string.
     * @throws IllegalArgumentException if the task type is unknown.
     */
    public static Task fromStorageString(String line) {
        assert line != null : "Storage line should not be null";
        
        String[] parts = line.split(" \\| ");
        
        assert parts.length >= 3 : "Storage line should have at least 3 parts";
        
        switch (parts[0]) {
        case "T":
            return new Todo(parts[2], parts[1].equals("1"));
        case "D":
            return new Deadline(parts[2], parts[1].equals("1"));
        case "E":
            return new Event(parts[2], parts[1].equals("1"));
        default:
            throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }
    }

    /**
     * Compares this task with another task for ordering.
     * The default implementation compares by description alphabetically.
     * Subclasses should override this method for specific sorting behavior.
     * @param other The task to compare with
     * @return A negative integer, zero, or a positive integer as this task is less than,
     *         equal to, or greater than the other
     */
    @Override
    public int compareTo(Task other) {
        return this.description.compareToIgnoreCase(other.description);
    }

    /**
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
