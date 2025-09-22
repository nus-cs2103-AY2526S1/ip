package moon.models;

/**
 * Represents an event task with a description and start/end times.
 * <p>
 * Inherits the name and done status from {@link Task}.
 */
public class Event extends Task {
    private final MoonDateTime fromTime;
    private final MoonDateTime toTime;

    /**
     * Creates a new event task.
     *
     * @param name     Description of the event
     * @param fromTime Start time of the event
     * @param toTime   End time of the event
     */
    public Event(String name, MoonDateTime fromTime, MoonDateTime toTime) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    private String getFromTime() {
        return this.fromTime.toString();
    }

    private String getToTime() {
        return this.toTime.toString();
    }

    /**
     * Converts this event to its storage string representation.
     * <p>
     * Format: {@code E | doneFlag | name | fromTime | toTime}
     * <ul>
     *   <li>{@code E} = event identifier</li>
     *   <li>{@code doneFlag} = {@code 1} if done, {@code 0} otherwise</li>
     *   <li>{@code name} = description</li>
     *   <li>{@code fromTime}, {@code toTime} = event times</li>
     * </ul>
     *
     * @return Storage string for this event
     */
    @Override
    public String toStorageString() {
        return String.join(" | ",
                "E",
                this.isDone() ? "1" : "0",
                getName(),
                this.getFromTime(),
                this.getToTime());
    }

    /**
     * Returns the string representation of this event for user display.
     * <p>
     * Format: {@code [E][X] name (from: fromTime to: toTime)}
     *
     * @return Formatted string for display
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.getFromTime(), this.getToTime());
    }
}
