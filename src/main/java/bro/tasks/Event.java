package bro.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a description, a start date and time, and an end date and time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Creates a new Event task with the given description, start date and time, and end date and time.
     * @param description The description of the task.
     * @param from The start date and time of the event in the format "d/M/yyyy HHmm".
     * @param to The end date and time of the event in the format "d/M/yyyy HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
        } catch (Exception e) {
            System.out.println("\tError parsing date/time. Please use the format: d/M/yyyy HHmm");
        }
    }

    /**
     * Creates a new Event task with the given description, status, start date and time, and end date and time.
     * @param description The description of the task.
     * @param isDone The status of the task.
     * @param from The start date and time of the event in the format "d/M/yyyy HHmm".
     * @param to The end date and time of the event in the format "d/M/yyyy HHmm".
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
        } catch (Exception e) {
            System.out.println("\tError parsing date/time. Please use the format: d/M/yyyy HHmm");
        }
    }

    /**
     * Checks if the event occurs on the given date.
     * @param date The date to check in the format "d/M/yyyy".
     * @return true if the event occurs on the given date, false otherwise.
     */
    public boolean isOnDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate fromDate = from.toLocalDate();
            LocalDate toDate = to.toLocalDate();
            LocalDate checkDate = LocalDate.parse(date, dateFormatter);
            return (fromDate.isEqual(checkDate)
                || toDate.isEqual(checkDate)
                || (checkDate.isAfter(fromDate)
                    && checkDate.isBefore(toDate)));
        } catch (Exception e) {
            System.out.println("\tError parsing date. Please use the format: d/M/yyyy");
            return false;
        }
    }

    /**
     * Converts the Event task to a data string for storage.
     * @return A string representation of the Event task for storage.
     */
    public String toDataString() {
        return String.format("E|%d|%s|%s|%s", (isDone ? 1 : 0),
            description,
            from.format(formatter),
            to.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        try {
            return String.format("[E] [%s] %s (from: %s to: %s)",
                getStatusIcon(),
                description,
                from.format(printFormat),
                to.format(printFormat));
        } catch (Exception e) {
            System.out.println("\tError parsing date. Please use the format: d/M/yyyy");
            return "";
        }
    }
}
