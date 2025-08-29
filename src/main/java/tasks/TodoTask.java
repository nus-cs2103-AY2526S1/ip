package tasks;
import exception.RomidasException;

/**
 * Represents a simple todo task without any time constraints.
 * Extends the base Task class to provide todo-specific functionality.
 */
public class TodoTask extends Task {
    /**
     * Constructs a new TodoTask with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Converts a string array representation back to a TodoTask.
     * Parses the task data from storage format and reconstructs the TodoTask object.
     * Validates the input format and sets the completion status.
     *
     * @param parts Array containing task type, completion status, and description.
     * @return The reconstructed TodoTask.
     * @throws RomidasException If the input format is invalid or missing required parts.
     */
    public static Task toTask(String[] parts) throws RomidasException {
        if (parts.length != 3) {
            throw new RomidasException("Invalid number of arguments. Expected 3 but got " + parts.length);
        }
        TodoTask task = new TodoTask(parts[2]);
        if (parts[1].equals("1")) {
            task.setIsDone(true);
        }
        return task;
    }

    @Override
    public String toText() {
        return "T | " + (this.isDone ? "1 | ": "0 | ") + this.getDescription();
    }

    @Override
    public String getStatus() {
        return "[T]";
    }

}
