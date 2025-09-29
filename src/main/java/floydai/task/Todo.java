package floydai.task;

/**
 * Represents a simple to-do task without any time constraints.
 * <p>
 * A {@code Todo} is the most basic type of {@link Task}, defined
 * only by its description and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a new {@code Todo} with the given description.
     * The task is initialized as not done.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}
