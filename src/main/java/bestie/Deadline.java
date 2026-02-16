package bestie;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Task with a deadline, optionally storing parsed temporal information for
 * nicer display and consistent persistence.
 */
public class Deadline extends Task {
    // typed fields if parsable
    private final LocalDate byDate;
    private final LocalDateTime byDateTime;
    // raw text fallback for unparsed inputs and backward compatibility
    private final String byRaw;

    /**
     * Creates a deadline task with a description and due date/time.
     *
     * @param description description of the work to complete
     * @param by          textual representation of when the task is due
     */
    public Deadline(String description, String by) throws BestieException {
        super(description, TaskType.DEADLINE);
        this.byRaw = by.trim();
        LocalDateTime dt = DateTimeUtil.parseDateTime(this.byRaw);
        if (dt != null) {
            this.byDateTime = dt;
            this.byDate = null;
        } else {
            this.byDate = DateTimeUtil.parseDate(this.byRaw);
            this.byDateTime = null;
            if (this.byDate == null) {
                throw new BestieException(
                        "that doesn't look like a valid date bestie! try something like yyyy-MM-dd or dd/MM/yyyy.");
            }
        }
    }

    /**
     * Returns a user-friendly string with the parsed deadline information.
     */
    @Override
    public String toString() {
        String nice;
        if (byDateTime != null) {
            nice = DateTimeUtil.pretty(byDateTime);
        } else if (byDate != null) {
            nice = DateTimeUtil.pretty(byDate);
        } else {
            nice = byRaw;
        }
        // Keep your existing "[D]" style plus base "[ ] desc"
        return "[D]" + super.toString() + " (by: " + nice + ")";
    }

    /**
     * Serializes the task with normalized temporal fields when available.
     */
    @Override
    public String toDataString() {
        String done = (status == Status.DONE) ? "1" : "0";
        String stored;
        if (byDateTime != null) {
            stored = DateTimeUtil.canonical(byDateTime); // yyyy-MM-dd HHmm
        } else if (byDate != null) {
            stored = DateTimeUtil.canonical(byDate);     // yyyy-MM-dd
        } else {
            stored = byRaw; // keep old data as-is
        }
        String base = type.getShortCode() + " | " + done + " | " + description + " | " + stored;
        return appendTagsForStorage(base);
    }
}
