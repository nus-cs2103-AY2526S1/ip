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
        assert description != null && !description.isBlank() :
                "Deadline description cannot be null/blank";
        assert byStr != null && !byStr.isBlank() :
                "Deadline 'by' string cannot be null/blank";
        this.by = parseDateTime(byStr);
        assert this.by != null :
                "Deadline 'by' LocalDateTime must not be null";
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
        assert by != null : "Deadline date must be initialized before formatting";
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
        assert by != null : "Deadline date must never be null";
        return by; // for comparisons or filtering
    }
}
