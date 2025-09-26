package winnie.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import winnie.exception.InvalidDateTimeException;

public class DateTimeUtilTest {

    @Test
    public void parseDateTime_validDateTimeString_returnsLocalDateTime() throws InvalidDateTimeException {
        LocalDateTime expected = LocalDateTime.of(2023, 12, 1, 14, 30);
        LocalDateTime actual = DateTimeUtil.parseDateTime("2023-12-01 1430");
        assertEquals(expected, actual);
    }

    @Test
    public void parseDateTime_validDateOnly_returnsLocalDateTimeWithMidnight() throws InvalidDateTimeException {
        LocalDateTime expected = LocalDateTime.of(2023, 12, 1, 0, 0);
        LocalDateTime actual = DateTimeUtil.parseDateTime("2023-12-01");
        assertEquals(expected, actual);
    }

    @Test
    public void parseDateTime_invalidFormat_throwsInvalidDateTimeException() {
        assertThrows(InvalidDateTimeException.class, () -> {
            DateTimeUtil.parseDateTime("invalid-date");
        });
    }

    @Test
    public void formatForDisplay_dateTimeWithTime_returnsFormattedString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 1, 14, 30);
        String result = DateTimeUtil.formatForDisplay(dateTime);
        assertEquals("Dec 01 2023, 2:30PM", result);
    }

    @Test
    public void formatForDisplay_dateTimeAtMidnight_returnsDateOnly() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 1, 0, 0);
        String result = DateTimeUtil.formatForDisplay(dateTime);
        assertEquals("Dec 01 2023", result);
    }

    @Test
    public void formatForStorage_validDateTime_returnsStorageFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 1, 14, 30);
        String result = DateTimeUtil.formatForStorage(dateTime);
        assertEquals("2023-12-01 14:30", result);
    }

    @Test
    public void parseFromStorage_validStoredDateTime_returnsLocalDateTime() throws DateTimeParseException {
        LocalDateTime expected = LocalDateTime.of(2023, 12, 1, 14, 30);
        LocalDateTime actual = DateTimeUtil.parseFromStorage("2023-12-01 14:30");
        assertEquals(expected, actual);
    }

    @Test
    public void parseFromStorage_invalidFormat_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseFromStorage("invalid-format");
        });
    }
}