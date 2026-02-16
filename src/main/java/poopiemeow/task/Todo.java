package poopiemeow.task;

import poopiemeow.exception.EmptyDescriptionException;

/**
 * Represents a simple todo task in the PoopieMeow application.
 * A todo is a basic task type that consists only of a description and completion status.
 * Unlike deadlines and events, todos have no associated time constraints.
 *
 * @author tch1001
 * @version 1.0
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     * The description must not be empty or contain only whitespace.
     *
     * @param description a description of what the todo involves
     * @throws EmptyDescriptionException if the description is empty or contains only whitespace
     */
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Converts the todo task to its file storage format.
     * The format follows the pattern: T|status|description
     *
     * @return a string representation of the todo suitable for file storage
     */
    @Override
    public String toFileString() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;
    }
}
