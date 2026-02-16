package luna.task;

/**
 * Represents a {@code Task} that has a name and a done status.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}