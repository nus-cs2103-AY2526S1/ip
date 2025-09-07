package king.task;

import king.KingException;

/**
 * Todo task with basic description and completion
 */
public class Todo extends Task {
    /**
     * Instantiates a todo task based on the description of the task.
     * If no description is provided, throws a missing description exception.
     *
     * @param description Description of the task.
     * @param priority    Priority of the task.
     * @throws KingException Error in creation of task.
     */
    public Todo(String description, Priority priority) throws KingException {
        super(description, priority);
    }

    @Override
    public Type getType() {
        return Type.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
