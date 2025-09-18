package tony.tasks;

/**
 * Represents a task with only a description.
 * Inherits common task behavior from {@link Task}.
 */
public class ToDo extends Task {

    public ToDo(String command) {
        super(command);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toDataFormat() {
        return "T | " + (isDone() ? 1 : 0) + " | " + this.command;
    }
}
