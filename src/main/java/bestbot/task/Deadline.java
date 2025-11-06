package bestbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task with a deadline ("by" date).
 */
public class Deadline extends Task {
    private final LocalDate by;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a Deadline task.
     *
     * @param description Task description. Must not be null or empty.
     * @param by Deadline in yyyy-MM-dd format. Must not be null or empty.
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null && !by.isBlank() : "Deadline date should not be null or empty";
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the deadline date.
     *
     * @return LocalDate representing the 'by' date.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return Display format of the deadline task.
     */
    @Override
    public String toString() {
        assert by != null : "Deadline date must not be null";
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    /**
     * Returns the string format used to save the deadline task.
     *
     * @return Save format string.
     */
    @Override
    public String toSaveFormat() {
        assert by != null : "Deadline date must not be null when saving";
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
