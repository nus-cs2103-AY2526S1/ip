package winnie.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description, TaskEnum.TODO);
    }
}
