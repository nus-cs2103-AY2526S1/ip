package botchat.task;

/**
 * Represents a task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a task with a description.
     * @param description the String description of a task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorage() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
