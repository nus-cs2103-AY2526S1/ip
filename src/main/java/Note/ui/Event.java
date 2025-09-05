package Note.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    public Event(String description, String fromStr, String toStr) {
        super(description);
        this.from = parseDateTime(fromStr);
        this.to = parseDateTime(toStr);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format. Use d/M/yyyy HHmm, e.g., 2/12/2019 1800", e);
        }
    }

    public String getFrom() {
        return from.format(OUTPUT_FORMATTER);
    }

    public String getTo() {
        return to.format(OUTPUT_FORMATTER);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] "
                + description + " (from: " + getFrom() + " to: " + getTo() + ")";
    }

    public LocalDateTime getFromDateTime() {
        return from;
    }

    public LocalDateTime getToDateTime() {
        return to;
    }
}
