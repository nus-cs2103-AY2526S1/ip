package piper.task;

import piper.PiperException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task that must be completed by a specific date.
 * The constructor accepts the /by field as entered by the user.
 * If given the ISO date format yyyy-MM-dd, the date is displayed as MMM d yyyy.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAYED_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected final String taskType = "D";
    protected String by;
    private LocalDate byDate;

    /**
     * Creates a Deadline.
     *
     * @param description task description.
     * @param by by deadline text.
     */
    public Deadline(String description, String by) throws PiperException {
        super(description);

        if (description == null || description.trim().isEmpty()) {
            throw new PiperException("Deadline needs a description.");
        }
        if (by == null) {
            throw new PiperException("/by cannot be empty.");
        }

        this.by = by;

        try {
            this.byDate = LocalDate.parse(this.by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    /**
     * Formats /by string to date if in yyyy-MM-dd format.
     *
     * @return formatted by date.
     */
    private String formatByDate() {
        return (byDate != null) ? byDate.format(DISPLAYED_DATE) : this.by;
    }

    /**
     * Updates the deadline date (/by field).
     * Accepts any string.
     * If it matches ISO yyyy-MM-dd, then a LocalDate is stored.
     *
     * @param updatedBy new deadline string.
     */
    public void updateByDate(String updatedBy) {
        this.by = updatedBy;

        try {
            this.byDate = LocalDate.parse(this.by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString() + " (by: " + this.formatByDate() + ")";
    }

    @Override
    public String toSerializedLine() {
        String doneField = getStatusIcon().equals("X") ? "1" : "0";
        return "D | " + doneField + " | " + this.getDescription() + " | " + this.by;
    }

}
