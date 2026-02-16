package betty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import betty.exception.BettyException;

/**
 * Represents an event task inherited from the task class
 * An event is a simple task with a description, completion status, duration from and to
 *
 * @see betty.task.Task
 */

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Construct a new event task with given description, isDone status, and duration from and to
     * @param description the details of the event
     * @param from        when the event starts
     * @param to          when the event ends
     * @param isDone      whether the task has been completed
     * @throws BettyException BettyException if there is error in creating the task
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) throws BettyException {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns the string representation of the event task for display purposes
     * @return a formatted string with the task completion status, description and duration
     */
    @Override
    public String toString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("[E]%s(from: %s to: %s)", super.toString(),
                                                        this.from.format(dateFormat),
                                                        this.to.format(dateFormat));
    }
    /**
     * Returns the string representation of the duration task for storage saving purposes
     * @return a formatted string with the task completion status, description and duration for saving into storage
     */
    @Override
    public String toSaveString() {
        // Format time to pattern MMM dd yyyy
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("E | %s | %s | %s", super.toSaveString(),
                                                        this.from.format(dateFormat),
                                                        this.to.format(dateFormat));
    }
}
