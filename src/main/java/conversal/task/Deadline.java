package conversal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the Conversal chatbot.
 *
 * A Deadline has a description and a due date.
 * It is displayed with the task type symbol [D].
 * Shows the formatted due date when listed.
 *
 */
public class Deadline extends Task {
    // Fields
    protected LocalDate dueDate;

    /**
     * Creates a new Deadline task with the given description
     * and due date.
     *
     * @param description description of the deadline task
     * @param dueDate     the due date of the task
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description, TaskType.DEADLINE);
        assert dueDate != null : "dueDate must not be null";
        this.dueDate = dueDate;
    }

    /**
     * Returns the due date of this deadline task.
     *
     * @return the due date as a LocalDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns the string representation of this Deadline.
     * Includes the task description and the due date in the format
     * (by: MMM d yyyy)
     *
     * @return the string form of this deadline task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDate = dueDate.format(formatter);
        return super.toString() + " (by: " + formattedDate + ")";
    }
}
