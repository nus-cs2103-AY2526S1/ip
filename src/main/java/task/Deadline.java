package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that has a name and a due date
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Deadline Constructor
     *
     * @param name
     *            The name for this task
     *
     * @param by
     *            A {@link LocalDate} object representing the due date
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getTaskTitle() {
        return String.format("%s (by: %s)", getName(), by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    protected char typeChar() {
        return 'D';
    }
}
