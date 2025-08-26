package rafayel.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task which is a subtype of Task.
 * A Event task has a description, a start date and an end date.
 * All dates are stored as a LocalDateTime object that includes the date and time.
 */
public class Event extends Task {

    /* The date that the Event task starts */
    LocalDateTime startDate;
    /* The date that the Event task ends */
    LocalDateTime endDate;

    /**
     * Constructs a new Event with the given description, start date and end date.
     *
     * @param description the description of the Event task.
     * @param startDate the start date of the Event task.
     * @param endDate the end date of the Event task.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Handles the formatting of the date time object.
     * Converts the LocalDateTime object into a String.
     *
     * @param dateTime LocalDateTime object that stores a date and time.
     * @return formatted string in a standardised format.
     */
    private static String handleDateTimeFormatting(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + handleDateTimeFormatting(this.startDate) + " to: "
                + handleDateTimeFormatting(this.endDate) + ")";
    }

    @Override
    public String saveTaskName() {
        return "E" + super.saveTaskName() + " | " + handleDateTimeFormatting(this.startDate) + " - "
                + handleDateTimeFormatting(this.endDate);
    }
}
