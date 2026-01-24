package chathyt.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task in the Duke application.
 * Each deadline has a description, a 'by' indicating
 * its end time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Deadline(String description, String byString) {
        super(description);
        this.by = LocalDateTime.parse(byString, inputFormat);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}
