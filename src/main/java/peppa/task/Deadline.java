package peppa.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task that must be finished by a specific date-time.
 * Stores the deadline and formats itself for display and persistence.
 */
public class Deadline extends Task {
    private static final String SAVE_DATE_FORMAT = "d/M/yyyy HHmm";
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy h a";
    private static final String OUTPUT_DATE_ONLY_FORMAT = "MMM d yyyy";
    
    // Supported input formats (with time)
    private static final String[] DATE_TIME_FORMATS = {
        "d/M/yyyy HHmm",     // 2/12/2019 1800
        "dd/MM/yyyy HHmm",   // 02/12/2019 1800
        "d-M-yyyy HHmm",     // 2-12-2019 1800
        "dd-MM-yyyy HHmm",   // 02-12-2019 1800
        "yyyy-MM-dd HHmm",   // 2019-12-02 1800
        "d/M/yyyy HH:mm",    // 2/12/2019 18:00
        "dd/MM/yyyy HH:mm",  // 02/12/2019 18:00
        "d-M-yyyy HH:mm",    // 2-12-2019 18:00
        "dd-MM-yyyy HH:mm",  // 02-12-2019 18:00
        "yyyy-MM-dd HH:mm"   // 2019-12-02 18:00
    };
    
    // Supported input formats (date only)
    private static final String[] DATE_ONLY_FORMATS = {
        "d/M/yyyy",          // 2/12/2019
        "dd/MM/yyyy",        // 02/12/2019
        "d-M-yyyy",          // 2-12-2019
        "dd-MM-yyyy",        // 02-12-2019
        "yyyy-MM-dd"         // 2019-12-02
    };
    
    protected LocalDateTime by;

    /**
     * Creates a new deadline task.
     *
     * @param description human-readable task details
     * @param by due date-time in various supported formats
     *           With time: d/M/yyyy HHmm, dd/MM/yyyy HH:mm, yyyy-MM-dd HH:mm, etc.
     *           Date only: d/M/yyyy, dd/MM/yyyy, yyyy-MM-dd, etc.
     *           If time is not specified, defaults to 23:59 (end of day)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by.trim());
    }

    /**
     * Parses a date-time string in various supported formats.
     * If time is not specified, defaults to 23:59 (end of day).
     *
     * @param dateTimeStr the date-time string to parse
     * @return parsed LocalDateTime
     * @throws IllegalArgumentException if the format is not supported
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        // Try to parse with time first
        for (String format : DATE_TIME_FORMATS) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
                return LocalDateTime.parse(dateTimeStr, fmt);
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }
        
        // Try to parse date only
        for (String format : DATE_ONLY_FORMATS) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
                LocalDate date = LocalDate.parse(dateTimeStr, fmt);
                // Default to end of day (23:59) if no time specified
                return date.atTime(23, 59);
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }
        
        throw new IllegalArgumentException("Unsupported date format: " + dateTimeStr);
    }


    /**
     * Encodes the task into the custom save-file syntax:
     * <pre>D | &lt;0 or 1&gt; | &lt;description&gt; | &lt;d/M/yyyy&nbsp;HHmm&gt;</pre>
     *
     * @return one-line representation ready to be written to disk
     */
    @Override
    public String toSaveFileFormat() {
        String data = "D | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(SAVE_DATE_FORMAT);
        data += this.description + " | " + this.by.format(fmt);
        return data;
    }

    /**
     * Human-friendly view of the task, e.g.
     * <code>[D]read book(by: Sep&nbsp;2&nbsp;2025&nbsp;11&nbsp;PM)</code>
     * If time is 23:59, displays date only.
     *
     * @return pretty-printed string for CLI display
     */
    @Override
    public String toString() {
        String formattedDate;
        // If time is 23:59 (default end of day), show date only
        if (this.by.getHour() == 23 && this.by.getMinute() == 59) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(OUTPUT_DATE_ONLY_FORMAT);
            formattedDate = this.by.format(fmt);
        } else {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
            formattedDate = this.by.format(fmt);
        }
        return "[D]" + super.toString() + "(by: " + formattedDate + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.by; // Use the deadline for sorting
    }

    @Override
    public int compareTo(Task other) {
        LocalDateTime otherDateTime = other.getDateTime();
        
        // If the other task has no date/time, this deadline comes before it
        if (otherDateTime == null) {
            return -1;
        }
        
        // Both tasks have date/time - compare by date/time
        return this.by.compareTo(otherDateTime);
    }
}

