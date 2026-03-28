package ember.task;

import ember.exception.InvalidInputException;
import ember.exception.MissingInformationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a event.
 * This task includes a name and a from and to date in the format yyyy-mm-dd.
 */
public class EventTask extends Task {
    private LocalDate from;
    private LocalDate to;
    private String fromFormatted;
    private String toFormatted;
    private String fromUnformatted;
    private String toUnformatted;
    /**
     * Constructs a {@code EventTask} with the specified task name and deadline.
     *
     * @param name           the name or description of the task
     * @param fromUnformatted  the from date in the format yyyy-mm-dd
     * @param toUnformatted  the to date in the format yyyy-mm-dd
     * @throws MissingInformationException if the task name is empty
     * @throws InvalidInputException if the deadline date format is invalid
     */
    public EventTask(String name, String fromUnformatted, String toUnformatted)
            throws MissingInformationException, InvalidInputException {
        super(name);
        try {
            this.from = LocalDate.parse(fromUnformatted);
            this.to = LocalDate.parse(toUnformatted);
            this.fromFormatted = from.format(DateTimeFormatter.ofPattern("d MMM, yyyy"));
            this.toFormatted = to.format(DateTimeFormatter.ofPattern("d MMM, yyyy"));
            this.fromUnformatted = fromUnformatted;
            this.toUnformatted = toUnformatted;

        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Input dates in format yyyy-mm-dd");

        }
    }

    @Override
    public LocalDate getDate() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    /**
     * Returns the detailed description of this task, including the formatted event time.
     *
     * @return the full description of the task with event info
     */
    @Override
    public String description() {
        return toString() + " (from: " + fromFormatted +
                ", to: " + toFormatted + ")";
    }

    @Override
    public String parseFormat() {
        return toString() + " (from: " + fromUnformatted +
                ", to: " + toUnformatted + ")";
    }
}
