package guibot.task;

import java.time.LocalDateTime;

import guibot.Parser;
import guibot.exception.WrongDateTimeFormatException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a Deadline task.
     *
     * @param description Description of the deadline task.
     * @param deadline Deadline of the task.
     */
    private Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method that creates a Deadline task from an array of strings.
     * Assumes that the number of elements in details is correct.
     *
     * @param details Array of string details to create Deadline task from.
     * @throws WrongDateTimeFormatException If the date time is in the wrong format.
     */
    public static Deadline of(String... details) throws WrongDateTimeFormatException {
        assert details.length == 2 : "Wrong number of elements in details";
        String description = details[0];
        LocalDateTime deadline = Parser.getDateTimeFromString(details[1]);
        return new Deadline(description, deadline);
    }

    @Override
    public String toStorageString() {
        return "d//" + super.toStorageString() + "/" + Parser.getInputStringFromDateTime(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.getOutputStringFromDateTime(deadline) + ")";
    }
}
