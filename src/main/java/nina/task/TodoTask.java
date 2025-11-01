package nina.task;

/**
 * Represents a TodoTask with description.
 */
public class TodoTask extends Task {
    private static final long serialVersionUID = 10L;

    /**
     * Constructs a TodoTask object
     *
     * @param description description of the task
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toSaveableLine() {
        return basePrefix("T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
