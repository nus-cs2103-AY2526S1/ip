package cheryl.task;

/**
 * Represents a todo task without a specific date/time.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo with the given title.
     *
     * @param title The title of the todo task
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return String in the format "[T][X] title" or "[T][ ] title"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}