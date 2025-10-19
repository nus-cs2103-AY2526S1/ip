package reboot.task;

/**
 * Represents a basic task that has to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description and isDone.
     * @param description Description of the todo task.
     * @param isDone Status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the task to a string that can be stored in a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts the task to a string that can be displayed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
