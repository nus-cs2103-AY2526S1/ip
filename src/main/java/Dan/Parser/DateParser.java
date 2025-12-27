package Dan.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateParser {

    // Define input formatters for different date/time patterns
    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("d/M/yyyy"),          // 2/12/2019
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),        // 02/12/2019
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),        // 2019-12-02
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),        // 2019/12/02
            DateTimeFormatter.ofPattern("MMM dd yyyy")        // Dec 02 2019
    );

    // Output formatter for display
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parse a date string and return a LocalDate object
     * @param dateStr The input string like "2/12/2019"
     * @return LocalDate object or null if parsing fails
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        String cleanInput = dateStr.trim();

        // Try each formatter until one works
        assert  !DATE_FORMATTERS.isEmpty();
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDate temp = LocalDate.parse(cleanInput, formatter);
                String outputDateString = temp.format(OUTPUT_DATE_FORMATTER);
                return LocalDate.parse(outputDateString, OUTPUT_DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                // Continue to next formatter
            }
        }

        return null; // No formatter worked
    }
}
