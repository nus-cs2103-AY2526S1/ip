package Note.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;

    // Input formatter: accepts d/M/yyyy HHmm (24-hour)
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    // Output formatter: displays as MMM d yyyy, h:mm a (12-hour with AM/PM)
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    public Deadline(String description, String byStr) {
        super(description);
        this.by = parseDateTime(byStr);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format. Use d/M/yyyy HHmm, e.g., 2/12/2019 1800", e);
        }
    }

    public String getBy() {
        return by.format(OUTPUT_FORMATTER);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] "
                + description + " (by: " + getBy() + ")";
    }

    public LocalDateTime getByDateTime() {
        return by; // for comparisons or filtering
    }
}
