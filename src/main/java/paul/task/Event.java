package paul.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import paul.exception.PaulException;

/**
 * An event task for Paul.
 * Includes a from date and to date, in MMM dd yyyy format.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event task from the description, from date and to date.
     *
     * @param description The description of the task.
     * @param from The start date in yyyy-mm-dd format.
     * @param to The end date in yyyy-mm-dd format.
     * @throws PaulException if the to date is before the from date.
     */
    public Event(String description, LocalDate from, LocalDate to) throws PaulException {
        super(description);

        if (to.isBefore(from)) {
            throw new PaulException("Hey! Your /to date is earlier than your /from date. "
                    + "Time travelling Paul isn't real!");
        }

        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of an event in MMM dd yyyy format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ", to: "
                + to.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    /**
     * Returns the string representation of an event for saving into a file.
     *
     * @return The event task string for saving.
     */
    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + " | " + from + " | " + to;
    }

    /**
     * Checks if the given object is equal to an Event.
     * They are equal if they have the same description, from date and to date.
     *
     * @param obj the reference object with which to compare.
     * @return true if this is equal to the Event.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return this.description.equalsIgnoreCase(other.description)
                && this.from.equals(other.from)
                && this.to.equals(other.to);
    }
}
