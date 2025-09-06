import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public final class DateTimeParser {
    public static final String HUMAN_PATTERN = "dd/MM/yyyy HH:mm:ss";      // for messages
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss")             // use uuuu for strict year
                    .withResolverStyle(ResolverStyle.STRICT);    // rejects 31/02/2025, etc.

    public static LocalDateTime parseDateTime(String s) throws DateTimeParseException {
        return LocalDateTime.parse(s, FORMATTER);
    }
}