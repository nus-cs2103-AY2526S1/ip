package clippy.task;

import clippy.ClippyException;

/**
 * Represents a task that occurs during a specific time period.
 */
public class EventTask extends Task {
    private DateTime from;
    private DateTime to;

    /**
     * Constructs an EventTask with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The starting time of the event.
     * @param to          The ending time of the event.
     * @throws ClippyException If the description is null or empty, or if from/to are null or empty.
     */
    public EventTask(String description, String from, String to) throws ClippyException {
        super(validateDescription(description));
        if (from == null || from.trim().isEmpty()) {
            throw new ClippyException("An event must have a starting time(use /from <time>)");
        }
        if (to == null || to.trim().isEmpty()) {
            throw new ClippyException("An event must have an ending time(use /to <time>).");
        }
        this.from = new DateTime(from);
        this.to = new DateTime(to);
    }

    /**
     * Validates the description of the event task.
     *
     * @param description The description to validate.
     * @return The validated description.
     * @throws ClippyException If the description is null or empty.
     */
    private static String validateDescription(String description) throws ClippyException {
        if (description == null || description.trim().isEmpty()) {
            throw new ClippyException("Your event must have a description.");
        }
        return description;
    }

    public DateTime getFrom() {
        return this.from;
    }

    public DateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
