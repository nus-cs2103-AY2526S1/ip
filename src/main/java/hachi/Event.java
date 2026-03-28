package hachi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task that has a start and end time. This class is a subclass of Task
 * and includes two LocalDateTime fields to represent the start and end times of the event.
 * The task will be marked as done or undone, and it will include a description, a start time,
 * and an end time.
 */

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
     *
     * @param description the description of the task
     * @param from        the start date and time of the event
     * @param to          the end date and time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event object with the specified description, completion status,
     * start time, and end time.
     *
     * @param description the description of the task
     * @param status      the completion status of the task
     * @param from        the start date and time of the event
     * @param to          the end date and time of the event
     */
    public Event(String description, boolean status, LocalDateTime from, LocalDateTime to) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task. The string includes the task's
     * description, completion status, start time, and end time.
     *
     * @return a string representing the event task
     */
    @Override
    public String toString() {
        String formattedFrom = new DateFormatter().formatTime(from);
        String formattedTo = new DateFormatter().formatTime(to);
        return "[E]" + super.toString() + String.format(" | From: %s | To: %s", formattedFrom, formattedTo);
    }
}
