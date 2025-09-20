package marquess.task;

import marquess.exception.InsufficientParametersException;
import marquess.exception.InvalidParameterException;
import marquess.exception.MarquessException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for deadline task with default isDone status.
     *
     * @param description Description of task.
     * @param by Datetime that the task must be completed by.
     * @throws MarquessException If insufficient or invalid parameters are passed in.
     */
    public Deadline(String description, String by) throws MarquessException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw by.isEmpty()
                    ? new InsufficientParametersException("deadline required")
                    : new InvalidParameterException(String.format("%s; datetime required", by));
        }
    }

    /**
     * Constructor for deadline task.
     *
     * @param isDone Initial isDone status.
     * @param description Description of task.
     * @param by Datetime that the task must be completed by.
     * @throws MarquessException If insufficient or invalid parameters are passed in.
     */
    public Deadline(boolean isDone, String description, String by) throws MarquessException {
        super(isDone, description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw by.isEmpty()
                    ? new InsufficientParametersException("deadline required")
                    : new InvalidParameterException(String.format("%s; datetime required", by));
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String exportTask() {
        return String.format("D,%s,%s", this.by, super.exportTask());
    }
}
