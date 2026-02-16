package bestie;

/**
 * Task representing a simple to-do item with no additional metadata.
 */
public class Todo extends Task {
    /**
     * Creates a todo task with the specified description.
     *
     * @param description what the user needs to do
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}