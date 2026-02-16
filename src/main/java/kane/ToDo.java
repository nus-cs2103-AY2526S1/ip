package kane;

/**
 * Represents a simple to-do task without additional time attributes.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }
}
