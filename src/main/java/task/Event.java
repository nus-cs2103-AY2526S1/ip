package task;

import exception.FrennyException;
import exception.TimeFormatException;
import time.Time;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    private String from;
    private String to;
    private Time timeFrom;
    private Time timeTo;

    private Event(String description, String from, String to, boolean isDone, Time timeFrom, Time timeTo) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    /**
     * Returns a string representation of the event task, including its type, status, description, and time range.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + timeFrom.getDateTime() + " to: " + timeTo.getDateTime() + ")";
    }

    /**
     * Creates a new Event task from the given detail string and completion status.
     *
     * @param detail The detail string containing the description, start time, and end time.
     * @param isDone The completion status of the task.
     * @return A new Event task.
     * @throws FrennyException If the detail string is improperly formatted or missing required information.
     * @throws TimeFormatException If the start or end time format is invalid.
     */
    public static Event addEventTask(String detail, boolean isDone) throws FrennyException, TimeFormatException {
        if (!detail.contains(" /from ") || !detail.contains(" /to ")) {
            throw new FrennyException("The event must be specified with ' /from ' and ' /to ' my fren :(");
        }
        String[] parts = detail.split(" /from ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new FrennyException("The description of an event cannot be empty my fren :(");
        }
        String description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new FrennyException("The event time cannot be empty my fren :(");
        }
        String from = timeParts[0];
        String to = timeParts[1];
        Time timeFrom = Time.parseDateTime(from);
        Time timeTo = Time.parseDateTime(to);
        return new Event(description, from, to, isDone, timeFrom, timeTo);
    }

    // I used Copilot to suggest similar message format in Todo and Deadline
    public String generateCommandString() {
        return "event " + this.description + " /from " + this.from + " /to " + this.to;
    }

    // I used Copilot to suggest similar message format in Todo and Deadline
    public String generateHistoryFileEntry() {
        return String.format("%s | %s", getDoneEncoding(), generateCommandString());
    }
}
