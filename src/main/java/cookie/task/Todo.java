package cookie.task;

import cookie.exception.CookieException;

/**
 * Represents a task with a specific description.
 * Does not have a date or time like its base Task class.
 */
public class Todo extends Task {

    /**
     * Creates new todo task with the given description.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts todo task into format for saving.
     *
     * @return Saved format of todo task.
     */
    @Override
    public String toStoredFormat() {
        int status = isDone ? 1 : 0;
        return "T | " + status + " | " + description;
    }

    /**
     * Updates todo task description with updated information.
     *
     * @param newInformation  Updated description.
     */
    @Override
    public void update(String newInformation) throws CookieException {
        if (newInformation == null || newInformation.isBlank()) {
            throw new CookieException("Todo task cannot be empty.");
        }
        this.description = newInformation.strip();
    }

    /**
     * Returns todo task in String format with its type and description.
     *
     * @return String form of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

