package marquess.task;

import marquess.exception.InsufficientParametersException;
import marquess.exception.InvalidParameterException;
import marquess.exception.MarquessException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An event with a beginning and end date.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for event with default isDone status.
     *
     * @param description Description of task.
     * @param start Beginning datetime of event.
     * @param end Ending datetime of event.
     * @throws MarquessException If insufficient or invalid parameters are passed in.
     */
    public Event(String description, String start, String end) throws MarquessException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw start.isEmpty() || end.isEmpty()
                    ? new InsufficientParametersException("start and end required")
                    : new InvalidParameterException(String.format("%s or %s; datetime required", start, end));
        }
    }

    /**
     * Constructor for event with default isDone status.
     *
     * @param isDone Initial isDone status.
     * @param description Description of task.
     * @param start Beginning datetime of event.
     * @param end Ending datetime of event.
     * @throws MarquessException If insufficient or invalid parameters are passed in.
     */
    public Event(boolean isDone, String description, String start, String end) throws MarquessException {
        super(isDone, description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw start.isEmpty() || end.isEmpty()
                    ? new InsufficientParametersException("start and end required")
                    : new InvalidParameterException(String.format("%s or %s; datetime required", start, end));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)",
                super.toString(),
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String exportTask() {
        return String.format("E,%s,%s,%s", this.start, this.end, super.exportTask());
    }
}
