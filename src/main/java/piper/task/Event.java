package piper.task;

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
     * @param from from start text.
     * @param to to end text.
     */
    public Event(String description, String from, String to) {
        super(description);
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
    }

    private String formatFromDate() {
        return (fromDate != null) ? fromDate.format(DISPLAYED_DATE) : this.from;
    }

    private String formatToDate() {
        return (toDate != null) ? toDate.format(DISPLAYED_DATE) : this.to;
    }

    public void updateRange(String updatedFrom, String updatedTo) {
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
