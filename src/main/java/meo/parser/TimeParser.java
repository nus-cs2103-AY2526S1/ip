package meo.parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses time text input by user.
 */
public class TimeParser {
    protected static final DateTimeFormatter[] FORMATTERS = {
        DateTimeFormatter.ofPattern("d/M/yyyy"),
        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        DateTimeFormatter.ofPattern("d/M/yyyy ha")
    };

    /**
     * Parses the time input by user and return a formatted string.
     *
     * @param text Time input by user.
     * @return Formatted string of the time.
     */
    public static String parseTime(String text) {
        LocalDateTime time = null;
        text = text.toLowerCase();
        // Try all formats until it is correct
        for (DateTimeFormatter format : FORMATTERS) {
            try {
                time = LocalDateTime.parse(text, format);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
        }

        // If no format matches
        if (time == null) {
            return "NA";
        }

        String timeFormatted = time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return timeFormatted;
    }
}
