import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            // Try yyyy-MM-dd HHmm format first
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                // Try yyyy-MM-dd format (default to 00:00)
                return LocalDateTime.parse(dateTimeStr + " 0000", INPUT_FORMAT);
            } catch (DateTimeParseException e2) {
                // If all parsing fails, throw a more informative exception
                throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HHmm format.");
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}