package stella;

/**
 * Represents a type of task that have a deadline. It is represented by
 * two strings, which are the description and deadline.
 */
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
