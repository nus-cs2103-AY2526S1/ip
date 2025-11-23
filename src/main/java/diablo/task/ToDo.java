package diablo.task;

/**
 * Represents a ToDo Task, which has a description and can be marked as done.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }


    /**
     * Gets the type of task.
     *
     * @return Capital letter "T" which represents a todo task.
     */
    @Override
    public String getType() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
