package heimerdinger.task;

import heimerdinger.HeimerdingerException;
import heimerdinger.parser.DateTimeParser;

/**
 * Represents a {@code Task} that spans a date/time range.
 *
 * <p>Events are parsed and stored using {@link DateTimeParser} for both start
 * ({@code fromDate}) and end ({@code toDate}). The constructor ensures the
 * end date is not before the start date.</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * Event e = new Event("project sync", "2025-10-03 09:00", "2025-10-03 10:00");
 * System.out.println(e);
 * // [E][ ] project sync (from: Oct 3 2025, 09:00 hrs to: Oct 3 2025, 10:00 hrs)
 * }</pre>
 *
 * @see DateTimeParser
 */
public class Event extends Task {
    protected String icon = "E";
    protected DateTimeParser fromDate;
    protected DateTimeParser toDate;

    /**
     * Constructs a new {@code Event}.
     *
     * @param description the description of the event
     * @param fromDate    the start date/time string, in {@code yyyy-MM-dd} or {@code yyyy-MM-dd HH:mm} format
     * @param toDate      the end date/time string, in {@code yyyy-MM-dd} or {@code yyyy-MM-dd HH:mm} format
     * @throws HeimerdingerException if either date cannot be parsed, or if the end is before the start
     */
    public Event(String description, String fromDate, String toDate) throws HeimerdingerException {
        super(description);
        this.fromDate = DateTimeParser.parse(fromDate);
        this.toDate = DateTimeParser.parse(toDate);
        if (this.toDate.isBefore(this.fromDate)) {
            throw new HeimerdingerException("Your dates are not in chronological order!");
        }
    }

    /**
     * Returns the identifying icon for this task type.
     *
     * @return the string {@code "E"}
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Returns a string representation of this event, suitable for display to the user.
     *
     * <p>Format: {@code [E][status] description (from: <formatted fromDate> to: <formatted toDate>)}.</p>
     *
     * @return a formatted string for display
     */
    public String toString() {
        return "[" + this.getIcon() + "][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.fromDate.toString() + " to: " + this.toDate.toString() + ")";
    }

    /**
     * Returns the encoded form of the start date/time for persistence.
     *
     * @return encoded start date/time string
     */
    public String fromDateEncode() {
        return this.fromDate.encode();
    }

    /**
     * Returns the encoded form of the end date/time for persistence.
     *
     * @return encoded end date/time string
     */
    public String toDateEncode() {
        return this.toDate.encode();
    }

    /**
     * Returns the parsed start date/time of this event.
     *
     * @return the {@link DateTimeParser} representing the start date/time
     */
    public DateTimeParser getFromDate() {
        return this.fromDate;
    }


    /**
     * Returns the parsed end date/time of this event.
     *
     * @return the {@link DateTimeParser} representing the end date/time
     */
    public DateTimeParser getToDate() {
        return this.toDate;
    }
}
