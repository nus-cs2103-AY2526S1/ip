package dyke.parse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Provides methods to parse datetime from recognized formats
 */
public class DateTimeParser {

    private static final DateTimeFormatter DATE_ONLY =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_HH =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    private static final DateTimeFormatter DATE_HH_MM =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_HH_MM_SS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final LocalDateTime today = LocalDateTime.now();

    private final LocalDateTime date;
    private final DateTimeFormatter format;


    private DateTimeParser(LocalDateTime date, DateTimeFormatter fmt) throws DykeException {
        assert date != null && fmt != null;
        if (date.isBefore(today)) {
            throw new DykeException("Dude, are we travelling to the past?");
        }
        this.date = date;
        this.format = fmt;
    }

    /**
     * Helper method to Parse Datetime based on recognized formats.
     * @param input String datetime input from User.
     * @return DateTimeParser object for internal tracking.
     * @throws DykeException If datetime input is not in a recognized format.
     */
    public static DateTimeParser parse(String input) throws DykeException {
        if (isDateOnly(input)) {
            return new DateTimeParser(LocalDate.parse(input, DATE_ONLY).atStartOfDay(), DATE_ONLY);
        }

        DateTimeFormatter[] dateTimeFormatters = new DateTimeFormatter[] {
            DATE_ONLY, DATE_HH, DATE_HH_MM, DATE_HH_MM_SS
        };

        for (DateTimeFormatter fmt : dateTimeFormatters) {
            if (isDateWithTime(input, fmt)) {
                return new DateTimeParser(LocalDateTime.parse(input, fmt), fmt);
            }
        }
        throw new DykeException("Invalid datetime: " + input);
    }

    /**
     * Checks if Date given by User matches the recognized {@code Date} format
     * @param input Date from User
     * @return {@code boolean}
     */
    private static boolean isDateOnly(String input) {
        try {
            LocalDate.parse(input, DATE_ONLY);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if Date given by User matches recognized {@code DateTime} formats
     * @param input Date from User
     * @param fmt {@code DateTimeFormatter} formats recognized by Dyke
     * @return {@code boolean}
     */
    private static boolean isDateWithTime(String input, DateTimeFormatter fmt) {
        try {
            LocalDateTime.parse(input, fmt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long timeDifference(DateTimeParser end) {
        return ChronoUnit.DAYS.between(this.date, end.date);
    }

    public boolean isConsistentFormat(DateTimeParser end) {
        return this.format == end.format;
    }

    /**
     * @return Date-time in an appropriate format.
     */
    public String reString() {
        return format.format(date);
    }
}
