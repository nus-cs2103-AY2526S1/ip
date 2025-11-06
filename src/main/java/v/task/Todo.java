package v.task;

import v.enums.TaskType;

/**
 * A simple todo task with just a description.
 * The most basic of vows, yet no less important.
 */
public class Todo extends Task {
    /**
     * Creates a new todo task.
     *
     * @param description The task to be done.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Returns the serialized representation of this todo for persistence.
     * Format: {@code T | <done:1|0> | <description>}.
     *
     * @return A save-friendly string for this todo.
     */
    @Override
    public String toSaveString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
