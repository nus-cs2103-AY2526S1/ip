/**
 * Abstract representation of a task.
 * Contains common properties like description and completion status.
 */

package salah;

/**
 * Base class for all tasks.
 */
public abstract class Task {
    /** The textual description of the task. */
    private final String description;
    /** Whether the task is marked as complete. */
    private boolean isComplete;

    /**
     * Returns whether this task is complete.
     *
     * @return {@code true} if the task is complete, {@code false} otherwise
     */
    public boolean getIsComplete() {
        return this.isComplete;
    }

    /** Marks this task as complete. */
    public void markAsComplete() {
        this.isComplete = true;
    }

    /** Marks this task as incomplete. */
    public void markAsIncomplete() {
        this.isComplete = false;
    }

    /**
     * Constructs a new {@code Task} with the given description.
     * Tasks are initially marked as incomplete.
     *
     * @param description textual description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of this task.
     * By default, returns only the description.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Serializes this task into a string suitable for saving to disk.
     *
     * @return serialized string representation of this task
     */
    public abstract String serialize();

    /**
     * Deserializes a line from the save file into a {@code Task}.
     * Delegates to the appropriate subclass based on the type code.
     * <ul>
     *   <li>"T" → {@link ToDos}</li>
     *   <li>"D" → {@link Deadlines}</li>
     *   <li>"E" → {@link Events}</li>
     * </ul>
     *
     * @param line the serialized task line
     * @return the corresponding {@code Task}, or {@code null} if type is unrecognized
     */
    public static Task deserialize(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
        return switch (parts[0]) {
            case "T" -> ToDos.fromData(parts);
            case "D" -> Deadlines.fromData(parts);
            case "E" -> Events.fromData(parts);
            default -> null;
        };
    }
    /**
     * Two tasks are considered equal if they have the same description.
     * Completion status is not considered for equality.
     *
     * @param o the other object to compare against
     * @return {@code true} if both tasks have the same description, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task other = (Task) o;
        return description.equals(other.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}