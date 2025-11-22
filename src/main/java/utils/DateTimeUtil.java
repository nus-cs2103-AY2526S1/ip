package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import exceptions.ApunableException;

/**
 * A util that help to parse from {@code String} into {@code LocalDateTime}. 
 */
public class DateTimeUtil {
    /**
     * Converts a 2-digits years(e.g. "25") into a 4-digits year(e.g. "2025"). 
     * 
     * @param yearStr the 2-digits year string. 
     * @return the converted 4-digits year string. 
     */
    private static int toFourDigitsYear(String yearStr) {
        if (yearStr.length() == 2) {
            int year = Integer.parseInt(yearStr);
            int century = (LocalDateTime.now().getYear() - year) / 100;

            return century * 100 + year;
        }

        return Integer.parseInt(yearStr);
    }

    /**
     * Parses {@code dateStr} into a {@code LocalDate} object, used by the {@code tryParse} method. 
     * 
     * @param dateStr date string of format: {@code dd-MM-yyyy} or {@code yyyy-MM-dd}(or slash{@code /} separated).
     * @return a {@code LocalDate} object. 
     */
    public static LocalDate parseDate(String dateStr) {
        String[] dayMonthYear = dateStr.split("[/-]");

        int day;
        int month;
        int year;

        if (dayMonthYear[0].length() == 4 || dayMonthYear[2].length() == 1) {
            day = Integer.parseInt(dayMonthYear[2]);
            month = Integer.parseInt(dayMonthYear[1]);
            year = toFourDigitsYear(dayMonthYear[0]);
        } else {
            day = Integer.parseInt(dayMonthYear[0]);
            month = Integer.parseInt(dayMonthYear[1]);
            year = toFourDigitsYear(dayMonthYear[2]);
        }

        return LocalDate.of(year, month, day);
    }

    /**
     * Parses {@code timeStr} into a {@code LocalTime} object, used by the {@code tryParse} method. 
     * 
     * @param timeStr time string of format {@code MM:ss} or {@code MMss}.
     * @return a {@code LocalTime} object. 
     */
    private static LocalTime parseTime(String timeStr) {
        int hour;
        int minute;

        String[] hourMinute = timeStr.split(":");

        if (hourMinute.length == 1) {
            hour = Integer.parseInt(timeStr.substring(0, 2));
            minute = Integer.parseInt(timeStr.substring(2, 4));
        } else {
            hour = Integer.parseInt(hourMinute[0]);
            minute = Integer.parseInt(hourMinute[1]);
        }

        return LocalTime.of(hour, minute);
    }

    /**
     * Parses {@code dateTimeStr} into a {@code LocalDateTime} object, 
     * as {@code LocalDateTime.parse} handle too little cases.
     * 
     * @param dateTimeStr datetime string of format {@code dd-MM-yyyy MM:ss} or other format accepted by
     * {@code parseDate} and {@code parseTime}.
     * @return a {@code LocalDateTime} object.
     */
    public static LocalDateTime tryParse(String dateTimeStr) throws ApunableException {
        try {
            dateTimeStr = dateTimeStr.trim();

            String dateStr = dateTimeStr;
            String timeStr = "00:00";

            if (dateTimeStr.split("[ T]").length > 1) {
                dateStr = dateTimeStr.split("[ T]")[0];
                timeStr = dateTimeStr.split("[ T]")[1];
            }

            LocalDate date = parseDate(dateStr);
            LocalTime time = parseTime(timeStr);

            return LocalDateTime.of(date, time);
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException("Invalid date time format");
        } catch (NumberFormatException e) {
            throw new ApunableException("Invalid date time format");
        } catch (Exception e) {
            throw new ApunableException(e.getMessage());
        }
    }
}
