package time;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

import exceptions.InvalidTimeInputException;

/**
 * Represents time with date month year and hour minute precision
 * Provides formatting for display and storage
 */
public class Time {
    //format as eg: 03-12-2025 1800
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HHmm");
    //format as eg: 3 Dec 2025 18:00
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern(
            "d MMM yyyy HH:mm");
    private LocalDateTime dateTime;

    /**
     * Constructor of Time
     * Initialises dateTime
     */
    public Time(String time) {
        try {
            this.dateTime = LocalDateTime.parse(time, FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeInputException();
        }
    }

    /**
     * Returns string of the time
     * Used for printing out time to be stored
     */
    @Override
    public String toString() {
        return dateTime.format(FORMAT);
    }

    /**
     * Returns string of the time
     * Used for printing out time to be shown
     */
    public String toPrint() {
        return dateTime.format(PRINTFORMAT);
    }
}
