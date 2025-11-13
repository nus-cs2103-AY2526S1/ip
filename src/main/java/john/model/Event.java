package john.model;

import java.time.LocalDateTime;

/**
 * Task representing an event with a start and end date-time.
 * <p>
 * Format responsibilities:
 * - toString() renders as: [E]{base} (from: dd/MM/yyyy HH:mm:ss to: dd/MM/yyyy HH:mm:ss)
 * - serialise() emits a pipe-delimited line using Task.baseSerialize:
 * E | done(0|1) | title | from | to
 * <p>
 * Invariants:
 * - from and to are non-null.
 * - The parser is expected to have validated that to is not before from.
 * <p>
 * Note:
 * - The exact date-time string is produced by Task.formatTime(LocalDateTime),
 * which should match the parser's expected pattern.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an event task.
     *
     * @param title event description; must not be null
     * @param from  start date-time; must not be null
     * @param to    end date-time; must not be null and not before start
     */
    public Event(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        assert from != null && to != null : "event times must not be null";
        assert !to.isBefore(from) : "event end must be >= start";
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a human-readable representation of the event.
     * Example: [E][ ] project demo (from: 05/09/2025 09:00:00 to: 05/09/2025 10:00:00)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatTime(from) + " to: " + formatTime(to) + ")";
    }

    /**
     * Serialises this task to a pipe-delimited record suitable for FileStorage.
     * Format: E | done(0|1) | title | from | to
     *
     * @return the serialised line
     */
    @Override
    public String serialise() {
        return baseSerialize("E", formatTime(from), formatTime(to));
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }
}
