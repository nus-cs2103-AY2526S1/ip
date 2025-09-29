package david.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import david.exception.InvalidDateTimeException;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Initialises a Deadline task.
     *
     * @param text Task description.
     * @param by Task end DateTime.
     */
    public Deadline(String text, String by) throws InvalidDateTimeException {
        super(text);

        assert by != null : "By should never be null";
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.by = LocalDateTime.parse(by, inputFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Eh your datetime format is wrong leh. Use this format lah: yyyy-MM-dd HHmm");
        }

    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + this.by.format(outputFormat) + ")";
    }
}
