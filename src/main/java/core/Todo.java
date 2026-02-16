package core;

/**
 * Represents a Todo task with a description.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Compares this todo with another task for ordering.
     * Todos are sorted alphabetically by description.
     * @param other The task to compare with
     * @return A negative integer, zero, or a positive integer as this todo is less than,
     *         equal to, or greater than the other
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof Todo) {
            return this.getDescription().compareToIgnoreCase(other.getDescription());
        } else {
            // For Deadline and Event tasks, todos come last (later in sort order)
            return 1;
        }
    }

    /**
     * @return String representation of the Todo task for display purposes.
     */
    @Override
    public String toString() {
        String todoDescriptionForDisplay = String.format("[T] %s", super.toString());
        return todoDescriptionForDisplay;
    }

    /**
     * @return String representation of the Todo task for storage purposes.
     */
    @Override
    public String toStorageString() {
        String todoDescriptionForStorage = "T | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription();
        return todoDescriptionForStorage;
    }
}
