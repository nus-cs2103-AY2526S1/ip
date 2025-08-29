package aurora.task;

/**
 * Represents a Todo task.
 * Task with description and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with the given description.
     *
     * @param description the task details.
     * @param isDone The completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toText() {
        return "T|" + super.toText() + "\n";
    }
}
