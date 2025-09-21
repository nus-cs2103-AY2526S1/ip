package sofi;

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
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                // Default to midnight if no time specified
                return LocalDateTime.parse(dateTimeStr + " 0000", INPUT_FORMAT);
            } catch (DateTimeParseException e2) {
                try {
                    // Try US date format
                    DateTimeFormatter altFormat = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                    return LocalDateTime.parse(dateTimeStr, altFormat);
                } catch (DateTimeParseException e3) {
                    try {
                        DateTimeFormatter altFormat = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                        return LocalDateTime.parse(dateTimeStr + " 0000", altFormat);
                    } catch (DateTimeParseException e4) {
                        throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd, "
                                + "yyyy-MM-dd HHmm, M/d/yyyy, or M/d/yyyy HHmm format.");
                    }
                }
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