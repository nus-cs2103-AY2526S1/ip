package Meal;

/**
 * Represents a todo task
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * String representation of the todo task
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
