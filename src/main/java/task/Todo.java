package task;

import exception.FrennyException;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    private Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Creates a new Todo task from the given detail string and completion status.
     *
     * @param detail The detail string containing the description of the Todo task.
     * @param isDone The completion status of the task.
     * @return A new Todo task.
     * @throws FrennyException If the detail string is empty.
     */
    public static Todo addTodoTask(String detail, boolean isDone) throws FrennyException {
        if (detail.trim().isEmpty()) {
            throw new FrennyException("The description of a todo cannot be empty my fren :(");
        }
        return new Todo(detail, isDone);
    }

    public String generateCommandString() {
        return "todo " + this.description;
    }

    public String generateHistoryFileEntry() {
        return String.format("%s | %s", getDoneEncoding(), generateCommandString());
    }
}
