package joobot.task;

/**
 * Represents an todo task with a description.
 * Subclasses of {@code Task} define specific task types (e.g., ToDo, Deadline, Event).
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * Returns a string representation of this todo task for display.
     *
     * @return The formatted event task string.
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString();
    }

    /**
     * Returns the file format string for this todo task.
     *
     * @return The string in file storage format: "T | status | description".
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
