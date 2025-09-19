package piper.task;

import piper.PiperException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task that spans a start and end date.
 * The constructor accepts the /from and /to fields as entered by the user.
 * If given the ISO date format yyyy-MM-dd, the date is displayed as MMM d yyyy.
 */
public class Event extends Task {
    private static final DateTimeFormatter DISPLAYED_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected final String taskType = "E";
    protected String from;
    protected String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Creates an Event.
     *
     * @param description task description.
     * @param from event start string.
     * @param to event end string.
     */
    public Event(String description, String from, String to) throws PiperException {
        super(description);

        if (description == null || description.trim().isEmpty()) {
            throw new PiperException("Event needs a description.");
        }
        if (from == null || to == null) {
            throw new PiperException("Event needs both /from and /to.");
        }

        this.from = from;
        this.to = to;

        try {
            this.fromDate = LocalDate.parse(this.from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
        try {
            this.toDate = LocalDate.parse(this.to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }

        if (this.fromDate != null && this.toDate != null) {
            if (!this.toDate.isAfter(this.fromDate)) {
                throw new PiperException("Not so fast time traveller! /to date must be after /from date.");
            }
        }
    }

    /**
     * Formats /from string to date if in yyyy-MM-dd format.
     *
     * @return formatted from date.
     */
    private String formatFromDate() {
        return (fromDate != null) ? fromDate.format(DISPLAYED_DATE) : this.from;
    }

    /**
     * Formats /to string to date if in yyyy-MM-dd format.
     *
     * @return formatted to date.
     */
    private String formatToDate() {
        return (toDate != null) ? toDate.format(DISPLAYED_DATE) : this.to;
    }

    /**
     * Updates the event date range (/from and /to fields).
     * Accepts any strings.
     * If they match ISO yyyy-MM-dd, then LocalDates are stored.
     *
     * @param updatedFrom new event start string.
     * @param updatedTo new event end string.
     */
    public void updateRange(String updatedFrom, String updatedTo) throws PiperException {
        this.from = updatedFrom;
        this.to = updatedTo;
        try {
            this.fromDate = LocalDate.parse(this.from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
        try {
            this.toDate = LocalDate.parse(this.to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }

        if (this.fromDate != null && this.toDate != null) {
            if (!this.toDate.isAfter(this.fromDate)) {
                // you may throw, or just reset both to null if you prefer to keep invalid string values
                throw new PiperException("Not so fast time traveller! /to date must be after /from date.");
            }
        }
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString()
                + " (from: " + this.formatFromDate() + " to: " + this.formatToDate() + ")";
    }

    @Override
    public String toSerializedLine() {
        String doneField = getStatusIcon().equals("X") ? "1" : "0";
        return "E | " + doneField + " | " + this.getDescription() + " | " + this.from + " | " + this.to;
    }

}
