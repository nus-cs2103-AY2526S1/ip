package goober.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

/**
 * Utility class for parsing command flags and date/time values.
 */
public class Parser {
    /**
     * Supported datetime format patterns for parsing input.
     */
    public static final List<String> FORMAT_LIST = List.of("yyyy-MM-dd HHmm", "HHmm yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = buildDateTimeFormatterFromPatterns(FORMAT_LIST);

    /**
     * Builds a DateTimeFormatter that can parse any of the given patterns.
     *
     * @param list list of datetime patterns
     * @return a DateTimeFormatter supporting all patterns in the list
     */
    public static DateTimeFormatter buildDateTimeFormatterFromPatterns(List<String> list) {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        list.forEach(str -> dateTimeFormatterBuilder.appendOptional(DateTimeFormatter.ofPattern(str)));
        return dateTimeFormatterBuilder.toFormatter();
    }

    /**
     * Extracts the argument following a given flag in a command string.
     *
     * @param line the full input line
     * @param flag the flag to search for (e.g. "/by", "/from")
     * @return the argument string after the flag, or an empty string if not found
     */
    public static String getFlagArg(String line, String flag) {
        assert flag != null : "Parser:getFlagArg: flag cannot be null";
        if (line.contains(flag)) {
            return line.split(flag, 2)[1].split("/", 2)[0].trim();
        } else {
            return "";
        }
    }

    /**
     * Parses a datetime string into a LocalDateTime using supported formats.
     *
     * @param str the datetime string to parse
     * @return the parsed LocalDateTime
     */
    public static LocalDateTime parseDateTime(String str) {
        return LocalDateTime.parse(str, DATE_TIME_FORMATTER);
    }

    /**
     * Formats a LocalDateTime into a string for display.
     *
     * @param dTF the LocalDateTime to format
     * @return formatted string
     */
    public static String dateTimeToString(LocalDateTime dTF) {
        return dTF.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + "H";
    }
}
