package david.task;

/**
 * Represents a Task which has no deadline.
 */
public class Todo extends Task {

    /**
     * Initialises a Todo task.
     *
     * @param text Task description.
     */
    public Todo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
