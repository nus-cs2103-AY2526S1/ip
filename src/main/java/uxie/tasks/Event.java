package uxie.tasks;

import java.time.LocalDateTime;
import java.util.List;

import uxie.interfaces.DateTimeParse;

/**
 * Events are Tasks that start and end at a specific date/time.
 *
 * @author junyan-k
 */
public class Event extends Task {

    /** Starting date/time. Stored as {@link LocalDateTime}. */
    private LocalDateTime startDateTime;

    /** Ending date/time. Stored as {@link LocalDateTime}. */
    private LocalDateTime endDateTime;

    /**
     * Generates an Event with provided description, start and end.
     * Event is incomplete by default.
     * @see Task#Task(String)
     */
    public Event(String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(desc);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Generates an Event with provided description, start and end, and initialized completion status.
     * @see Task#Task(boolean, String)
     */
    public Event(boolean isCompleted, String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(isCompleted, desc);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * {@inheritDoc}
     *
     * @return "E"
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    /**
     * {@inheritDoc}
     *
     * @return List containing ({@link #startDateTime}, {@link #endDateTime})
     */
    @Override
    public List<LocalDateTime> getTimeArguments() {
        return List.of(startDateTime, endDateTime);
    }

    /**
     * Returns Event as String.
     * Format: "[E][<'X' if completed, ' ' if not>] {desc} (from: {@link #startDateTime} to: {@link #endDateTime})"
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)",
                getSymbol(), super.toString(), DateTimeParse.parseOutput(startDateTime),
                DateTimeParse.parseOutput(endDateTime));
    }

    /**
     * Returns true if both Events are equal.
     * Two Events are equal if they have the same description, to and from LDTs.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event e) {
            return e.getDesc().equals(this.getDesc())
                    && e.startDateTime.equals(this.startDateTime)
                    && e.endDateTime.equals(this.endDateTime);
        }
        return false;
    }

}
