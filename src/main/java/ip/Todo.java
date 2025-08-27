package ip;

/**
 * Represents a Todo task, which is a task without any date/time constraints.
 * Inherits from the base Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon of the task.
     * For Todo, this is "T".
     *
     * @return "T" as the type icon
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }
}
