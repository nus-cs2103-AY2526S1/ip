package chlo.task;

import java.time.LocalDateTime;

import chlo.exception.ChloException;
import chlo.ui.Parser;

/**
 * Creates a deadline task
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for a deadline task
     * @param description
     * @param by
     * @throws ChloException
     */
    public Deadline(String description, String by) throws ChloException {
        super(description);

        this.by = Parser.parseDate(by);
        this.raw = String.format("deadline %s /by %s", description, by);

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.getFormattedDate(this.by) + ")";
    }

    /**
     * Get by time for sort command to handle
     * @return
     */
    public LocalDateTime getBy() {
        return by;
    }
}
