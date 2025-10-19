package reboot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a start time and an end time.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructs a new Event task with the given description,
     * isDone, start and end dates without time.
     * @param description Description of the event task.
     * @param isDone Status of the task.
     * @param start Start date of the task.
     * @param end End date of the task.
     */
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        startDate = start;
        endDate = end;
    }

    /**
     * Constructs a new Event task with the given description,
     * isDone, start and end times.
     * @param description Description of the event task.
     * @param isDone Status of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        startDateTime = start;
        endDateTime = end;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDateTime(LocalDateTime dateTime) {
        this.startDateTime = dateTime;
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDateTime(LocalDateTime dateTime) {
        this.endDateTime = dateTime;
    }

    public void setEndDate(LocalDate date) {
        this.endDate = date;
    }

    /**
     * Converts the task to a string that can be stored in a file.
     */
    @Override
    public String toFileString() {
        if (startDate != null) {
            DateTimeFormatter start = DateTimeFormatter.ofPattern("dd MM yyyy");
            DateTimeFormatter end = DateTimeFormatter.ofPattern("dd MM yyyy");
            return "E | " + (isDone ? "1" : "0") + " | " + description
                    + " | " + startDate.format(start) + " | " + endDate.format(end);
        } else {
            DateTimeFormatter start = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
            DateTimeFormatter end = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
            return "E | " + (isDone ? "1" : "0") + " | " + description
                    + " | " + startDateTime.format(start) + " | " + endDateTime.format(end);
        }
    }

    /**
     * Converts the task to a string that can be displayed.
     */
    @Override
    public String toString() {
        if (startDate != null) {
            DateTimeFormatter start = DateTimeFormatter.ofPattern("dd MM yyyy");
            DateTimeFormatter end = DateTimeFormatter.ofPattern("dd MM yyyy");
            return "[E]" + super.toString() + " (from: "
                    + startDate.format(start) + " to: " + endDate.format(end) + ")";
        } else {
            DateTimeFormatter start = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
            DateTimeFormatter end = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
            return "[E]" + super.toString() + " (from: "
                    + startDateTime.format(start) + " to: " + endDateTime.format(end) + ")";
        }
    }
}
