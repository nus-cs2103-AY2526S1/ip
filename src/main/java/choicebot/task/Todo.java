package choicebot.task;

import choicebot.ChoiceBotException;

/**
 * Represents a todo task.
 * <p>
 * A todo task contains only a description and is identified with a "T".
 * </p>
 */
public class Todo extends Task {
    /**
     * Constructs a new todo task with the given description and completion status.
     *
     * @throws ChoiceBotException If description is null or blank.
     */
    public Todo(String description, Boolean isDone) throws ChoiceBotException {
        super(description, isDone);
        this.type = "T";

        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("You must add a description for toDo tasks. Please try again.");
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Prepends the string with [T] to indicate that it is a todo task.
     * </p>
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
