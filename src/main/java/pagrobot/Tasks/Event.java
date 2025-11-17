package pagrobot.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task that spans a period of time. An Event has a
 * description, a start time, and an end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event with the given name, start, and end time.
     *
     * @param name the description of the event task.
     * @param from the start time as a string.
     * @param to the end time as a string.
     */
    public Event(String name, String from, String to) {
        this(name, from, to, false);
    }

    /**
     * Constructs a new Event with the given name, start, end time, and
     * status.
     *
     * @param name the description of the event task.
     * @param from the start time as a string.
     * @param to the end time as a string.
     * @param isDone whether the task is completed.
     */
    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = super.parseDate(from);
        this.to = super.parseDate(to);
    }

    /**
     * Returns the string representation of this Event.
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format("(from: %s to: %s)", from.format(outputFormatter), to.format(outputFormatter));
    }

    /**
     * Creates an Event from its saved memory representation.
     *
     * @param input the serialized string.
     * @return the reconstructed event task.
     */
    public static Task fromMemory(String input) {
        String[] parts = input.split("\\|");
        return new Event(parts[2], parts[3], parts[4], parts[1].equals("1"));
    }

    /**
     * Returns the string representation of this Event for saving to
     * memory.
     *
     * @return the memory string of this event.
     */
    @Override
    public String toMemory() {
        return String.format("E|%d|%s|%s|%s", super.isDone() ? 1 : 0, super.getName(), this.from, this.to);
    }

    /**
     * Returns the start time of this event.
     *
     * @return the start time as a LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of this event.
     *
     * @return the end time as a LocalDateTime.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    public static String inputArgument() {
        return "event <task name> /from <date: dd/mm/yyyy> <time: HHmm> /to <date: dd/mm/yyyy> <time: HHmm>";
    }
}
