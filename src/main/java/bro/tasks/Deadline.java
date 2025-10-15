package bro.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a description and a due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Creates a new Deadline task with the given description and due date and time.
     *
     * @param description The description of the task.
     * @param by          The due date and time of the task in the format "d/M/yyyy
     *                    HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (Exception e) {
            System.out.println("\tError parsing date/time. Please use the format: d/M/yyyy HHmm");
        }
    }

    /**
     * Creates a new Deadline task with the given description, status, and due date
     * and time.
     *
     * @param description The description of the task.
     * @param isDone      The status of the task.
     * @param by          The due date and time of the task in the format "d/M/yyyy
     *                    HHmm".
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (Exception e) {
            System.out.println("\tError parsing date/time. Please use the format: d/M/yyyy HHmm");
        }
    }

    /**
     * Checks if the deadline falls on the given date.
     *
     * @param date The date to check in the format "d/M/yyyy".
     * @return true if the deadline falls on the given date, false otherwise.
     */
    public boolean isOnDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate checkDate = LocalDate.parse(date, dateFormatter);
            return by.toLocalDate().isEqual(checkDate);
        } catch (Exception e) {
            System.out.println("\tError parsing date. Please use the format: d/M/yyyy");
            return false;
        }
    }

    /**
     * Converts the Deadline task to a data string for storage.
     *
     * @return A string representation of the Deadline task for storage.
     */
    public String toDataString() {
        return String.format("D|%d|%s|%s", (isDone ? 1 : 0),
                description,
                by.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        try {
            return String.format("[D] [%s] %s (by: %s)",
                    getStatusIcon(),
                    description,
                    by.format(printFormat));
        } catch (Exception e) {
            System.out.println("\tError parsing date. Please use the format: d/M/yyyy");
            return "";
        }
    }
}
