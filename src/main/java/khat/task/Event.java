package khat.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import khat.exception.DeadlineTaskException;
import khat.exception.EventTaskException;

/** Represents an Event task */
public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDateTime fromDateTime;
    protected LocalDate toDate;
    protected LocalDateTime toDateTime;
    private boolean hasTime;

    /**
     * Constructs an Event task with the given description, completion status,
     * start date/time and end date/time.
     *
     * @param description Description of task.
     * @param isDone True if task is completed, false otherwise.
     * @param from Start date/time of the event.
     * @param to End date/time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) throws EventTaskException {
        super(description, isDone);
        try {
            this.fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.hasTime = true;
            this.from = from;
            this.to = to;
        } catch (DateTimeParseException e1) {
            try {
                this.fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.hasTime = false;
                this.from = from;
                this.to = to;
            } catch (DateTimeParseException e2) {
                throw new EventTaskException("Invalid date/time format! Use dd-MM-yyyy or dd-MM-yyyy HHmm!");
            }
        }
    }
    /**
     * Returns true if the event includes time, false if only dates.
     *
     * @return True if event has time, false otherwise.
     */
    public boolean hasTime() {
        return this.hasTime;
    }

    /**
     * Returns the formatted from date/dateTime string for display.
     *
     * @return Formatted from date/dateTime string.
     */
    private String fromDateToString() {
        if (hasTime) {
            return this.fromDateTime.format(DateTimeFormatter.ofPattern("dd MMM yy h:mm a"));
        } else {
            return this.fromDate.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        }
    }

    /**
     * Returns the formatted to date/dateTime string for display.
     *
     * @return Formatted to date/dateTime string.
     */
    private String toDateToString() {
        if (hasTime) {
            return this.toDateTime.format(DateTimeFormatter.ofPattern("dd MMM yy h:mm a"));
        } else {
            return this.toDate.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        }
    }

    @Override
    public String toSaveString() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.getDescription() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateToString() + " to: " + this.toDateToString() + ")";
    }
}
