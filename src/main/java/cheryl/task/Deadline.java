package cheryl.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be done by a specific date.
 */
public class Deadline extends Task {
    LocalDate dueDate;

    /**
     * Creates a new Deadline task with a title and due date.
     *
     * @param title The title of the task
     * @param dueDate The due date of the task
     */
    public Deadline(String title, LocalDate dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    /**
     * Returns the due date of the task.
     *
     * @return the LocalDate representing the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return String in the format "[D][ ] title (by: yyyy-mm-dd)"
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}