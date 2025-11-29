package kleebot.task;

/**
 * Represents a deadline object
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    public String getType() {
        return "D";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")" + ", priority: " + getPriority();
    }
}
