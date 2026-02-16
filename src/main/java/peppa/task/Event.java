package peppa.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task that spans a time range rather than a single deadline.
 * Stores both start and end {@link LocalDateTime} values.
 */
public class Event extends Task {
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
    
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates a new event.
     *
     * @param description free-text details of the event
     * @param from start date-time in various supported formats
     *             With time: d/M/yyyy HHmm, dd/MM/yyyy HH:mm, yyyy-MM-dd HH:mm, etc.
     *             Date only: d/M/yyyy, dd/MM/yyyy, yyyy-MM-dd, etc.
     *             If time is not specified, defaults to 00:00 (start of day)
     * @param to   end date-time in the same format
     *             If time is not specified, defaults to 23:59 (end of day)
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from.trim(), true);  // true = start time (00:00 default)
        this.to = parseDateTime(to.trim(), false);     // false = end time (23:59 default)
    }

    /**
     * Parses a date-time string in various supported formats.
     * If time is not specified, defaults based on isStartTime parameter.
     *
     * @param dateTimeStr the date-time string to parse
     * @param isStartTime if true, defaults to 00:00; if false, defaults to 23:59
     * @return parsed LocalDateTime
     * @throws IllegalArgumentException if the format is not supported
     */
    private LocalDateTime parseDateTime(String dateTimeStr, boolean isStartTime) {
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
                // Default to start of day (00:00) for start time, end of day (23:59) for end time
                if (isStartTime) {
                    return date.atTime(0, 0);
                } else {
                    return date.atTime(23, 59);
                }
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }
        
        throw new IllegalArgumentException("Unsupported date format: " + dateTimeStr);
    }

    /**
     * Serialises the event using the custom save-file layout:
     * <pre>E | &lt;0 or 1&gt; | &lt;description&gt; | &lt;from&nbsp;d/M/yyyy&nbsp;HHmm&gt; | &lt;to&nbsp;d/M/yyyy&nbsp;HHmm&gt;</pre>
     *
     * @return one-line representation for disk storage
     */
    @Override
    public String toSaveFileFormat() {
        String data = "E | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(SAVE_DATE_FORMAT);
        data += this.description + " | " + this.from.format(fmt) + " | " + this.to.format(fmt);
        return data;
    }

    /**
     * Pretty-prints the task for CLI display, e.g.
     * <code>[E]project meeting (from: Sep&nbsp;12&nbsp;2025&nbsp;1 PM&nbsp;to:&nbsp;Sep&nbsp;12&nbsp;2025&nbsp;3 PM)</code>
     * If times are default (00:00 and 23:59), displays dates only.
     *
     * @return human-friendly description containing the date range
     */
    @Override
    public String toString() {
        String formattedFrom, formattedTo;
        
        // Check if from time is 00:00 (default start time)
        if (this.from.getHour() == 0 && this.from.getMinute() == 0) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(OUTPUT_DATE_ONLY_FORMAT);
            formattedFrom = this.from.format(fmt);
        } else {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
            formattedFrom = this.from.format(fmt);
        }
        
        // Check if to time is 23:59 (default end time)
        if (this.to.getHour() == 23 && this.to.getMinute() == 59) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(OUTPUT_DATE_ONLY_FORMAT);
            formattedTo = this.to.format(fmt);
        } else {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
            formattedTo = this.to.format(fmt);
        }
        
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.from; // Use the start time for sorting
    }

    @Override
    public int compareTo(Task other) {
        LocalDateTime otherDateTime = other.getDateTime();
        
        // If the other task has no date/time, this event comes before it
        if (otherDateTime == null) {
            return -1;
        }
        
        // Both tasks have date/time - compare by date/time
        return this.from.compareTo(otherDateTime);
    }
}
