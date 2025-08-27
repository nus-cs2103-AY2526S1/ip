package zell.util;

import zell.exception.ZellException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Wraps a LocalDate or LocalDateTime depending on what the user chooses.
 * This class basically allows a user to store either a LocalDate or LocalDateTime
 */
public class DateOrTime {
    /** The LocalDate object */
    private LocalDate date;

    /** The LocalDateTime object */
    private LocalDateTime dateTime;

    /**
     * Constructs the constructor for DateOrTime
     * <p>
     * It converts and stores the date/datetime in the DateOrTime class in the respective LocalDate or
     * LocalDateTime object.
     * </p>
     * @param dateOrTimeString The date or datetime string in the format {@code yyyy-MM-dd} or
     * {@code yyyy-MM-dd HH:mm} respectively.
     * @throws ZellException If the date or datetime string is invalid
     */
    public DateOrTime(String dateOrTimeString) throws ZellException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            this.dateTime = LocalDateTime.parse(dateOrTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                this.date = LocalDate.parse(dateOrTimeString, dateFormatter);
            } catch (DateTimeParseException de) {
                throw new ZellException("Date or DateTime should be in the respective formats " +
                        "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30");
            }
        }
    }

    /**
     * Converts the LocalDate or LocalDateTime object to a String to be stored for Storage
     * <p>
     * It converts and stores the date/datetime in the DateOrTime class in the respective LocalDate or
     * LocalDateTime object.
     * </p>
     * @return The string format of the current date or datetime
     */
    public String originalFormat() {
        String original = "";

        if (this.date != null) {
            original = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (this.dateTime != null) {
            original = this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        return original;
    }

    /**
     * Overrides the default toString
     * <p>
     * Displays the LocalDate or LocalDateTime in the format {@code MMM d yyyy} or {@code MMM d yyyy hh:mm a}
     * respectively.
     * </p>
     * @return The toString format of the DateOrTime object
     */
    @Override
    public String toString() {
        String formatted = "";

        if (this.date != null) {
            formatted = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else if (this.dateTime != null) {
            formatted = this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        }

        return formatted;
    }
}
