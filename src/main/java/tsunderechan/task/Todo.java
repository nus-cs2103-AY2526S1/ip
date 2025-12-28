package tsunderechan.task;

/**
 * Represents a todo Task with a description.
 */
public class Todo extends Task {

    /**
     * Instantiates a Todo object.
     *
     * @param description Description of the Todo Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates a Todo object.
     * This overloaded method allows for manually setting the isDone field.
     *
     * @param description Description of the Todo Task.
     * @param isDone Whether the task has already been completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getIcon() {
        return "T";
    }

    @Override
    public String getTiming() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
