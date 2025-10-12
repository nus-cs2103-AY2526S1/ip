package com.oadultradeepfield.smartotter.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Utility class for parsing and formatting dates.
 */
public class DateParser {
    private static final DateTimeFormatter INPUT_WIH_TIME =
        DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
    private static final DateTimeFormatter INPUT_DATE_ONLY = DateTimeFormatter.ofPattern("M/d/yyyy");

    private static final DateTimeFormatter OUTPUT_WITH_TIME =
        DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm");
    private static final DateTimeFormatter OUTPUT_DATE_ONLY =
        DateTimeFormatter.ofPattern("MMM d, yyyy");

    /**
     * Parses a date string into a {@link LocalDateTime}.
     *
     * @param input the date string to parse, e.g., "2/12/2019" or "2/12/2019 1800"
     * @return an {@link Optional} containing the parsed {@link LocalDateTime}, or empty if the input
     *      cannot be parsed
     */
    public static Optional<LocalDateTime> parse(String input) {
        try {
            // If input contains time (space followed by digits)
            if (input.trim().matches(".+ \\d{3,4}")) {
                return Optional.of(LocalDateTime.parse(input, INPUT_WIH_TIME));
            } else {
                // Only date, no time
                LocalDate date = LocalDate.parse(input, INPUT_DATE_ONLY);
                return Optional.of(date.atStartOfDay());
            }
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Formats a {@link LocalDateTime} into a readable string.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a formatted string, e.g., "Feb 12, 2019" or "Feb 12, 2019 18:00"
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return dateTime.format(OUTPUT_DATE_ONLY);
        } else {
            return dateTime.format(OUTPUT_WITH_TIME);
        }
    }

    /**
     * Formats a {@link LocalDateTime} into the original input-style string.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a string in the input format, e.g., "2/12/2019" or "2/12/2019 1800"
     */
    public static String formatForLine(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return dateTime.format(INPUT_DATE_ONLY);
        } else {
            return dateTime.format(INPUT_WIH_TIME);
        }
    }
}
