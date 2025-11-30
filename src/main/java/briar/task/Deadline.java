package briar.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that can be stored in a TaskList.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Creates a Deadline task.
     * @param description Description of task.
     * @param date Deadline date of the task in yyyy-mm-dd format.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the representation of the task to be written into a file as text.
     */
    @Override
    public String toText() {
        return "D|" + super.toText() + "|" + date;
    }
}
