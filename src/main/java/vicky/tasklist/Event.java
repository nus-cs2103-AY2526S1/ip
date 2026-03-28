package vicky.tasklist;

import vicky.exception.InvalidInputException;

import java.time.LocalDateTime;

/**
 * Represents an event with a start and end time, extending the Deadline class.
 *
 * @author Rachel Wong
 */
public class Event extends Deadline {
    protected LocalDateTime from;
    protected LocalDateTime by;

    /**
     * Constructor for Event class, initializes the event with a name, start and end times.
     * @param name The name of the event.
     * @param from The start time of the event.
     * @param by The end time of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime by) {
        super(name, by);
        this.by = by;
        this.from = from;
    }

    /**
     * Constructor for Event class, initializes the event with a name, start and end times, and completion status.
     * @param name The name of the event.
     * @param from The start time of the event.
     * @param by The end time of the event.
     * @param isDone The completion status of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime by, boolean isDone) {
        super(name, by, isDone);
        this.by = by;
        this.from = from;
    }

    /**
     * Returns the start time of the event.
     *
     * @return LocalDateTime representing the start time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return LocalDateTime representing the end time of the event.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns the string representation of the start date and time in the output format.
     *
     * @return string representing the start date and time.
     */
    public String getStartDateTime() {
        return this.from.format(OUTPUT_FORMAT);
    }

    /**
     * Returns the string representation of the end date and time in the output format.
     *
     * @return string representing the end date and time.
     */
    public String getEndDateTime() {
        return this.by.format(OUTPUT_FORMAT);
    }

    /**
     * Checks if the start and end dates are the same.
     */
    public boolean isSameDate() {
        return this.from.toLocalDate().equals(this.by.toLocalDate());
    }

    /**
     * Returns the end time in the specified time format.
     */
    public String getEndTime() {
        return this.by.toLocalTime().format(TIME_FORMAT);
    }

    /**
     * Changes the start time of the event to the new start time.
     *
     * @param from New start LocalDateTime of the event.
     * @throws InvalidInputException if from (event start time) is after by (event end time).
     */
    public void changeFrom(LocalDateTime from) throws InvalidInputException {
        if (from.isBefore(this.by)) {
            this.from = from;
        } else {
            throw new InvalidInputException("What kind of event starts after it ends?");
        }
    }

    /**
     * Changes the end time by to a new end time.
     *
     * @param by New end LocalDateTime of the event.
     * throws InvalidInputException if by (event end time) is before from (event start time).
     */
    @Override
    public void changeBy(LocalDateTime by) throws InvalidInputException {
        if (this.from.isBefore(by)) {
            this.by = by;
        } else {
            throw new InvalidInputException("What kind of event starts after it ends?");
        }
    }

    /**
     * Changes both the start time and end time of the event to the new start time and end time.
     *
     * @param from New start LocalDateTime of the event.
     * @param by New end LocalDateTime of the event.
     * @throws InvalidInputException if from (event start time) is after by (event end time).
     */
    public void changeEventTime(LocalDateTime from, LocalDateTime by) throws InvalidInputException {
        if (from.isBefore(by)) {
            this.from = from;
            this.by = by;
        } else {
            throw new InvalidInputException("What kind of event starts after it ends?");
        }
    }

    /**
     * Returns a storage string of the event in the format:
     * "Event | {completion status} | {event name} | {start time} | {end time}"
     *
     * @return A storage string describing the event with its name, completion status, start time, and end time.
     */
    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Event | %d | %s | %s | %s", done, this.name, this.getStartDateTime(),
                this.getEndDateTime());
    }

    /**
     * Returns a string representation of the event in the format:
     * "[E] [{completion status}] {event name} (from {start time} to {end time})"
     *
     * @return A string describing the event with its name, completion status, start time, and end time.
     */
    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        String end = this.isSameDate() ? this.getEndTime() : this.getEndDateTime();
        return String.format("[E] [%c] %s (from %s to %s)", p, this.name, this.getStartDateTime(), end);
    }
}
