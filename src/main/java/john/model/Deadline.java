package john.model;

import java.time.LocalDateTime;

/**
 * Task representing a deadline with a single due date-time.
 * <p>
 * Format responsibilities:
 * - toString() renders as: [D]{base} (by: dd/MM/yyyy HH:mm:ss)
 * - serialise() emits a pipe-delimited line using Task.baseSerialize:
 * D | done(0|1) | title | by
 * <p>
 * Invariants:
 * - deadline is non-null.
 * <p>
 * Note:
 * - The exact date-time string is produced by Task.formatTime(LocalDateTime),
 * which should match the parser's expected pattern.
 */
public class Deadline extends Task {
    /**
     * Due date-time for this deadline task.
     */
    private final LocalDateTime deadline;

    /**
     * Creates a deadline task.
     *
     * @param title    task description; must not be null
     * @param deadline the due date-time; must not be null
     */
    public Deadline(String title, LocalDateTime deadline) {
        super(title);
        assert deadline != null : "deadline must not be null";
        this.deadline = deadline;
    }

    /**
     * Returns a human-readable representation of the deadline.
     * Example: [D][ ] submit report (by: 05/09/2025 16:30:45)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatTime(deadline) + ")";
    }

    /**
     * Serialises this task to a pipe-delimited record suitable for FileStorage.
     * Format: D | done(0/1) | title | by
     *
     * @return the serialised line
     */
    @Override
    public String serialise() {
        return baseSerialize("D", formatTime(deadline));
    }
}
