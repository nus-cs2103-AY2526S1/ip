package diablo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents a deadline given by a description and due date, and can be marked as done.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }


    /**
     * Gets the type of task.
     *
     * @return Capital letter "D" which represents a deadline.
     */
    @Override
    public String getType() {
        return "D";
    }


    /**
     * Gets the due date of the task.
     *
     * @return String representation of the due date in the format yyyy-MM-dd.
     */
    public String getByDate() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
