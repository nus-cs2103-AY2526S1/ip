package piper.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected final String taskType = "D";
    protected String by;
    private LocalDate byDate;
    private static final DateTimeFormatter DISPLAYED_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(this.by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    private String formatByDate() {
        return (byDate != null) ? byDate.format(DISPLAYED_DATE) : this.by;
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