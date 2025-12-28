package bytebuddy.task;

/**
 * Represents a to-do task in ByteBuddy.
 * A To-do has only a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Creates a new to-do task that is not yet completed.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Creates a new to-do task with a specified completion status.
     *
     * @param description Description of the to-do task.
     * @param isDone true if the to-do is already completed; false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        return super.toString();
    }
}
