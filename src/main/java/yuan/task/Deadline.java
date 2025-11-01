package yuan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 *
 * <p>AI-Assisted: JavaDoc suggested by ChatGPT and refined manually.</p>
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate deadline;

    /**
     * Creates a Deadline task with the given description, date, and status.
     *
     * @param description
     * @param date
     * @param isDone
     */
    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.deadline = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMATTER) + ")";
    }

    /**
     * Converts the deadline task into a storage-friendly format.
     *
     * @return storage string representation
     */
    @Override
    public String toStorageFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + deadline;
    }
}
