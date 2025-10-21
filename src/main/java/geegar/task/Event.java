package geegar.task;

import java.time.LocalDateTime;

/**
 * Event type that contains description and a from and to date&time
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event instance based on the description, from and to time
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event instance based on the description, from and to time, and if task is completed
     *
     * @param description
     * @param from
     * @param to
     * @param isDone
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * returns the from localdatetime
     * @return
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * returns the to localdatetime
      * @return
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toSaveString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(Task.SAVE_FORMATTER)
                + " to: " + this.to.format(Task.SAVE_FORMATTER) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(Task.DISPLAY_FORMATTER)
                + " to: " + this.to.format(Task.DISPLAY_FORMATTER) + ")";
    }
}
