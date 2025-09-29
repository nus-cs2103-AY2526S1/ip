package david.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import david.exception.InvalidDateTimeException;

/**
 * Represents a Task with a start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initialises an Event Task.
     *
     * @param text Task description.
     * @param from Task start datetime.
     * @param to Task end datetime.
     */
    public Event(String text, String from, String to) throws InvalidDateTimeException {
        super(text);

        assert from!= null : "From should never be null";
        assert to!= null : "To should never be null";

        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.from = LocalDateTime.parse(from, inputFormat);
            this.to = LocalDateTime.parse(to, inputFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Eh your datetime format is wrong leh. Use this format lah: yyyy-MM-dd HHmm");
        }

    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[E]" + super.toString() + " (from: " + this.from.format(outputFormat) + " to: " + this.to.format(outputFormat) + ")";
    }
}
