package jinwoo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jinwoo.exception.DukeException;
import jinwoo.util.Parser;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs a Tasks.Event task with the given description, start time, and end time.
     *
     * @throws DukeException if the description, start time, or end time is empty.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = Parser.parseToDate(from);
        this.to = Parser.parseToDate(to);
        if (description.isBlank()) {
            throw new DukeException("Warning ??? : the description of an event cannot be empty.");
        }
        if (from.isBlank() || to.isBlank()) {
            throw new DukeException("Warning ??? : the time of an event cannot be empty.");
        }
    }

    /**
     * LocalDate to String
     * Storage print purpose
     */
    public String getFrom() {
        return from.toString();
    }

    public String getTo() {
        return to.toString();
    }

    /**
     * Creates and returns a copy for different history states.
     * Refer to copilot suggestion.
     */
    @Override
    public Task copyTask() throws DukeException {
        Event copy = new Event(this.description, this.from.toString(), this.to.toString());
        if (this.isDone) {
            copy.mark();
        }
        return copy;
    }

    /**
     * Get description for user print purpose
     */
    @Override
    public String getDescription() {
        return description + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + ")";
    }

    @Override
    public String toString() {
        return " [E]" + super.toString();
    }
}
