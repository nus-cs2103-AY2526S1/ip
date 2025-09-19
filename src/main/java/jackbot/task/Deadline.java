package jackbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with a due date/time.
 *
 * <p><b>User input format (from the Parser):</b>
 * <pre>{@code
 * deadline <description> /by <timestamp>
 * }</pre>
 * where {@code <timestamp>} is either:
 * <ul>
 *   <li>{@code yyyy-MM-dd HH:mm:ss} (e.g., {@code 2025-09-19 21:30:00}), or</li>
 *   <li>ISO local date-time {@code yyyy-MM-dd'T'HH:mm:ss} (e.g., {@code 2025-09-19T21:30:00}).</li>
 * </ul>
 *
 * <p><b>Persistence format (wire):</b>
 * <pre>{@code
 * D|<done>|<description>|<isoLocalDateTime>
 * }</pre>
 * where the fourth field is {@code LocalDateTime} in ISO_LOCAL_DATE_TIME
 * (e.g., {@code 2025-09-19T21:30:00}). See {@link #serialize()} and
 * {@link #Deadline(String, String)}.
 *
 */
public class Deadline extends Task {

    /** Display format used in {@link #toString()}. */
    private static final DateTimeFormatter DISPLAY_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Accepted input formats for the user-facing constructor. */
    private static final DateTimeFormatter[] INPUT_CANDIDATES = new DateTimeFormatter[] {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME, // 2025-09-19T21:30:00
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // 2025-09-19 21:30:00
    };

    private final LocalDateTime by;

    /**
     * Constructs a {@code Deadline} from a combined description string of the form
     * {@code "<description> /by <timestamp>"} as produced by the parser.
     *
     * @param description combined string containing description and {@code /by} timestamp
     * @throws IllegalArgumentException if the input is missing {@code " /by "} or the timestamp is invalid
     */
    public Deadline(String description) {
        super(extractDesc(description));
        this.by = parseWhen(extractWhen(description));
    }

    /**
     * Deserialization constructor used by {@link Task#deserialize(String)}.
     * Expects {@code by} in ISO_LOCAL_DATE_TIME.
     *
     * @param description task description
     * @param by ISO local date-time string (e.g., {@code 2025-09-19T21:30:00})
     * @throws DateTimeParseException if {@code by} is not a valid ISO local date-time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Returns UI text like {@code [D][ ] Do thing (by: 2025-09-19 21:30:00)}.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FMT) + ")";
    }

    /**
     * Serializes as {@code D|<done>|<description>|<isoLocalDateTime>}.
     */
    @Override
    public String serialize() {
        return "D" + "|" + (this.isDone() ? "1" : "0") + "|" + this.getDescription() + "|" + by;
        // by.toString() == ISO_LOCAL_DATE_TIME
    }

    // ---------- helpers ----------

    private static String extractDesc(String combined) {
        if (combined == null) {
            throw new IllegalArgumentException("Deadline description cannot be null");
        }
        String[] parts = combined.split(" \\/by ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException(
                    "Missing ' /by ' delimiter. Example: deadline Submit report /by 2025-09-19 21:30:00");
        }
        return parts[0].trim();
    }

    private static String extractWhen(String combined) {
        String[] parts = combined.split(" \\/by ", 2);
        return parts[1].trim();
    }

    private static LocalDateTime parseWhen(String s) {
        for (DateTimeFormatter fmt : INPUT_CANDIDATES) {
            try {
                return LocalDateTime.parse(s, fmt);
            } catch (DateTimeParseException ignored) {
                // try next
            }
        }
        throw new IllegalArgumentException(
                "Invalid datetime. Use 'yyyy-MM-dd HH:mm:ss' or ISO 'yyyy-MM-dd'T'HH:mm:ss'");
    }
}
