package mambo.task;

/**
 * Represents a task which does not have any deadlines or dates attached to it
 *
 * @author kentalim2
 */
public class ToDoTask extends Task {

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("T / %s / %s", this.isMarked(), this.getDescription());
    }

    @Override
    public String toString() {
        return "(T) " + super.toString();
    }
}
