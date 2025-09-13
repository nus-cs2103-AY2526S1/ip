package hermione.tasks;

/**
 * Represents a ToDo task in the Hermione application.
 */
public class ToDo extends Task {
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getCode() + "]" + super.toString();
    }
}
