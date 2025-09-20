package stella;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Converts date (or date with timing) to a more human-readable format.
 */
public class TimeConverter {
    public static final String VALID_DATE = "dd-mm-yyyy";
    public static final String VALID_DATE_WITH_TIME = "dd-mm-yyyy-tttt";

    private static final int INDEX_OF_FIRST_DASH = 2;
    private static final int INDEX_OF_SECOND_DASH = 5;
    private static final int INDEX_OF_THIRD_DASH = 10;
    private static final int START_INDEX_FOR_MIN = 13;

    /**
     * Converts date (with timing) to a more human-readable format.
     * If input does not follow standard timing (e.g. 1-1-25-1200 or next Monday 2PM),
     * function would return the same output (e.g. 1-1-25-1200  or next Monday 2PM).
     * If invalid date (e.g. 11-12-2013-2500), function return "Unknown Timing".
     *
     * @param rawFormat Input that follows dd-mm-yyyy-tttt format (e.g. 01-12-2025-1245).
     * @return Date (with timing) in HH:mm, dd MMMM yyyy format (e.g. 12:45, 01 December 2025).
     */
    public static String convertDateWithTime(String rawFormat) {
        try {
            assert rawFormat.length() == VALID_DATE_WITH_TIME.length();

            int day = Integer.parseInt(rawFormat.substring(0, INDEX_OF_FIRST_DASH));
            int month = Integer.parseInt(rawFormat.substring(INDEX_OF_FIRST_DASH + 1, INDEX_OF_SECOND_DASH));
            int year = Integer.parseInt(rawFormat.substring(INDEX_OF_SECOND_DASH + 1, INDEX_OF_THIRD_DASH));
            int hour = Integer.parseInt(rawFormat.substring(INDEX_OF_THIRD_DASH + 1, START_INDEX_FOR_MIN));
            int min = Integer.parseInt(rawFormat.substring(START_INDEX_FOR_MIN));

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
     * Converts date to a more human-readable format.
     * If input does not follow standard timing (e.g. 1-1-25 or next Monday),
     * function would return the same output (e.g. 1-1-25 or next Monday).
     * If invalid date (e.g. 35-12-2013), function return "Unknown Timing".
     *
     * @param rawFormat Input that follows dd-mm-yyyy format (e.g. 01-12-2025).
     * @return Date in dd MMMM yyyy format (e.g. 01 December 2025).
     */
    public static String convertDate(String rawFormat) {
        try {
            assert rawFormat.length() == VALID_DATE.length();

            int day = Integer.parseInt(rawFormat.substring(0, INDEX_OF_FIRST_DASH));
            int month = Integer.parseInt(rawFormat.substring(INDEX_OF_FIRST_DASH + 1, INDEX_OF_SECOND_DASH));
            int year = Integer.parseInt(rawFormat.substring(INDEX_OF_SECOND_DASH + 1));

            LocalDate input = LocalDate.of(year, month, day);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

            return input.format(formatter);
        } catch (NumberFormatException e) {
            return rawFormat;
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
        return "Unknown Timing";
    }
}
