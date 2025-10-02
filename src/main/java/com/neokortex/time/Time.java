package com.neokortex.time;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.neokortex.exceptions.InvalidDateSerializationException;
import com.neokortex.exceptions.InvalidTimeSerializationException;
import com.neokortex.time.dateparser.CompositeDateParser;
import com.neokortex.time.dateparser.DateParser;
import com.neokortex.time.timeparser.CompositeTimeParser;
import com.neokortex.time.timeparser.TimeParser;

/**
 * Represents an all-purpose time class. It has configurable display formatting.
 * <p>
 * The {@code Time} class provides a convenient way to handle dates and times. It supports parsing,
 * serialization, and different display formats.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li>Dates and/or time parsing</li>
 *   <li>Configurable display formatting</li>
 *   <li>Serialization and deserialization support</li>
 * </ul>
 *
 * <p><b>Display Mode:</b></p>
 * <ul>
 *   <li>{@link DisplayMode#FULL}: Shows both date and time</li>
 *   <li>{@link DisplayMode#DATE_ONLY}: Shows only the date portion</li>
 *   <li>{@link DisplayMode#TIME_ONLY}: Shows only the time portion</li>
 * </ul>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * displayTypeOrdinal,hour:minute:second,day/month/year
 * </pre>
 *
 * <p><b>Credit: documentation was written based on suggestions and recommendations from generative AI</b></p>
 *
 * @see LocalDate
 * @see LocalTime
 * @see LocalDateTime
 */
public class Time {
    /**
     * Local enum representing the 3 supported DisplayTypes.
     */
    private enum DisplayMode {
        FULL,
        TIME_ONLY,
        DATE_ONLY
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mma");

    private final LocalDate date;
    private final LocalTime time;
    private DisplayMode displayMode;

    /**
     * Constructs a Time object from a LocalDateTime.
     * The display type is set to {@link DisplayMode#FULL}.
     *
     * @param time the LocalDateTime to be converted.
     */
    public Time(LocalDateTime time) {
        this.date = time.toLocalDate();
        this.time = time.toLocalTime();
        this.displayMode = DisplayMode.FULL;
    }

    /**
     * Constructs a Time object from a LocalDate.
     * The display type is set to {@link DisplayMode#DATE_ONLY}.
     *
     * @param date the date to be converted.
     */
    public Time(LocalDate date) {
        this.date = date;
        this.time = LocalTime.of(23, 59, 0);
        this.displayMode = DisplayMode.DATE_ONLY;
    }

    /**
     * Constructs a Time object from a LocalDate.
     * The display type is set to {@link DisplayMode#TIME_ONLY}.
     *
     * @param time the time to be converted.
     */
    public Time(LocalTime time) {
        this.date = LocalDate.now();
        this.time = time;
        this.displayMode = DisplayMode.TIME_ONLY;
    }

    /**
     * Private constructor for internal use with full control over all components.
     *
     * @param date the date component.
     * @param time the time component.
     * @param displayMode the display format to use.
     */
    private Time(LocalDate date, LocalTime time, DisplayMode displayMode) {
        this.date = date;
        this.time = time;
        this.displayMode = displayMode;
    }

    public void setDisplayTypeToFull() {
        this.displayMode = DisplayMode.FULL;
    }

    public void setDisplayTypeToTimeOnly() {
        this.displayMode = DisplayMode.TIME_ONLY;
    }

    public void setDisplayTypeToDateOnly() {
        this.displayMode = DisplayMode.DATE_ONLY;
    }

    /**
     * Extracts the date and/or time from a string input. It is capable or processing
     * (relatively) natural language.
     *
     * <p>
     * This method uses a composite parser to extract both date and time components
     * from the input. If no date is found, the current date is used. If no time is found,
     * 23:59pm is used as default.
     * </p>
     * <p></p>
     * Returns a null if neither date nor time can be parsed from the input.
     * </p>
     *
     * <p><b>Credit: documentation was written based on suggestions and recommendations from generative AI</b></p>
     *
     * @param input the string to parse for date and time information.
     * @return an {@link Optional} containing a {@code Time} object representing the parsed
     *         date and time, or an empty {@code Optional} if the input string does not contain a
     *         parsable time.
     *
     * @see CompositeDateParser
     * @see CompositeTimeParser
     */
    public static Time parseTime(String input) {
        CompositeDateParser dateParser = DateParser.createDefaultParser();
        List<LocalDate> possibleDates = dateParser.processString(input);
        CompositeTimeParser timeParser = TimeParser.createDefaultParser();
        List<LocalTime> possibleTimes = timeParser.processString(input);

        boolean hasDate = false;
        boolean hasTime = false;
        LocalDate dateFound = LocalDate.now();
        LocalTime timeFound = LocalTime.of(23, 59);
        if (!possibleDates.isEmpty()) {
            dateFound = possibleDates.get(possibleDates.size() - 1);
            hasDate = true;
        }

        if (!possibleTimes.isEmpty()) {
            timeFound = possibleTimes.get(possibleTimes.size() - 1);
            hasTime = true;
        }

        if (!hasDate && !hasTime) {
            return null;
        }

        return new Time(dateFound,
                        timeFound,
                        getDisplayMode(hasDate, hasTime));
    }

    /**
     * Serializes the {@code Time} object to a string representation for storage.
     * <p>
     * Format: {@code displayTypeOrdinal,hour:minute:second,day/month/year}
     * </p>
     *
     * @return a string representation of the Time object
     *
     * @see #deserialize(String)
     */
    public String serialize() {
        return String.format("%d,%d:%d:%d,%d/%d/%d", this.displayMode.ordinal(),
                this.time.getHour(), this.time.getMinute(), this.time.getSecond(),
                this.date.getDayOfMonth(), this.date.getMonthValue(), this.date.getYear());
    }

    /**
     * Deserializes a Time object from its string representation.
     * <p>
     * This method is the inverse of {@link #serialize()} and as such expects the same format.
     * </p>
     *
     * @param input the serialized string representation of a Time object
     * @return a reconstructed Time object
     * @throws InvalidTimeSerializationException if the time component cannot be parsed
     * @throws InvalidDateSerializationException if the date component cannot be parsed
     * @throws IllegalArgumentException if the display type ordinal is invalid
     * @throws NumberFormatException if any numeric component cannot be parsed
     *
     * @see #serialize()
     * @see InvalidTimeSerializationException
     * @see InvalidDateSerializationException
     */
    public static Time deserialize(String input) {
        String[] parts = input.split(",");

        DisplayMode displayMode = intToDisplayType(Integer.parseInt(parts[0]));
        LocalTime time = deserializeTime(parts[1]);
        LocalDate date = deserializeDate(parts[2]);

        return new Time(date, time, displayMode);
    }

    private static DisplayMode getDisplayMode(boolean hasDate, boolean hasTime) {
        if (hasDate && hasTime) {
            return DisplayMode.FULL;
        }
        if (hasDate) {
            return DisplayMode.DATE_ONLY;
        }
        if (hasTime) {
            return DisplayMode.TIME_ONLY;
        }
        throw new IllegalArgumentException("Cannot simultaneously have no date and time!");

    }

    private static DisplayMode intToDisplayType(int ordinal) {
        if (ordinal < 0 || ordinal >= DisplayMode.values().length) {
            throw new IllegalArgumentException("Invalid ordinal: " + ordinal);
        }

        return DisplayMode.values()[ordinal];
    }

    private static LocalTime deserializeTime(String input) {
        String[] parts = input.split(":");
        if (parts.length != 3) {
            throw new InvalidTimeSerializationException("Incorrect time format!");
        }

        int hour;
        int minute;
        int second;

        try {
            hour = Integer.parseInt(parts[0]);
            minute = Integer.parseInt(parts[1]);
            second = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidTimeSerializationException(
                    "Time string contains non-integers!");
        }

        LocalTime time;
        try {
            time = LocalTime.of(hour, minute, second);
        } catch (DateTimeException e) {
            throw new InvalidTimeSerializationException(
                    "Time string given is not a valid time!");
        }

        return time;
    }

    private static LocalDate deserializeDate(String input) {
        String[] parts = input.split("/");
        if (parts.length != 3) {
            throw new InvalidDateSerializationException(
                    "Incorrect date format!");
        }

        int day;
        int month;
        int year;

        try {
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidDateSerializationException(
                    "Time string contains non-integers!");
        }

        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidDateSerializationException("Time string given is not a valid time!");
        }

        return date;
    }

    /**
     * Returns the string representation of this Time object according
     * to the display mode.
     * <p>
     * Format:
     * </p>
     * <ul>
     *   <li>{@link DisplayMode#FULL}: "HHMM dd Month yyyy"</li>
     *   <li>{@link DisplayMode#TIME_ONLY}: "HHMM"</li>
     *   <li>{@link DisplayMode#DATE_ONLY}: "dd Month yyyy"</li>
     * </ul>
     *
     * @return a formatted string representation of this Time object
     */
    @Override
    public String toString() {
        String dateString = DATE_FORMATTER.format(date);
        String timeString = TIME_FORMATTER.format(time);

        return switch (displayMode) {
        case FULL -> timeString + " " + dateString;
        case TIME_ONLY -> timeString;
        case DATE_ONLY -> dateString;
        default -> "Not a valid time";
        };
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof Time time) {
            return this.date.equals(time.date)
                    && this.time.equals(time.time);
        }

        return false;
    }
}
