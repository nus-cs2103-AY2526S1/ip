package bob.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    LocalDate dueDate;
    private static final DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, LocalDate dueDate) throws IllegalArgumentException {
        this(description, false, dueDate);
    }

    public Deadline(String description, boolean complete, LocalDate dueDate) throws IllegalArgumentException {
        super(description, complete);
        if (dueDate == null) {
            throw new IllegalArgumentException("Deadline date cannot be null.");
        }
        this.dueDate = dueDate;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    public void setDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns String format of task for storage
     * @return taskString
     */
    @Override
    public String toFileString() {
        return String.format("%s /done %d /des %s /by %s",
                getTaskType(),
                isComplete() ? 1 : 0,
                getDescription(),
                dueDate
        );
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                dueDate.format(DATE_PRINT_FORMAT)
        );
    }
}
