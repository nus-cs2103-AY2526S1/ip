package stella;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for converting date (or date with timing) to a more
 * human-readable format
 */
public class TimeConverter {

    /**
     * Convert date (with timing) to a more human-readable format.
     * If input does not follow standard timing (e.g. 1-1-25-1200 or next Monday 2PM),
     * function would return the same output (e.g. 1-1-25-1200  or next Monday 2PM).
     * If invalid date (e.g. 11-12-2013-2500), function return "Unknown Timing"
     *
     * @param rawFormat Input that follows dd-mm-yyyy-tttt format (e.g. 01-12-2025-1245)
     * @return Date (with timing) in HH:mm, dd MMMM yyyy format (e.g. 12:45, 01 December 2025)
     */
    public static String convertDateWithTime(String rawFormat) {
        try {
            int day = Integer.parseInt(rawFormat.substring(0, 2));
            int month = Integer.parseInt(rawFormat.substring(3, 5));
            int year = Integer.parseInt(rawFormat.substring(6, 10));
            int hour = Integer.parseInt(rawFormat.substring(11, 13));
            int min = Integer.parseInt(rawFormat.substring(13, 15));

            LocalDateTime input = LocalDateTime.of(year, month, day, hour, min);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd MMMM yyyy");
            return input.format(formatter);
        } catch (NumberFormatException e) {
            return rawFormat;
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
        return "Unknown Timing";
    }
    /**
     * Convert date to a more human-readable format.
     * If input does not follow standard timing (e.g. 1-1-25 or next Monday),
     * function would return the same output (e.g. 1-1-25 or next Monday).
     * If invalid date (e.g. 35-12-2013), function return "Unknown Timing"
     *
     * @param rawFormat Input that follows dd-mm-yyyy format (e.g. 01-12-2025)
     * @return Date in dd MMMM yyyy format (e.g. 01 December 2025)
     */
    public static String convertDate(String rawFormat) {
        try {
            int day = Integer.parseInt(rawFormat.substring(0, 2));
            int month = Integer.parseInt(rawFormat.substring(3, 5));
            int year = Integer.parseInt(rawFormat.substring(6, 10));

            LocalDate input = LocalDate.of(year, month, day);
            return input.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        } catch (NumberFormatException e) {
            return rawFormat;
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
        return "Unknown Timing";
    }
}
