package diablo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task, which has a description, start and end date, and can be marked as done.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }


    /**
     * Gets the type of task.
     *
     * @return Capital letter "E" which represents an event.
     */
    @Override
    public String getType() {
        return "E";
    }


    /**
     * Gets the start date of the event.
     *
     * @return String representation of the start date in the format yyyy-MM-dd.
     */
    public String getFromDate() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    /**
     * Gets the end date of the event.
     *
     * @return String representation of the end date in the format yyyy-MM-dd.
     */
    public String getToDate() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" +
                super.toString() +
                " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                ")";
    }

}
