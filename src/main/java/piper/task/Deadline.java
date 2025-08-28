package piper.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected final String taskType = "D";
    protected String by;
    private LocalDate date;
    private static final DateTimeFormatter DISPLAYED_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.date = LocalDate.parse(this.by);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    private String formatDate() {
        if (date != null) {
            return String.valueOf(DISPLAYED_DATE);
        }
        return this.by;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + formatDate() + ")";
    }

    @Override
    public String toSerializedLine() {
        String doneField = getStatusIcon().equals("X") ? "1" : "0";
        return "D | " + doneField + " | " + this.getDescription() + " | " + this.by;
    }


}