package ui;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Support reading some specific string as date and time
 */
public class DateTimeService {
    private LocalDate currentdate = LocalDate.now();
    private static final List<DateTimeFormatter> SUPPORTED_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),

            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")
    );

    private static final DateTimeFormatter OUTPUT_DATE = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    private static final DateTimeFormatter OUTPUT_DATETIME = DateTimeFormatter.ofPattern("hh:mm, MMM dd, yyyy");

    /**
     * Read input String as date and time and print in different format
     *
     * @param input  The string of date and time to be read
     * @return date and time red
     */
    public static String outputDateTime(String input) {
        String trimmed = input.trim();

        for (DateTimeFormatter formatter : SUPPORTED_FORMATTERS) {

            try {
                boolean hasTime = formatter.toString().contains("HHmm");

                if (hasTime) {
                    LocalDateTime dateTime = LocalDateTime.parse(trimmed, formatter);
                    return dateTime.format(OUTPUT_DATETIME);
                } else {
                    LocalDate date = LocalDate.parse(trimmed, formatter);
                    return date.format(OUTPUT_DATE);
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return input;
    }

}
