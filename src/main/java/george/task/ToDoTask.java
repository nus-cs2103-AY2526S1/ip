package george.task;

import george.exceptions.GeorgeException;

/**
 * Represents a todo task without any date or time constraints.
 * Extends the base Task class for basic task functionality.
 */
public class ToDoTask extends Task {
    /**
     * Constructs a ToDoTask with the specified description.
     *
     * @param description The description of the todo task
     * @throws GeorgeException If the description is invalid
     */
    public ToDoTask(String description) throws GeorgeException {
        this(description, false);
    }

    /**
     * Constructs a ToDoTask with description and completion status.
     *
     * @param description The description of the todo task
     * @param isDone The completion status of the task
     * @throws GeorgeException If the description is invalid
     */
    public ToDoTask(String description, boolean isDone) throws GeorgeException {
        super(description);

        assert description != null : "Description cannot be null - should be caught by super()";
        assert !description.trim().isEmpty() : "Description cannot be empty - should be caught by super()";
        this.isDone = isDone;
        assert this.getDescription().equals(description) : "Description should be properly set";
    }

    /**
     * Returns the type identifier for todo tasks.
     *
     * @return The string "[T]" representing a todo task
     */
    @Override
    public String getType() {
        return "[T]";
    }

    /**
     * Returns the formatted display text for the todo task.
     *
     * @return A formatted string containing task type, status, and description
     */
    @Override
    public String getDisplayText() {
        String displayText = this.getType() + this.getStatus() + " "
                + this.getDescription();

        assert displayText != null : "Display text cannot be null";
        assert displayText.contains("[T]") : "Display text should contain task type";
        assert displayText.contains(this.getDescription()) : "Display text should contain description";
        assert displayText.contains("[") && displayText.contains("]") : "Display text should contain status brackets";

        return displayText;
    }

    /**
     * Returns a string representation for storage purposes.
     *
     * @return A pipe-separated string containing task type, status, and description
     */
    @Override
    public String toString() {
        String tempString = getType().charAt(1) + " | " + (isDone() ? 1 : 0) + " | " + getDescription();
        assert tempString != null : "Storage string cannot be null";
        assert tempString.startsWith("T | ") : "Storage string should start with 'T | '";
        assert tempString.contains(" | ") : "Storage string should contain pipe separators";
        assert tempString.contains(isDone() ? " | 1 | " : " | 0 | ") : "Storage string should contain correct status";
        assert tempString.endsWith(getDescription()) : "Storage string should end with description";

        return tempString;

    }
}
