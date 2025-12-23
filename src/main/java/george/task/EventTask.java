package george.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import george.exceptions.GeorgeException;
import george.utils.DateTimeParser;

/**
 * Represents an event task with specific start and end times.
 * Extends the base Task class with event-specific functionality.
 */

public class EventTask extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an EventTask with description, start time, and end time strings.
     *
     * @param description The description of the event task
     * @param startTime The start time string to be parsed
     * @param endTime The end time string to be parsed
     * @throws GeorgeException If the description is invalid or times cannot be parsed
     */
    public EventTask(String description, String startTime, String endTime) throws GeorgeException {
        this(description, startTime, endTime, false);
    }

    /**
     * Constructs an EventTask with description, start time, and end time as LocalDateTime objects.
     * This constructor is used when datetime objects are already parsed and available.
     *
     * @param description The description of the event task
     * @param startTime The start time as a LocalDateTime object
     * @param endTime The end time as a LocalDateTime object
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime, boolean isDone)
            throws GeorgeException {
        super(description);
        assert startTime != null : "Start time cannot be null";
        assert endTime != null : "End time cannot be null";
        assert !startTime.isAfter(endTime) : "Start time must be before or equal to end time";
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
    }

    /**
     * Constructs an EventTask with description, start time, end time, and completion status.
     *
     * @param description The description of the event task
     * @param startTime The start time string to be parsed
     * @param endTime The end time string to be parsed
     * @param isDone The completion status of the task
     * @throws GeorgeException If the description is invalid or times cannot be parsed
     */
    public EventTask(String description, String startTime, String endTime, boolean isDone) throws GeorgeException {
        super(description);
        assert startTime != null && !startTime.trim().isEmpty() : "Start time string cannot be null or empty";
        assert endTime != null && !endTime.trim().isEmpty() : "End time string cannot be null or empty";
        this.startTime = DateTimeParser.parseDateTime(startTime);
        this.endTime = DateTimeParser.parseDateTime(endTime);
        this.isDone = isDone;
        assert this.startTime != null : "Parsed start time cannot be null";
        assert this.endTime != null : "Parsed end time cannot be null";
        assert !this.startTime.isAfter(this.endTime) : "Start time must be before or equal to end time";
    }

    /**
     * Returns the type identifier for event tasks.
     *
     * @return The string "[E]" representing an event task
     */
    @Override
    public String getType() {
        return "[E]";
    }

    /**
     * Returns the formatted display text for the event task.
     *
     * @return A formatted string containing task type, status, description, and time range
     */
    @Override
    public String getDisplayText() {
        assert startTime != null : "Start time must be initialized before calling getDisplayText";
        assert endTime != null : "End time must be initialized before calling getDisplayText";
        return this.getType() + this.getStatus() + " " + this.getDescription()
                + " (from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
    }

    /**
     * Returns the formatted start time string.
     *
     * @return The start time formatted as "MMM dd yyyy HH:mm"
     */
    public String getStartTime() {
        assert startTime != null : "Start time cannot be null when formatting";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return startTime.format(formatter);
    }

    /**
     * Returns the formatted end time string.
     *
     * @return The end time formatted as "MMM dd yyyy HH:mm"
     */
    public String getEndTime() {
        assert endTime != null : "End time cannot be null when formatting";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return endTime.format(formatter);
    }

    /**
     * Returns a string representation for storage purposes.
     *
     * @return A pipe-separated string containing task type, status, description, and times
     */
    @Override
    public String toString() {
        assert startTime != null : "Start time must be initialized for toString";
        assert endTime != null : "End time must be initialized for toString";

        return getType().charAt(1) + " | " + (isDone() ? 1 : 0) + " | " + getDescription() + " | "
                + getStartTime() + " | " + getEndTime();
    }
}
