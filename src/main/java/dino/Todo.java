package dino;

/**
 * Represents a Todo type task with task description.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the string representation of the Todo task
     *
     * @return string containing the task type, status and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo task for storage.
     *
     * @return string of the task in the storing format
     */
    @Override
    public String toStoreFormat() {
        String tagName = this.tag != null ? this.tag : "";
        return "T | " + (isDone ? "1" : "0") + " | " + description + " | " + tagName;
    }
}

