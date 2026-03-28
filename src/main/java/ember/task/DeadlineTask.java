package ember.task;

import ember.exception.InvalidInputException;
import ember.exception.MissingInformationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * This task includes a name and a due date in the format yyyy-mm-dd.
 */
public class DeadlineTask extends Task {
    private LocalDate by;
    private String byFormatted;
    private String byUnformatted;

    /**
     * Constructs a {@code DeadlineTask} with the specified task name and deadline.
     *
     * @param name           the name or description of the task
     * @param byUnformatted  the deadline in the format yyyy-mm-dd
     * @throws MissingInformationException if the task name is empty
     * @throws InvalidInputException if the deadline date format is invalid
     */
    public DeadlineTask(String name, String byUnformatted) throws MissingInformationException, InvalidInputException {
        super(name);
        try {
            this.by = LocalDate.parse(byUnformatted);
            this.byFormatted = by.format(DateTimeFormatter.ofPattern("d MMM, yyyy"));
            this.byUnformatted = byUnformatted;
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Input dates in format yyyy-mm-dd");
        }
    }

    @Override
    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    /**
     * Returns the detailed description of this task, including the formatted deadline.
     *
     * @return the full description of the task with deadline info
     */
    @Override
    public String description() {
        return toString() + " (by: " + byFormatted + ")";
    }

    @Override
    public String parseFormat() {
        return toString() + " (by: " + byUnformatted + ")";
    }
}
