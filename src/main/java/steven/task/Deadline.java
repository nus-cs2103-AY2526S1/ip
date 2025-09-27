package steven.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import steven.exception.InvalidDateAndTimeFormatException;

/**
 * Represents a task that needs to be done before a specific date or time.
 *
 * @author w3njii
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;
    private final String deadlineString;

    /**
     * Creates a new deadline task with the given description and deadline.
     * The deadline must be provided in the format: dd-mm-yyyy tttt
     * where dd is the day, mm is the month, yyyy is the year, and
     * tttt is the time in 24-hour format.
     *
     * @param description the description of the task
     * @param deadline the deadline string in the specified format
     * @throws InvalidDateAndTimeFormatException If the given deadline string is not in the correct format
     */
    public Deadline(String description, String deadline) throws InvalidDateAndTimeFormatException {
        super(description);
        assert deadline != null : "Deadline should not be null";
        try {
            this.deadlineString = deadline;
            int day = Integer.parseInt(deadline.substring(0, 2));
            int month = Integer.parseInt(deadline.substring(3, 5));
            int year = Integer.parseInt(deadline.substring(6, 10));
            int hour = Integer.parseInt(deadline.substring(11, 13));
            int minute = Integer.parseInt(deadline.substring(13, 15));
            this.deadline = LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new InvalidDateAndTimeFormatException();
        }
    }

    @Override
    public String convertToSaveFormat() {
        return "[D]" + super.convertToSaveFormat() + " (by: " + this.deadlineString + ")";
    }

    /**
     * {@inheritDoc}
     *
     * Additionally, adds a "[D]" at the front to represent "deadline" task, and the deadline at the end.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
