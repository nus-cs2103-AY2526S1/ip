package yappy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import yappy.exception.YappyException;


/**
 * Represents an <code>Event</code> task which is a type of <code>Task</code>.
 * An <code>Event</code> requires a description, a completion status,
 * a start date and an end date.
 *
 * <p>This class extends the <code>Task</code> class and overrides methods
 * for file storage and string representation.</p>
 */
public class Event extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new {@code Event} task with the given description,
     * completion status, from date and to date.
     *
     * @param description Textual description of the task.
     * @param isCompleted {@code true} if the task has been completed,
     *                    {@code false} otherwise.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, Boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E|" + this.isCompleted() + "|" + this.getDescription() + "|"
                + this.from.format(FORMATTER) + "|" + this.to.format(FORMATTER) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Returns the start date of the event.
     * Returned object is of type LocalDateTime.
     *
     * @return The start date and time of the event.
     */

    public LocalDateTime getFromDate() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     * Returned object is of type LocalDateTime.
     *
     * @return The end date and time of the event.
     */
    public LocalDateTime getToDate() {
        return this.to;
    }


    @Override
    public String reschedule(LocalDateTime newFrom, LocalDateTime newTo) throws YappyException {
        this.from = newFrom;
        this.to = newTo;
        return "Successfully rescheduled event: \n" + this;
    }
}

