package heimerdinger.task;

import heimerdinger.HeimerdingerException;
import heimerdinger.parser.DateTimeParser;

/**
 * Represents a {@code Task} with a due date/time.
 *
 * <p>Deadlines are parsed and stored using {@link DateTimeParser}, and
 * can be displayed in user-friendly form or encoded for persistence.</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * Deadline d = new Deadline("submit lab", "2025-10-31 18:00");
 * System.out.println(d);
 * // [D][ ] submit lab (by: Oct 31 2025, 18:00 hrs)
 * }</pre>
 *
 * @see DateTimeParser
 */
public class Deadline extends Task {
    protected String icon = "D";
    protected DateTimeParser deadline;

    /**
     * Constructs a new {@code Deadline}.
     *
     * @param description the description of the deadline task
     * @param deadline    the due date/time string, in {@code yyyy-MM-dd} or {@code yyyy-MM-dd HH:mm} format
     * @throws HeimerdingerException if the date/time string cannot be parsed
     */
    public Deadline(String description, String deadline) throws HeimerdingerException {
        super(description);
        this.deadline = DateTimeParser.parse(deadline);
    }

    /**
     * Returns the identifying icon for this task type.
     *
     * @return the string {@code "D"}
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Returns a string representation of this deadline, suitable for display to the user.
     *
     * <p>Format: {@code [D][status] description (by: <formatted date/time>)}.</p>
     *
     * @return a formatted string for display
     */
    public String toString() {
        return "[" + this.getIcon() + "][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline.toString() + ")";
    }

    /**
     * Returns a string encoding of the deadline’s date/time for persistence.
     *
     * <p>Format: {@code yyyy-MM-dd} or {@code yyyy-MM-dd HH:mm}, depending on input.</p>
     *
     * @return encoded date/time string
     */
    public String encode() {
        return this.deadline.encode();
    }

    /**
     * Returns the parsed {@link DateTimeParser} of this deadline.
     *
     * @return the {@link DateTimeParser} object for this deadline
     */
    public DateTimeParser getDeadline() {
        return this.deadline;
    }
}
