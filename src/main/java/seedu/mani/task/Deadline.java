package seedu.mani.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    private static final String TYPE = "D";
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy ha", Locale.ENGLISH);

    private final LocalDateTime deadline;

    /**
     * Constructs a new Deadline task with the specified description and date string.
     *
     * @param task Description of the deadline; must not be null or empty
     * @param date Date-time string in format d/MM/yyyy HHmm
     */
    public Deadline(String task, String date) {
        super(task);
        assert date != null : "Deadline date is null";
        this.deadline = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    /**
     * Constructs a Deadline task with specified description, date string, and completion status.
     *
     * @param task Description of the deadline; must not be null or empty
     * @param date Date-time string in format d/MM/yyyy HHmm
     * @param mark True if task is completed, false otherwise
     */
    public Deadline(String task, String date, boolean mark, int count){
        super(task, mark, count);
        assert date != null : "Deadline date is null";
        this.deadline = LocalDateTime.parse(date, INPUT_FORMATTER);
    }

    @Override
    public String getDetails() {
        return TYPE + " | " + super.getDetails() + " | " + this.deadline.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() +
                " (by: " + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }
}