package sunday;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Utility for parsing and formatting dates and times consistently.
 */
public class DateTimeHelper {
    private static final List<DateTimeFormatter> DATEIN = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("uuuu-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/uuuu")
    );

    private static final List<DateTimeFormatter> DATETIMEIN = List.of(
            DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm"),
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("d/M/uuuu HH:mm")
    );

    public static final DateTimeFormatter DATE_SAVE = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter DATE_TIME_SAVE = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");

    public static final DateTimeFormatter DATE_PRINT = DateTimeFormatter.ofPattern("MMM dd uuuu");
    public static final DateTimeFormatter DATE_TIME_PRINT = DateTimeFormatter.ofPattern("MMM dd uuuu h:mma");

    /**
     * Parses a date string into a LocalDate using several supported formats.
     *
     * @param date date string
     * @return parsed LocalDate
     * @throws IllegalArgumentException if none match
     */
    public static LocalDate parseDate(String date) {
        for (var type : DATEIN) {
            try {
                return LocalDate.parse(date.trim(), type);
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Invalid date: " + date);
    }

    /**
     * Parses a date-time string into a LocalDateTime using several supported formats.
     *
     * @param dateTime date-time string
     * @return parsed LocalDateTime
     * @throws IllegalArgumentException if none match
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        for (var type : DATETIMEIN) {
            try {
                return LocalDateTime.parse(dateTime.trim(), type);
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Invalid date and time: " + dateTime);
    }
}