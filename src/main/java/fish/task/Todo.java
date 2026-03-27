package fish.task;

/**
 * Represents a Todo task that is to be completed.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task identification.
     *
     * @return the string type of the Todo task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a string of the task.
     *
     * @return A string in the TaskList.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo task to be print in the output file.
     *
     * @return A pipe-separated string.
     */
    @Override
    public String toFileString() {
        return String.join(" | ", "T", isDone ? "1" : "0", description);
    }
}
