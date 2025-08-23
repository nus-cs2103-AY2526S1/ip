package BobbyWasabi.Tasks;

/**
 * Represents a simple task with no time constraints.
 * Inherits from the {@code Task} class.
 */
public class ToDo extends Task {
    /**
     * Constructs a {@code ToDo} task with the given description and marked status.
     *
     * @param description The description of the to-do task.
     * @param isMarked Whether the task is marked as completed.
     */
    public ToDo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    /**
     * Returns a string representation of the to-do task,
     * including its type and inherited details (marked status and description).
     *
     * @return Formatted string representation of the to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a serialized string of the to-do task suitable for saving to a file.
     * Format: {@code T|<description>|<checked>}.
     *
     * @return A string representing the to-do task for file storage.
     */
    @Override
    public String getData() {
        return String.format("T|%s|%s",
                super.getDescription(), super.checked());
    }
}
