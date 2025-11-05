package uxie.interfaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import uxie.exceptions.UxieSyntaxException;

/**
 * Parses Strings to DateTime.
 *
 * @author junyan-k
 */
public class DateTimeParse {

    /** DateTimeFormatter used to format user-inputted date/times. */
    private static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /** DateTimeFormatter used to format date/times printed by UI. */
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm");

    /** DateTimeFormatter used to format date/times in stored locally. */
    private static final DateTimeFormatter STORAGE_DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Parses input String to LocalDateTime object.
     * Uses {@link #INPUT_DATETIME_FORMAT}
     *
     * @throws UxieSyntaxException incorrect format of input string
     */
    public static LocalDateTime parseInput(String dateTimeString) throws UxieSyntaxException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UxieSyntaxException(
                    String.format("Your date/time format is incorrect. (%s)", INPUT_DATETIME_FORMAT));
        }
    }

    /**
     * Returns LocalDateTime object as String in output format.
     * Uses {@link #OUTPUT_DATETIME_FORMAT}
     */
    public static String parseOutput(LocalDateTime dateTime) {
        assert dateTime != null : "DateTimeParse#parseOutput: dateTime is null";
        return OUTPUT_DATETIME_FORMAT.format(dateTime);
    }

    /**
     * Parses input String to LocalDateTime object for local storage.
     * Uses {@link #STORAGE_DATETIME_FORMAT}
     *
     * @return LocalDateTime produced from parsing stored data
     * @throws UxieSyntaxException incorrect format of input string
     */
    public static LocalDateTime parseStorageRead(String dateTimeString) throws UxieSyntaxException {
        try {
            return LocalDateTime.parse(dateTimeString, STORAGE_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UxieSyntaxException(
                    String.format("Your date/time format is incorrect. (%s)", STORAGE_DATETIME_FORMAT));
        }
    }

    /**
     * Returns DateTime object as String in output format for local storage.
     * Uses {@link #STORAGE_DATETIME_FORMAT}
     */
    public static String parseStorageWrite(LocalDateTime dateTime) {
        assert dateTime != null : "DateTimeParse#parseStorageWrite: dateTime is null";
        return STORAGE_DATETIME_FORMAT.format(dateTime);
    }

}
