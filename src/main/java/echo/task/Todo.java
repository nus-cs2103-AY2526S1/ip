package echo.task;

/**
 * Represents a todo task. A <code>Todo</code> object is a
 * subtype of <code>Task</code>
 * that contains only a description with no associated time or date.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
