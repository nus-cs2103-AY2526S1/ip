package shadow.tasks;

import java.time.LocalDateTime;

import shadow.parsers.DateTimeParser;

/**
 * Represents an event task with a name, start time, and end time.
 * <p>
 * An Event is a subclass of {@link Task} that includes additional
 * attributes for start and end date-time values. It provides functionality
 * to format its details into a string representation, calculate the
 * time remaining until the event starts, and validate input parsing
 * for creating new event instances.
 */
public class Event extends Task {

    public static final String ERROR_MESSAGE = "Usage: event <taskName> /from <from> /to <to>";

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a new Event task with the specified name, start time, and end time.
     *
     * @param name the name or description of the event
     * @param startTime the date and time when the event starts
     * @param endTime the date and time when the event ends
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        long daysLeft = DateTimeParser.timeLeft(this.startTime);
        return String.format(
                "[E]%s (from: %s to: %s): ",
                super.toString(),
                DateTimeParser.format(this.startTime),
                DateTimeParser.format(this.endTime)
        ) + (daysLeft < 0 ? "event passed" : daysLeft + " days left");
    }

    /**
     * Creates a new {@link Event} task from the given input string.
     * <p>
     * Expects the input to contain a task name, a start time, and an end time,
     * separated by the delimiters "/from" and "/to" respectively.
     * Parses the start and end times using {@link DateTimeParser}.
     * If the input is {@code null} or improperly formatted, throws an {@link IllegalArgumentException}
     * with usage instructions.
     * Upon successful creation, the event task is printed to the console.
     * </p>
     *
     * @param input the raw user input containing the task description, start time, and end time
     * @return a new {@code Event} instance representing the task with its start and end times
     * @throws IllegalArgumentException if the input is {@code null} or missing required delimiters
     */
    public static Event of(String input) {
        if (input == null) {
            throw new IllegalArgumentException(Event.ERROR_MESSAGE);
        }

        String[] fromSplit = input.split("/from", 2);
        if (fromSplit.length < 2) {
            throw new IllegalArgumentException(Event.ERROR_MESSAGE);
        }

        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new IllegalArgumentException(Event.ERROR_MESSAGE);
        }
        LocalDateTime startTime = DateTimeParser.parse(toSplit[0].trim());
        LocalDateTime endTime = DateTimeParser.parse(toSplit[1].trim());
        return new Event(fromSplit[0].trim(), startTime, endTime);
    }
}
