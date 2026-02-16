package guibot.task;

import java.time.LocalDateTime;

import guibot.Parser;
import guibot.exception.WrongDateTimeFormatException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates an Event task.
     *
     * @param description Description of the deadline task.
     * @param from The start of the task.
     * @param to The end of the task.
     */
    private Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method that creates an Event task from an array of strings.
     * Assumes that the number of elements in details is correct.
     *
     * @param details Array of string details to create Event task from.
     * @throws WrongDateTimeFormatException If the date time is in the wrong format.
     */
    public static Event of(String... details) throws WrongDateTimeFormatException {
        assert details.length == 3 : "Wrong number of elements in details";
        String description = details[0];
        LocalDateTime start = Parser.getDateTimeFromString(details[1]);
        LocalDateTime end = Parser.getDateTimeFromString(details[2]);
        return new Event(description, start, end);
    }

    @Override
    public String toStorageString() {
        return "e//" + super.toStorageString()
                + "/" + Parser.getInputStringFromDateTime(start)
                + "/" + Parser.getInputStringFromDateTime(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + Parser.getOutputStringFromDateTime(start)
                + " to: " + Parser.getOutputStringFromDateTime(end) + ")";
    }
}
