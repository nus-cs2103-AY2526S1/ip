package karl.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline type task with a due date.
 */
public class Deadline extends Task {
    private LocalDate by;  // store deadline as LocalDate
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a Deadline task with description and due date string.
     * @param description task description
     * @param by due date in yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description);
        // parse input date string to LocalDate
        this.by = LocalDate.parse(by.trim(), INPUT_FORMAT);
    }

    /**
     * Returns the due date.
     * @return LocalDate representing due date
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns string representation including due date.
     * @return string representation
     */
    @Override
    public String toString() {
        // format date as 'MMM dd yyyy' e.g. Oct 15 2019
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
