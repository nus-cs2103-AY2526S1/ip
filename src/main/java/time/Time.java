package time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exception.TimeFormatException;

/**
 * Represents a date and time.
 */
public class Time {
    private static DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private LocalDateTime dateTime;

    private Time(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Parses a date/time string in the format "dd/MM/yyyy HH:mm" and returns a Time object.
     *
     * @param dateTimeStr The date/time string to parse.
     * @return A Time object representing the parsed date and time.
     * @throws TimeFormatException If the input string is not in the correct format.
     */
    public static Time parseDateTime(String dateTimeStr) throws TimeFormatException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, parseFormatter);
            return new Time(dateTime);
        } catch (Exception e) {
            throw new TimeFormatException("Invalid date/time format. Please use 'dd/MM/yyyy HH:mm' format.");
        }
    }

    /**
     * Returns the date and time as a formatted string in the format "dd MMM yyyy HH:mm".
     *
     * @return The formatted date and time string.
     */
    public String getDateTime() {
        return this.dateTime.format(printFormatter);
    }
}
