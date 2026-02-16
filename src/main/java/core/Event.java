package core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import util.TronaldDumpException;

/**
 * Represents an event task with a description, start date/time, and end date/time.
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * @param description Description of the event task with start and end times
     * @param isDone Boolean indicating if the task is done.
     */
    public Event(String description, boolean isDone) {
        super(description.split(" /from ")[0], isDone);
        String[] fromSplit = description.split(" /from ");
        String errorMessageForInvalidEventDescription = "Event description must include both start date/time after '/from' and end date/time after '/to'.";
        if (fromSplit.length < 2) {
            throw new TronaldDumpException(
                    errorMessageForInvalidEventDescription);
        }
        String[] toSplit = fromSplit[1].split(" /to ");
        if (toSplit.length < 2) {
            throw new TronaldDumpException(
                    errorMessageForInvalidEventDescription);
        }
        String startStr = toSplit[0].trim();
        String endStr = toSplit[1].trim();
        this.startDateTime = parseDateTime(startStr);
        this.endDateTime = parseDateTime(endStr);
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" or "yyyy-MM-dd HHmm" to a LocalDateTime object.
     * If only the date is provided, the time is set to midnight.
     * @param dateStr Date string to parse
     * @return LocalDateTime object representing the parsed date/time
     * @throws TronaldDumpException if the date string is not in a valid format
     */
    private LocalDateTime parseDateTime(String dateStr) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDateTime.parse(dateStr, dateTimeFormat);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(dateStr, dateFormat).atStartOfDay();
            } catch (DateTimeParseException e2) {
                String errorMessageForInvalidDateFormat = "Invalid date format! Please use yyyy-MM-dd or yyyy-MM-dd HHmm.";
                throw new TronaldDumpException(errorMessageForInvalidDateFormat);
            }
        }
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? startDateTime.toLocalDate().format(dateFormat)
                : startDateTime.format(dateTimeFormat);
        String end = endDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? endDateTime.toLocalDate().format(dateFormat)
                : endDateTime.format(dateTimeFormat);
        String eventDescriptionForStorage = "E | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " /from " + start + " /to " + end;
        return eventDescriptionForStorage;
    }

    /**
     * Compares this event with another task for ordering.
     * Events are sorted chronologically by their start date/time.
     * @param other The task to compare with
     * @return A negative integer, zero, or a positive integer as this event is less than,
     *         equal to, or greater than the other
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.startDateTime.compareTo(otherEvent.startDateTime);
        } else if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return this.startDateTime.compareTo(otherDeadline.getDeadline());
        } else {
            // For Todo tasks, events come first (earlier in sort order)
            return -1;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        DateTimeFormatter outputFormatDateOnly = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String start = startDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? startDateTime.toLocalDate().format(outputFormatDateOnly)
                : startDateTime.format(outputFormatWithTime);
        String end = endDateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)
                ? endDateTime.toLocalDate().format(outputFormatDateOnly)
                : endDateTime.format(outputFormatWithTime);
        String eventDescriptionForDisplay = String.format("[E] %s (from: %s to: %s)", super.toString(), start, end);
        return eventDescriptionForDisplay;
    }
}
