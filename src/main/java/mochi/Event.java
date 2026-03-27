package mochi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Implements an Event task, which is a type of Task that has a description, start time, and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an uncompleted Event with a description, start time, and end time.
     * The start and end times can be specified in either "yyyy-MM-dd HHmm" format or "yyyy-MM-dd" format.
     * If the time is not specified, it defaults to the start of the day.
     *
     * @param desc the description of the event
     * @param from the start time of the event, in "yyyy-MM-dd HHmm" or "yyyy-MM-dd" format
     * @param to the end time of the event, in "yyyy-MM-dd HHmm" or "yyyy-MM-dd" format
     * @throws EventException if the date/time format is invalid or if the end time is before the start time
     */
    public Event(String desc, String from, String to) throws EventException {
        super(desc);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        // From datetime handler
        try {
            this.from = LocalDateTime.parse(from, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                // Handle no time specified
                this.from = LocalDate.parse(from).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new EventException();
            }
        }
        // To datetime handler
        try {
            this.to = LocalDateTime.parse(to, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                // Handle no time specified
                LocalDateTime dt = LocalDate.parse(to).atStartOfDay();
                if (this.from.isAfter(dt)) {
                    throw new EventException("The end time must be after the start time.");
                }
                this.to = dt;
            } catch (DateTimeParseException e2) {
                throw new EventException();
            }
        }
    }

    /**
     * Creates an Event with a description, start time, and end time with the specified completion status.
     * The start and end times can be specified in either "yyyy-MM-dd HHmm" format or "yyyy-MM-dd" format.
     * If the time is not specified, it defaults to the start of the day.
     *
     * @param desc the description of the event
     * @param from the start time of the event, in "yyyy-MM-dd HHmm" or "yyyy-MM-dd" format
     * @param to the end time of the event, in "yyyy-MM-dd HHmm" or "yyyy-MM-dd" format
     * @param status the completion status of the event
     * @throws EventException if the date/time format is invalid or if the end time is before the start time
     */
    public Event(String desc, String from, String to, boolean status) throws EventException {
        this(desc, from, to);
        if (status) {
            super.mark();
        }
    }

    @Override
    public String getSaveString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("E | %d | %s | %s | %s", this.completed ? 1 : 0, super.getDescriptionSaveString(),
                this.from.format(dateFormat), this.to.format(dateFormat));
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm");
        return String.format("[E]%s (from %s to %s)", super.toString(),
                this.from.format(dateFormat), this.to.format(dateFormat));
    }
}
