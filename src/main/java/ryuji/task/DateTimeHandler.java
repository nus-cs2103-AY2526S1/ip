package ryuji.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Utility class for handling various date and time formats.
 * <p>This class provides methods to parse, detect, and format date-time strings in different formats.</p>
 * <p>The supported date-time formats include both date and time (with time being optional) and different local
 * and international date-time representations. Currently, it supports date formats such as "yyyy-MM-dd" and "yyyy/MM/dd".</p>
 */
public class DateTimeHandler {

    /** Input formatter patterns to parse date and optional time (time is optional). */
    private static final List<String> patterns = List.of(
            "yyyy-MM-dd",
            "yyyy/MM/dd"
    );

    /** Output formatter to display the date in a user-friendly format. */
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Parses a given date-time string into a {@link LocalDate} object.
     * <p>This method attempts to detect the format of the provided string using predefined patterns.
     * If a valid format is detected, the string is parsed into a {@link LocalDate} object. If no valid
     * format is found, the method returns null.</p>
     * <p>Currently supported formats include "yyyy-MM-dd" and "yyyy/MM/dd".</p>
     *
     * @param dateString the date-time string to parse
     * @return the parsed {@link LocalDate} object, or null if no valid format was found
     */
    public static LocalDate getDate(String dateString) {
        LocalDate formattedDate;
        try {
            formattedDate = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            formattedDate = null;
        }
        System.out.println(formattedDate);  // Debug output, can be removed for production.
        return formattedDate;
    }

    /**
     * Formats a given {@link LocalDate} object into a user-friendly string representation.
     * <p>This method uses the predefined output formatter to convert the {@link LocalDate} into a string
     * formatted as "MMM d yyyy" (e.g., "Sep 15 2025").</p>
     *
     * @param parsedDate the {@link LocalDate} object to format
     * @return the formatted date string, in the form "MMM d yyyy" (e.g., "Sep 15 2025")
     */
    public static String formatDetectedDate(LocalDate parsedDate) {
        return parsedDate.format(outputFormatter);
    }
}
