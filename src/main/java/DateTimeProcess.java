import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeProcess {
    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d M yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"),
            DateTimeFormatter.ISO_LOCAL_DATE,
    };

    private static final DateTimeFormatter OUTPUT_DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter OUTPUT_FILE_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");


    public static LocalDateTime parseDateTime(String dateTimeStr) throws PhucException {
        String trimmedStr = dateTimeStr.trim();

        if (!trimmedStr.contains(" ")) {
            trimmedStr += " 0000";
        }

        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmedStr, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new PhucException("Invalid date format: " + dateTimeStr);
    }

    public static String formatForDisplay(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DISPLAY_FORMAT);
    }


    public static String formatForFile(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FILE_FORMAT);
    }
}