package briar.task;

/**
 * Represents a todo task that can be stored in a TaskList.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the representation of the task to be written into a file as text.
     */
    @Override
    public String toText() {
        return "T|" + super.toText();
    }
}
