package tom;

/**
 * Represents a task to be done by the user.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return super.showPriority() + "[T]" + super.toString();
    }
}
