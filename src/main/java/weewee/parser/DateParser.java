package weewee.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {
    // Strict input & save format
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Pretty display format
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    // Parse user input string into LocalDateTime
    public static LocalDateTime dateParse(String input) {
        return LocalDateTime.parse(input, inputFormat);
    }

    // Format for saving (always yyyy-MM-dd HHmm)
    public static String formatForSave(LocalDateTime dt) {
        return dt.format(inputFormat);
    }

    // Format for displaying to the user
    public static String displayFormat(LocalDateTime dt) {
        return dt.format(outputFormat);
    }
}
