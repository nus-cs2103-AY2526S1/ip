package chash.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskDateParser {
    private static final DateTimeFormatter PRINT_FORMAT = 
        DateTimeFormatter.ofPattern("MMM d yyyy");

    public static LocalDateTime tryParse(String input) {
        try {
            return LocalDateTime.parse(input);
        } catch (DateTimeParseException e) {
            //Allowed to swallow this error based on prog design
            //User can choose not to submit a ISO-8601 date string
            return null;
        }
    }

    public static String format(LocalDateTime dateTime, String dateStr) {
        return (dateTime != null) ? dateTime.format(TaskDateParser.PRINT_FORMAT) :
            dateStr;
    }
}
