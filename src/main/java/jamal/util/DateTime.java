package jamal.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Datetime logic for patterns and formatting
 */
public class DateTime {

    /**
     * Formats date string into readable format i.e "Jan 12 2019, 8.30pm"
     *
     * @param dateTime LocalDateTime of date string
     * @return readable date string
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
    }

    /**
     * Detects if current date is before input date
     *
     * @param startDate LocalDateTime of date string
     * @return true if current date is before input date, false otherwise
     */
    public static boolean isUpcoming(LocalDateTime startDate) {
        return LocalDateTime.now().isBefore(startDate);
    }

    /**
     * Detects if current date is after input date
     *
     * @param endDate LocalDateTime of date string
     * @return true if current date is after input date, false otherwise
     */
    public static boolean isOverdue(LocalDateTime endDate) {
        return LocalDateTime.now().isAfter(endDate);
    }

    /**
     * Detects if current date is after start date and before end date
     *
     * @param startDate LocalDateTime of date string for start date
     * @param endDate LocalDatetime of date string for end date
     * @return true if current date is after start date and before end date, false otherwise
     */
    public static boolean isOngoing(LocalDateTime startDate, LocalDateTime endDate) {
        return LocalDateTime.now().isAfter(startDate) && LocalDateTime.now().isBefore(endDate);
    }

    /**
     * Detects if current date is before input date
     *
     * @param endDate LocalDateTime of date string
     * @return true if current date is before input date, false otherwise
     */
    public static boolean isOngoing(LocalDateTime endDate) {
        return LocalDateTime.now().isBefore(endDate);
    }
}
