package thopz;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task
 * A deadline task has a description and a start date and end time
 */

public class Events extends Task {

    public static final DateTimeFormatter[] INPUT_FORMATS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/dd/MM HH:mm"),
    };
    public static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private String start;
    private String end;

    /**
     * Constructs a command to add deadline with the specified description and due date.
     *
     * @param desc  the description of the task
     * @param start the string representation of the due date/time
     * @param end the string representation of the end date/time
     *
     */

    public Events(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
        this.start = parseDate(start.trim());
        if (this.start == null) {
            throw new IllegalArgumentException("give in date and time format");
        }
        this.end = parseDate(end.trim());
        if (this.end == null) {
            throw new IllegalArgumentException("give in date and time format");
        }
    }

    /**
     * Parses the given string into list of formatted date/time using the accepted input formats.
     * If parsing fails,returns the original string.
     *
     * @param dateString  string to parse
     * @return  formatted date/time string
     */

    public String parseDate(String dateString) {
        for (DateTimeFormatter formatter : INPUT_FORMATS) {
            try {
                return LocalDateTime.parse(dateString, formatter).format(OUTPUT);
            } catch (DateTimeException e) {
                // Try next format
            }
        }
        return dateString;
    }

    /**
     * Saving format for storage class
     */

    @Override
    public String saveformat() {
        return "E / " + (getTaskStatus() ? "1" : "0") + " / " + getTasks()
                + " / " + start + " / " + end;
    }

    /**
     * String representation of the events class
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (Starts on : " + start + ", ends by : " + end + ")";
    }

}
