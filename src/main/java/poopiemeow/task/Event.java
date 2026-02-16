package poopiemeow.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import poopiemeow.exception.EmptyDescriptionException;

/**
 * Represents an event task in the PoopieMeow application.
 * An event is a task that occurs during a specific time period, with a start and end time.
 * It extends the base Task class and adds event scheduling functionality.
 *
 * @author tch1001
 * @version 1.0
 */
public class Event extends Task {
    /** The start date and time of the event */
    private LocalDateTime startTime;
    /** The end date and time of the event */
    private LocalDateTime endTime;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     * The description must not be empty or contain only whitespace.
     * Both start and end times must be in the format "yyyy-MM-dd HHmm".
     *
     * @param description a description of what the event involves
     * @param startTimeStr the start time string in "yyyy-MM-dd HHmm" format
     * @param endTimeStr the end time string in "yyyy-MM-dd HHmm" format
     * @throws EmptyDescriptionException if the description is empty or contains only whitespace
     * @throws DateTimeParseException if either time string format is invalid
     */
    public Event(String description, String startTimeStr, String endTimeStr) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of an event cannot be empty.");
        }
        this.startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return the LocalDateTime representing when the event begins
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return the LocalDateTime representing when the event ends
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Converts the event task to its file storage format.
     * The format follows the pattern: E|status|description|startTime|endTime
     *
     * @return a string representation of the event suitable for file storage
     */
    @Override
    public String toFileString() {
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" +
               startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "|" +
               endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the event task.
     * The format includes the task type indicator [E], completion status,
     * description, and formatted start and end times.
     *
     * @return a string showing the event's status, description, and time period
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " +
               startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + " to: " +
               endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
