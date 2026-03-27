package pepe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pepe.exception.PepeExceptions;

/**
 * Represents a Deadline task.
 * <p>
 * A Deadline task has a name, a marked status, and a due date.
 * The due date cannot be before the current date.
 */
public class Deadlines extends Task {
    private String dateline;
    private LocalDate date;

    /**
     * Constructs a new Deadline task with the given name and due date.
     *
     * @param name     the name or description of the task
     * @param dateline the due date in the format yyyy-MM-dd
     * @throws PepeExceptions if the date is invalid or before today
     */
    public Deadlines(String name, String dateline) throws PepeExceptions {
        super(name);
        assert name != null && !name.isBlank() : "Task name should not be null or empty";
        assert dateline != null && !dateline.isBlank() : "Dateline should not be null or empty";
        try {
            this.date = LocalDate.parse(dateline);
            this.dateline = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (date.isBefore(LocalDate.now())) {
                throw new PepeExceptions("Invalid Input: Dateline cannot be before today!");
            }
        } catch (DateTimeParseException e) {
            throw new PepeExceptions("Invalid Input: Please check the format of your dates (yyyy-mm-dd)");
        }
    }

    /**
     * Returns a string representation of the Deadline task for display.
     * <p>
     * Format: [D][X] taskName (by: MMM d yyyy) if marked, [D][ ] taskName (by: MMM d yyyy) if unmarked.
     *
     * @return a human-readable string representing the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateline + ")";
    }

    /**
     * Returns a string representing the Deadline task in a file-friendly format.
     * <p>
     * Format: D | 1 | taskName | MMM d yyyy (if marked) or D | 0 | taskName | MMM d yyyy (if unmarked)
     *
     * @return the Deadline task formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        assert dateline != null && !dateline.isBlank() : "Dateline should be non-null and non-empty for file format";
        return "D" + " | " + super.checkMarked() + " | " + super.getName() + " | " + this.dateline;
    }
}
