package olaf.tasks;

import olaf.TaskType;

/**
 * Represents a ToDo task which has a description and can be marked done or undone.
 * This class extends the base Task class with a type TODO.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
        assert this.description != null : "Description should not be null";
        assert this.type != null : "Task type should not be null";
    }
}
