package lenny.logic.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Utility class for parsing and formatting date/time values
 */
public class DateFormatter {
    private DateFormatter() {}

    /**
     * Formats date and time given by the user into a standard format
     *
     * @param input User given date and time.
     * @return A string representation of the date and time.
     */
    public static String format(String input) {
        String s = input.trim();

        // Case 1: d/M/yyyy HHmm  e.g. 2/12/2019 1800
        if (s.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}")) {
            LocalDateTime dt = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return toOrdinalDateTime(dt);
        }

        // Case 2: yyyy-MM-dd
        if (s.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate d = LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
            return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        }

        // Case 3: MM-dd-yyyy
        if (s.matches("\\d{2}-\\d{2}-\\d{4}")) {
            LocalDate d = LocalDate.parse(s, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        }

        // Case 4a: yyyy-MM-dd HHmm
        if (s.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
            LocalDateTime dt = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return toOrdinalDateTime(dt);
        }

        // Case 4b: MM-dd-yyyy HHmm
        if (s.matches("\\d{2}-\\d{2}-\\d{4} \\d{4}")) {
            LocalDateTime dt = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm"));
            return toOrdinalDateTime(dt);
        }

        // Case 5: d/M/yyyy (date only, with slashes)
        if (s.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            LocalDate d = LocalDate.parse(s, DateTimeFormatter.ofPattern("d/M/yyyy"));
            return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        }

        // Fallback: if nothing matched, return as-is.
        return s;
    }

    /**
     * A helper function to produce an ordinal date and time given any DateTime object.
     *
     * @param dt A LocalDateTime object.
     * @return A string representation of the ordinal date and time.
     */
    private static String toOrdinalDateTime(LocalDateTime dt) {
        int day = dt.getDayOfMonth();
        String dayWithOrd = day + ordinal(day);
        String month = dt.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = dt.getYear();

        int h24 = dt.getHour();
        int m = dt.getMinute();
        int h12 = (h24 % 12 == 0) ? 12 : (h24 % 12);
        String ampm = (h24 < 12) ? "am" : "pm";
        String time = (m == 0) ? (h12 + ampm) : (h12 + ":" + String.format("%02d", m) + ampm);

        return String.format("%s of %s %d, %s", dayWithOrd, month, year, time);
    }

    /**
     * Given an integer,produces an ordinal for it.
     *
     * @param d An integer
     * @return A string representation of the ordinal of an integer.
     */
    private static String ordinal(int d) {
        int v = d % 100;
        if (v >= 11 && v <= 13) {
            return "th";
        }
        switch (d % 10) {
        case 1: return "st";
        case 2: return "nd";
        case 3: return "rd";
        default: return "th";
        }
    }
}
