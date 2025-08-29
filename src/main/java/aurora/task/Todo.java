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
     */
    public Todo(String description) {
        super(description);
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
    public String toCSV() {
        return "T," + super.toCSV() + "\n";
    }
}
