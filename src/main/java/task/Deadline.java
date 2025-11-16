package task;

import exception.FrennyException;
import exception.TimeFormatException;
import time.Time;

/**
 * Represents a deadline task with a description and a due date/time.
 */
public class Deadline extends Task {

    private final String by;
    private Time time;

    private Deadline(String description, String by, boolean isDone, Time time) {
        super(description);
        this.by = by;
        this.isDone = isDone;
        this.time = time;
    }

    /**
     * Returns a string representation of the deadline task, including its type, status, description, and due date/time.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.getDateTime() + ")";
    }

    /**
     * Creates a new Deadline task from the given detail string and completion status.
     *
     * @param detail The detail string containing the description and due date/time.
     * @param isDone The completion status of the task.
     * @return A new Deadline task.
     * @throws FrennyException If the detail string is improperly formatted or missing required information.
     * @throws TimeFormatException If the due date/time format is invalid.
     */
    public static Deadline addDeadlineTask(String detail, boolean isDone) throws FrennyException, TimeFormatException {
        if (!detail.contains(" /by ")) {
            throw new FrennyException("The deadline must be specified with ' /by ' my fren :(");
        }
        if (detail.indexOf(" /by ") != detail.lastIndexOf(" /by ")) {
            throw new FrennyException("There should be only one ' /by ' in a deadline my fren :(");
        }
        String[] parts = detail.split(" /by ", 2);
        String description = parts[0];
        if (detail.trim().isEmpty() || description.trim().isEmpty()) {
            throw new FrennyException("The description of a deadline cannot be empty my fren :(");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new FrennyException("The deadline time cannot be empty my fren :(");
        }
        String by = parts[1];
        Time time = Time.parseDateTime(by);
        return new Deadline(description, by, isDone, time);
    }

    public String generateCommandString() {
        return "deadline " + this.description + " /by " + this.by;
    }

    public String generateHistoryFileEntry() {
        return String.format("%s | %s", getDoneEncoding(), generateCommandString());
    }
}
