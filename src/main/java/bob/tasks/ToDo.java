package bob.tasks;

/**
 * Represents a simple "to do" task.
 */
public class ToDo extends Task {
    public ToDo(String description) throws IllegalArgumentException{
        super(description);
    }

    public ToDo(String description, boolean complete) throws IllegalArgumentException{
        super(description, complete);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
