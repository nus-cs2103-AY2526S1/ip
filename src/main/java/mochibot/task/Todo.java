package mochibot.task;

/**
 * This class represents a {@code Todo} task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return this.getType() + super.toString();
    }
}
