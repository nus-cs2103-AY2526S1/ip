package Chunky.Task;

/**
 * Deadlines class, has a deadline for each Task Objects
 */
public class Deadlines extends Task {
    private String dueDate;
    private String location;

    public Deadlines(String description, String dueDate, String location) {
        super(description);
        this.dueDate = dueDate;
        this.location = location;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + super.formatDate(this .dueDate) + ") at " + this.location;
    }

    public String getBy() {
        return this.dueDate;
    }

    public String getLocation() {
        return this.location;
    }
}
