package nailongbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task scheduled within a specific time range.
 * An Event stores a task description,
 * a start time, and an end time. Both times are parsed from raw string inputs into LocalDateTime objects
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    /**
     * Constructs an Event task with the specified description,
     * start time, and end time.
     *
     * @param description   the description of the event
     * @param rawStartTime  the start time as a string in the format @code d/M/yyyy HHmm
     * @param rawEndTime    the end time as a string in the format d/M/yyyy HHmm
     * @throws IllegalArgumentException if either time is in an invalid format
     */
    public Event(String description, String rawStartTime, String rawEndTime) {
        super(description);

        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startTime = LocalDateTime.parse(rawStartTime.trim(), inputFormatter);
            this.endTime = LocalDateTime.parse(rawEndTime.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
        }
    }

    public LocalDateTime getDate() {
        return startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String newFormatStartTime = startTime.format(outputFormatter);
        String newFormatEndTime = endTime.format(outputFormatter);
        return "[E]" + super.toString() + " (from: " + newFormatStartTime + " to: " + newFormatEndTime + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0")
                + " | " + description + " | "
                + startTime.format(fileFormatter)
                + " | " + endTime.format(fileFormatter);
    }
}

