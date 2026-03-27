package tom;

/**
 * Represents a task with a description and a flag denoting if it has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String id;
    protected boolean isPrioritised;

    /**
     * Constructs a Task.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.id = "T";
        this.isPrioritised = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    public void prioritise() {
        this.isPrioritised = true;
    }

    public String showPriority() {
        return isPrioritised ? "*" : "";
    }

    /**
     * Returns a description of the Task in the correct format to be stored in the text file.
     * @return Formatted string.
     */
    public String saveDesc() {
        return showPriority() + id + " | " + (isDone ? "1 | " : "0 | ") + description;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
