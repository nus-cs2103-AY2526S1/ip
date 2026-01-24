package noob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import noob.exception.NoobException;

/**
 * A subclass of Task that represents a task with a start and end date
 */
public class EventTask extends Task {

    private LocalDate from;
    private LocalDate to;

    public EventTask(String text, String from, String to) throws NoobException {
        super(text);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new NoobException("Invalid date formats, please provide a date of the form yyyy-mm-dd");
        }
    }

    /**
     * Formats the start date into a string
     *
     * @return A string of the form "MMM d yyyy"
     */
    public String getFormattedFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats the end date into a string
     *
     * @return A string of the form "MMM d yyyy"
     */
    public String getFormattedTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats the start date into an ISO string
     *
     * @return An ISO string of the start date
     */
    public String getIsoFrom() {
        return this.from.toString();
    }

    /**
     * Formats the end date into an ISO string
     *
     * @return An ISO string of the end date
     */
    public String getIsoTo() {
        return this.to.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", getFormattedFrom(), getFormattedTo());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EventTask t) {
            boolean fromEquals = this.from.equals(t.from);
            boolean toEquals = this.to.equals(t.to);
            boolean descEquals = this.getDesc().equals(t.getDesc());
            return fromEquals && toEquals && descEquals;
        }
        return false;
    }
}
