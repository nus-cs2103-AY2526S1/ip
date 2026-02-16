package yang.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task occurring at a specific date.
 */
public final class Event extends Task {
    private final LocalDate at;
    private final DateTimeFormatter OUT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a new {@code Event} task that is initially not done.
     *
     * @param description description of the event
     * @param at date when the event occurs
     */
    public Event(String description, LocalDate at) {
        super(description, false);
        this.at = at;
    }

    /**
     * Creates a new {@code Event} task with an explicit completion state.
     *
     * @param description description of the event
     * @param at date when the event occurs
     * @param isDone whether the event is already marked as done
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public boolean sameIdentity(Task other) {
        if (!(other instanceof Event e)) {
            return false;
        }
        return this.description.trim().equalsIgnoreCase(e.description.trim())
                && this.at.equals(e.getAt());
    }

    public LocalDate getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (at: " + at.format(OUT) + ")";
    }

    @Override
    public String toStorage() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + at;
    }
}
