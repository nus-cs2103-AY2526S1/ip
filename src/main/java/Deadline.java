package sofi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                // Default to 11:59 PM if no time specified
                return LocalDateTime.parse(dateTimeStr + " 2359", INPUT_FORMAT);
            } catch (DateTimeParseException e2) {
                try {
                    // Try US date format
                    DateTimeFormatter altFormat = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                    return LocalDateTime.parse(dateTimeStr, altFormat);
                } catch (DateTimeParseException e3) {
                    try {
                        DateTimeFormatter altFormat = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                        return LocalDateTime.parse(dateTimeStr + " 2359", altFormat);
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
        String tagsString = getTagsString();
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")" +
               (tagsString.isEmpty() ? "" : " " + tagsString);
    }

    public LocalDateTime getBy() {
        return by;
    }
}

