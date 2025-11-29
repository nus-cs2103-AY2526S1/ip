package larry.model;

import larry.util.DateTimeFormats;

/**
 * A task that is due by a specific date/time.
 * Stores the raw input; formatted when displayed.
 */
public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    protected String typeIcon() {
        return "D";
    }
    @Override
    public String toString() {

        return super.toString() + " (by: " + DateTimeFormats.pretty(by) + ")";
    }
    public String getBy() {
        return by;
    }
}
