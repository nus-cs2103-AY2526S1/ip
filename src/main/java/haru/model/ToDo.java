package haru.model;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with name.
     *
     * @param name the task name
     */
    public ToDo(String name) {
        super(name, TaskType.TODO);
    }
}
