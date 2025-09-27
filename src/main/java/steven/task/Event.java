package steven.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import steven.exception.InvalidDateAndTimeFormatException;

/**
 * Represents a task that start at a specific date/time and ends at a specific date/time.
 *
 * @author w3njii
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String startTimeString;
    private final String endTimeString;

    /**
     * Creates a new event task with the given description, start time, and end time.
     * The start and end time must be provided in the format: dd-mm-yyyy tttt
     * where dd is the day, mm is the month, yyyy is the year, and
     * tttt is the time in 24-hour format.
     *
     * @param description the description of the task
     * @param startTimeString the start time string in the specified format
     * @param endTimeString the end time string in the specified format
     */
    public Event(String description, String startTimeString, String endTimeString)
            throws InvalidDateAndTimeFormatException {
        super(description);
        assert startTimeString != null : "Start time String should not be null";
        assert endTimeString != null : "End time String should not be null";
        try {
            this.startTimeString = startTimeString;
            this.endTimeString = endTimeString;
            int startDay = Integer.parseInt(startTimeString.substring(0, 2));
            int startMonth = Integer.parseInt(startTimeString.substring(3, 5));
            int startYear = Integer.parseInt(startTimeString.substring(6, 10));
            int startHour = Integer.parseInt(startTimeString.substring(11, 13));
            int startMinute = Integer.parseInt(startTimeString.substring(13, 15));
            this.startTime = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute);
            assert this.startTime != null : "Start time should not be null";
            int endDay = Integer.parseInt(endTimeString.substring(0, 2));
            int endMonth = Integer.parseInt(endTimeString.substring(3, 5));
            int endYear = Integer.parseInt(endTimeString.substring(6, 10));
            int endHour = Integer.parseInt(endTimeString.substring(11, 13));
            int endMinute = Integer.parseInt(endTimeString.substring(13, 15));
            this.endTime = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute);
            assert this.endTime != null : "End time should not be null";
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new InvalidDateAndTimeFormatException();
        }
    }

    @Override
    public String convertToSaveFormat() {
        return "[E]" + super.convertToSaveFormat() + " (from: " + this.startTimeString
                + " to: " + this.endTimeString + ")";
    }

    /**
     * {@inheritDoc}
     *
     * Additionally, adds a "[E]" at the front to represent "event" task, and the start and end time at the end.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
