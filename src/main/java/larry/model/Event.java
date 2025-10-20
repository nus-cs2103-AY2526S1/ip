package larry.model;

import larry.util.DateTimeFormats;

/**
 * A task that is due by a specific date/time.
 * Stores the raw input; formatted when displayed.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    protected String typeIcon() {
        return "E";
    }
    @Override
    public String toString() {
        return super.toString()
                + " (from: " + DateTimeFormats.pretty(from)
                + " to: " + DateTimeFormats.pretty(to) + ")";
    }

    /**
     * Returns the raw user-provided start datetime string for this event.
     * <p>
     * Display formatting is handled elsewhere (see {@link larry.util.DateTimeFormats}).
     *
     * @return the start datetime as originally entered by the user
     */
    public String getFrom() { return from; }

    /**
     * Returns the raw user-provided end datetime string for this event.
     * <p>
     * Display formatting is handled elsewhere (see {@link larry.util.DateTimeFormats}).
     *
     * @return the end datetime as originally entered by the user
     */
    public String getTo() { return to; }
}
