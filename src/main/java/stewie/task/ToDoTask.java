package stewie.task;

/**
 * Represents a simple to-do task without specific timing constraints.
 */
public class ToDoTask extends Task {

    /**
     * Creates a new ToDoTask with the specified description.
     *
     * @param description The task description.
     */
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}
