package jackbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task representing an event with a start and end timestamp.
 *
 * <p><b>User input format (from the Parser):</b>
 * <pre>{@code
 * event <description> /from <ts1> /to <ts2>
 * }</pre>
 * where each timestamp is either:
 * <ul>
 *   <li>{@code yyyy-MM-dd HH:mm:ss} (e.g., {@code 2025-09-19 21:30:00}), or</li>
 *   <li>ISO local date-time {@code yyyy-MM-dd'T'HH:mm:ss} (e.g., {@code 2025-09-19T21:30:00}).</li>
 * </ul>
 *
 * <p><b>Persistence format (wire):</b>
 * <pre>{@code
 * E|<done>|<description>|<fromIso>|<toIso>
 * }</pre>
 * where the timestamps are {@link java.time.format.DateTimeFormatter#ISO_LOCAL_DATE_TIME}.
 *
 */
public class Event extends Task {

    /** Display format used in {@link #toString()}. */
    private static final DateTimeFormatter DISPLAY_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Accepted input formats for the user-facing constructor. */
    private static final DateTimeFormatter[] INPUT_CANDIDATES = new DateTimeFormatter[] {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME, // 2025-09-19T21:30:00
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // 2025-09-19 21:30:00
    };

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an {@code Event} from a combined description of the form
     * {@code "<description> /from <ts1> /to <ts2>"}.
     *
     * @param description combined string containing description and time range
     * @throws IllegalArgumentException if required delimiters are missing or any timestamp is invalid
     */
    public Event(String description) {
        super(extractDesc(description));
        String[] range = extractRange(description); // [fromStr, toStr]
        this.from = parseWhen(range[0]);
        this.to = parseWhen(range[1]);
    }

    /**
     * Deserialization constructor used by {@link Task#deserialize(String)}.
     * Expects ISO local date-time strings.
     *
     * @param description task description
     * @param from ISO local date-time (e.g., {@code 2025-09-19T21:30:00})
     * @param to   ISO local date-time (e.g., {@code 2025-09-19T22:45:00})
     * @throws DateTimeParseException if a timestamp is not valid ISO local date-time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Returns UI text like:
     * {@code [E][ ] Team sync (from: 2025-09-19 21:30:00 to: 2025-09-19 22:00:00)}.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DISPLAY_FMT)
                + " to: " + to.format(DISPLAY_FMT) + ")";
    }

    /**
     * Serializes as {@code E|<done>|<description>|<fromIso>|<toIso>}.
     */
    @Override
    public String serialize() {
        // LocalDateTime#toString() == ISO_LOCAL_DATE_TIME
        return "E" + "|" + (this.isDone() ? "1" : "0") + "|"
                + this.getDescription() + "|" + from + "|" + to;
    }

    // ---------- helpers ----------

    private static String extractDesc(String combined) {
        if (combined == null) {
            throw new IllegalArgumentException("Event description cannot be null");
        }
        String[] parts = combined.split(" \\/from ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException(
                    "Missing ' /from ' delimiter. Example: event"
                    + "Standup /from 2025-09-19 09:00:00 /to 2025-09-19 09:15:00");
        }
        return parts[0].trim();
    }

    private static String[] extractRange(String combined) {
        String[] firstSplit = combined.split(" \\/from ", 2);
        if (firstSplit.length < 2) {
            throw new IllegalArgumentException(
                    "Missing ' /from ' delimiter. Example: event Title /from <ts1> /to <ts2>");
        }
        String[] secondSplit = firstSplit[1].split(" \\/to ", 2);
        if (secondSplit.length < 2) {
            throw new IllegalArgumentException(
                    "Missing ' /to ' delimiter. Example: event Title /from <ts1> /to <ts2>");
        }
        return new String[] { secondSplit[0].trim(), secondSplit[1].trim() };
    }

    private static LocalDateTime parseWhen(String s) {
        IllegalArgumentException lastError = null;
        for (DateTimeFormatter fmt : INPUT_CANDIDATES) {
            try {
                return LocalDateTime.parse(s, fmt);
            } catch (DateTimeParseException ex) {
                // Keep the last failure to provide context if all formats fail
                lastError = new IllegalArgumentException(
                    "Failed to parse with format: " + fmt, ex);
            }
        }

        throw new IllegalArgumentException(
            "Invalid datetime. Use 'yyyy-MM-dd HH:mm:ss' or ISO 'yyyy-MM-dd'T'HH:mm:ss'",
            lastError
        );
    }
}
