package shef.task;

/**
 * Represents todo tasks.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toCsvString() {
        return "T," + super.toCsvString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
