package sunoo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end time.
 * An event occurs during a specific period.
 */
public class Event extends Task {

    /** The start date and time of the event */
    private final LocalDateTime from;

    /** The end date and time of the event */
    private final LocalDateTime to;

    /**
     * Creates a new event task.
     *
     * @param isDone Whether the task is completed.
     * @param description Description of the task.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     * Overrides the parent method to prepend the "[E]" icon and show the start and end times.
     *
     * @return "[E]" followed by the task's status icon, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy h:mma")) + "; to: "
                + to.format(DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy h:mma")) + ")";
    }

    /**
     * Returns a string representation of the event task for saving to a text file.
     * Overrides the parent method to prepend "E" and include the start and end times.
     *
     * @return "E" followed by the base task text representation, start time, and end time.
     */
    @Override
    public String getTxtRepresentation() {
        return "E" + super.getTxtRepresentation() + " | " + from + " | " + to;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event e) {
            return description.equals(e.description) && from.equals(e.from) && to.equals(e.to);
        }
        return false;
    }
}
