package peanutbutter.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import peanutbutter.TaskType;

/**
 * Represents an event task that occurs over a period of time.
 * <p>
 * The event can be specified with a date or date-time for both start (from) and end (to).
 * Provides methods to get the start time, display the event, and store it in a file-friendly format.
 */
public class Event extends Task {
    private static final DateTimeFormatter inputDT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputDT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter inputD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputD = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDateTime fromDT;
    private LocalDateTime toDT;
    private LocalDate fromD;
    private LocalDate toD;

    /**
     * Creates a new Event task with description and start and end times.
     *
     * @param description the description of the event
     * @param from the start time (yyyy-MM-dd or yyyy-MM-dd HHmm)
     * @param to the end time (yyyy-MM-dd or yyyy-MM-dd HHmm)
     * @throws IllegalArgumentException if the date/time format is invalid
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);

        try {
            fromDT = LocalDateTime.parse(from, inputDT);
        } catch (DateTimeParseException e) {
            try {
                fromDT = null;
                fromD = LocalDate.parse(from, inputD);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date/time format. Use yyyy-MM-dd or yyyy-MM-dd HHmm");
            }
        }

        try {
            toDT = LocalDateTime.parse(to, inputDT);
        } catch (DateTimeParseException e) {
            toDT = null;
            toD = LocalDate.parse(to, inputD);
        }
    }

    /**
     * Returns the start time of the event as a LocalDateTime.
     * If only a date was provided, returns the start of that day.
     *
     * @return the start time of the event
     */
    public LocalDateTime getStartTime() {
        if (fromDT != null) {
            return fromDT;
        } else if (fromD != null) {
            return fromD.atStartOfDay();
        } else {
            return null;
        }
    }

    /**
     * Returns a string representation of the event for display.
     *
     * @return a formatted string including description, start, and end times
     */
    @Override
    public String toString() {
        String fromStr = fromDT == null ? fromD.format(outputD) : fromDT.format(outputDT);
        String toStr = toDT == null ? toD.format(outputD) : toDT.format(outputDT);

        return "[E] " + super.toString() + " (from: " + fromStr + " to: " + toStr + ")";
    }

    /**
     * Returns a string representation of the event suitable for storage in a file.
     *
     * @return a line-formatted string representing the event
     */
    @Override
    public String makePretty() {
        String fromStr = fromDT == null ? fromD.format(inputD) : fromDT.format(inputDT);
        String toStr = toDT == null ? toD.format(inputD) : toDT.format(inputDT);

        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromStr + " | " + toStr;
    }
}
