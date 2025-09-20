package lyra.task;

import java.time.LocalDate;

import lyra.util.DateTimeUtil;

/**
 * Represents a task that occurs during a specific time period.
 * Extends the base Task class to provide event-specific functionality with start and end dates.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs a new Event task with the specified description and time period.
     *
     * @param description the description of the event task
     * @param from the start date of the event
     * @param to the end date of the event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the type code identifier for Event tasks.
     *
     * @return "E" representing Event task type
     */
    public static String typeCode() {
        return "E";
    }

    /**
     * Gets the start date of the event.
     *
     * @return the start date
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Gets the end date of the event.
     *
     * @return the end date
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Converts the Event task to a data string format for storage.
     * Format: E | status | description | from to to
     *
     * @return string representation suitable for data storage
     */
    public String toDataString() {
        String fromStr = from.format(DateTimeUtil.STORAGE_DATE);
        String toStr = to.format(DateTimeUtil.STORAGE_DATE);
        return String.join(" | ", typeCode(), this.isDone ? "1" : "0", this.description, fromStr + " to " + toStr);
    }

    /**
     * Returns a string representation of the Event task.
     * Format: [E][status] description (from: start_date to: end_date)
     *
     * @return string representation of the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtil.formatForDisplay(from) + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }
}


