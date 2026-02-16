package betty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import betty.exception.BettyException;

/**
 * Represents a deadline task inherited from the task class
 * A deadline is a simple task with a description, completion status, and complete by date
 *
 * @see betty.task.Task
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Construct a new deadline task with the given description, isDone status, and deadline date
     * @param description the details of the task
     * @param by          when the task has to be completed by
     * @param isDone      whether the task has been completed
     * @throws BettyException BettyException if there is error in creating the task
     */
    public Deadline(String description, LocalDate by, boolean isDone) throws BettyException {
        super(description, isDone);
        this.by = by;
    }
    /**
     * Returns the string representation of the deadline task for display purposes
     * @return a formatted string with the task completion status, description and deadline
     */
    @Override
    public String toString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("[D]%s(by: %s)", super.toString(), this.by.format(dateFormat));
    }
    /**
     * Returns the string representation of the deadline task for storage saving purposes
     * @return a formatted string with the task completion status, description and deadline for saving into storage
     */
    @Override
    public String toSaveString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String doneValue = super.isDone() ? "1" : "0";
        return String.format("D | %s | %s", super.toSaveString(), this.by.format(dateFormat));
    }
}
