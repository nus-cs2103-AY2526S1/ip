package john.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    /**
     * Constructor for deadline.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }
    /**
     * Constructor for deadline with specified mark as done.
     */
    public Deadline(String name, LocalDate deadline, boolean done) {
        super(name, done);
        this.deadline = deadline;
    }
    /**
     * Constructor for deadline that creates a copy of another deadline.
     */
    public Deadline(Deadline other) {
        super(other);
        this.deadline = other.deadline;
    }
    /**
     * Creates a deep copy of this task.
     */
    @Override
    public Deadline copy() {
        return new Deadline(this);
    }
    /**
     * String representation of the deadline for displaying to user.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
    /**
     * String representation of the deadline for saving in the save file.
     */
    @Override
    public String writeString() {
        return "D | " + super.writeString() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
