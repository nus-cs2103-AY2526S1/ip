package baymax.task;

/**
 * Represents a simple to-do task.
 * <p>
 * Displays itself with a {@code [T]} tag.
 * </p>
 */
public class ToDo extends Task {
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveFormat() {
        return String.format("T | %b | %s", this.isDone, this.description);
    }
}
