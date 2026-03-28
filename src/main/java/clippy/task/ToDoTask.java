package clippy.task;

import clippy.ClippyException;

/**
 * Represents a todo task.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the given description.
     *
     * @param description The description of the todo task.
     * @throws ClippyException If the description is null or empty.
     */
    public ToDoTask(String description) throws ClippyException {
        super(validateDescription(description));
    }

    /**
     * Validates the description of the todo task.
     *
     * @param description The description to validate.
     * @return The validated description.
     * @throws ClippyException If the description is null or empty.
     */
    private static String validateDescription(String description) throws ClippyException {
        if (description == null || description.trim().isEmpty()) {
            throw new ClippyException("A todo task must have a description.");
        }
        return description;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
