package merlie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean hasToTime;
    private boolean hasFromTime;

    /**
     * Constructs an Event with the given description, start time, end time, and
     * flags indicating whether each date includes a specific time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param hasFromTime Whether the start time includes a specific time.
     * @param to End time of the event.
     * @param hasToTime Whether the end time includes a specific time.
     */
    public Event(String description, LocalDateTime from, boolean hasFromTime, LocalDateTime to, boolean hasToTime) {
        super(description);
        this.from = from;
        this.hasFromTime = hasFromTime;
        this.to = to;
        this.hasToTime = hasToTime;
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time as a LocalDateTime.
     */
    private LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return End time as a LocalDateTime.
     */
    private LocalDateTime getTo() {
        return to;
    }

    private boolean getHasFromTime() {
        return this.hasFromTime;
    }

    private boolean getHasToTime() {
        return this.hasToTime;
    }

    /**
     * Sets or updates the start time of the event.
     *
     * @param from The new start time.
     */
    private void setFrom(LocalDateTime from, boolean hasFromTime) {
        this.from = from;
        this.hasFromTime = hasFromTime;
    }

    /**
     * Sets or updates the end time of the event.
     *
     * @param to The new end time.
     */
    private void setTo(LocalDateTime to, boolean hasToTime) {
        this.to = to;
        this.hasToTime = hasToTime;
    }

    @Override
    public boolean isDuplicate(Task other) {
        if (this.isSameDescription(other)) {
            if (other instanceof Event e) {
                return this.from.equals(e.getFrom()) && this.to.equals(e.getTo());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isUpdateSuccessful(Task other) {
        if (this.isSameDescription(other) && other instanceof Event e) {
            setFrom(e.getFrom(), e.getHasFromTime());
            setTo(e.getTo(), e.getHasToTime());
            return true;
        }
        return false;
    }

    @Override
    public String format() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
            + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " /to "
            + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        String fromStr;
        String toStr;

        if (hasFromTime) {
            fromStr = from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        } else {
            fromStr = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        if (hasToTime) {
            toStr = to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        } else {
            toStr = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        return "[E]" + super.toString() + " (from: " + fromStr + "; to: " + toStr + ")";
    }
}
