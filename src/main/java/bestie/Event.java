package bestie;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Task that spans a time range, optionally storing parsed temporal data.
 */
public class Event extends Task {
    // typed (if parsable)
    private final LocalDate fromDate;
    private final LocalDateTime fromDateTime;
    private final LocalDate toDate;
    private final LocalDateTime toDateTime;

    // raw fallbacks
    private final String fromRaw;
    private final String toRaw;

    /**
     * Creates an event with start and end temporal information.
     *
     * @param description high-level description of the event
     * @param from        textual representation of when the event starts
     * @param to          textual representation of when the event ends
     */
    public Event(String description, String from, String to) throws BestieException{
        super(description, TaskType.EVENT);
        this.fromRaw = from.trim();
        this.toRaw = to.trim();

        LocalDateTime fdt = DateTimeUtil.parseDateTime(this.fromRaw);
        LocalDateTime tdt = DateTimeUtil.parseDateTime(this.toRaw);

        if (fdt != null) {
            this.fromDateTime = fdt;
            this.fromDate = null;
        } else {
            this.fromDate = DateTimeUtil.parseDate(this.fromRaw);
            this.fromDateTime = null;
            if (this.fromDate == null) {
                throw new BestieException(
                        "the /from date doesn't look valid bestie! try something like yyyy-MM-dd or dd/MM/yyyy.");
            }
        }

        if (tdt != null) {
            this.toDateTime = tdt;
            this.toDate = null;
        } else {
            this.toDate = DateTimeUtil.parseDate(this.toRaw);
            this.toDateTime = null;
            if (this.toDate == null) {
                throw new BestieException(
                        "the /to date doesn't look valid bestie! try something like yyyy-MM-dd or dd/MM/yyyy.");
            }
        }
    }

    /**
     * Returns a user-friendly string describing the event timeframe.
     */
    @Override
    public String toString() {
        String fromNice, toNice;

        if (fromDateTime != null) {
            fromNice = DateTimeUtil.pretty(fromDateTime);
        } else if (fromDate != null) {
            fromNice = DateTimeUtil.pretty(fromDate);
        } else {
            fromNice = fromRaw;
        }

        if (toDateTime != null) {
            toNice = DateTimeUtil.pretty(toDateTime);
        } else if (toDate != null) {
            toNice = DateTimeUtil.pretty(toDate);
        } else {
            toNice = toRaw;
        }

        return "[E]" + super.toString() + " (from: " + fromNice + " to: " + toNice + ")";
    }

    /**
     * Serializes the event into the pipe-delimited storage representation.
     */
    @Override
    public String toDataString() {
        String done = (status == Status.DONE) ? "1" : "0";
        String fromStored, toStored;

        if (fromDateTime != null) {
            fromStored = DateTimeUtil.canonical(fromDateTime);
        } else if (fromDate != null) {
            fromStored = DateTimeUtil.canonical(fromDate);
        } else {
            fromStored = fromRaw;
        }

        if (toDateTime != null) {
            toStored = DateTimeUtil.canonical(toDateTime);
        } else if (toDate != null) {
            toStored = DateTimeUtil.canonical(toDate);
        } else {
            toStored = toRaw;
        }

        String base = type.getShortCode() + " | " + done + " | " + description
                + " | " + fromStored + " | " + toStored;
        return appendTagsForStorage(base);
    }
}
