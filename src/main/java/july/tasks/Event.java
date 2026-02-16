package july.tasks;

import july.datetime.StringTime;
import july.error.InvalidEventException;

/**
 * Represents a task with a specific time period defined by start and end times.
 * This class extends Task to include event functionality, allowing tasks
 * to have both a "from" start time and a "to" end time.
 */
public class Event extends Task {

    protected StringTime from;
    protected StringTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description the description of the event task
     * @param from the start date/time as a string
     * @param to the end date/time as a string
     */
    public Event(String description, String from, String to) {
        super(description);
        assert from != null : "Event start time cannot be null";
        assert to != null : "Event end time cannot be null";
        this.from = new StringTime(from);
        this.to = new StringTime(to);
    }

    /**
     * Processes an event command argument to create an Event object.
     * Parses the input string to extract the task description, start time, and end time,
     * expecting the format "description /from startTime /to endTime".
     *
     * @param argument the command argument containing description and time details separated by "/from" and "/to"
     * @return a new Event object created from the parsed input
     * @throws InvalidEventException if the argument doesn't contain exactly one "/from" and one "/to" separator
     */
    public static Event process(String argument) throws InvalidEventException {
        if (!argument.contains("/from") || !argument.contains("/to")) {
            throw new InvalidEventException("You need ONE '/from' and ONE '/to' for the event command");
        }

        String[] toSplit = argument.split("/to", 2);
        String to = toSplit[1].trim();

        String[] fromSplit = toSplit[0].split("/from", 2);
        String from = fromSplit[1].trim();
        String description = fromSplit[0].trim();

        assert !description.isEmpty() : "Description cannot be empty";
        assert !from.isEmpty() : "Start time cannot be empty";
        assert !to.isEmpty() : "End time cannot be empty";
        return new Event(description, from, to);
    }

    /**
     * Returns a string representation of this event task.
     * Includes the task type marker "[E]", the parent task information,
     * and the time period in parentheses.
     *
     * @return a formatted string showing the event task details with time period
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + from + " to: " + to + ")";
    }

    /**
     * Returns the save format representation of this event task.
     * Creates a string suitable for file storage containing the task type,
     * completion status, description, start time, and end time.
     *
     * @return a formatted string for saving the event task to storage
     */
    public String toSave() {
        return String.format("E %s %s /from %s /to %s", super.isDone ? "1" : "0", super.description,
                this.from.toSave(), this.to.toSave());
    }

    /**
     * Checks if this event task is relevant for the specified date.
     * Determines if the given date falls within the event's time period
     * by checking if it's after the start time and before the end time.
     *
     * @param o the StringTime object representing the date to check against
     * @return true if the specified date falls within the event's time period and all dates are valid,
     *         false if any date is invalid or the specified date is outside the event period
     */
    public boolean check(StringTime o) {
        if (from.isString() || to.isString() || o.isString()) {
            return false;
        }
        return from.compareTo(o) < 0 && to.compareTo(o) > 0 && !super.isDone;
    }
}
