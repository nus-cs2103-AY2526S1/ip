package phuc.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import phuc.exception.PhucException;


public class DateTimeProcessTest {

    @Test
    public void testParseDateTime() {
        assertDoesNotThrow(() -> {
            LocalDateTime result = DateTimeProcess.parseDateTime("15/12/2023 1700");
            assertEquals(LocalDateTime.of(2023, 12, 15, 17, 0), result);
        });

        assertDoesNotThrow(() -> {
            LocalDateTime result = DateTimeProcess.parseDateTime("15-12-2023 1700");
            assertEquals(LocalDateTime.of(2023, 12, 15, 17, 0), result);
        });

        assertDoesNotThrow(() -> {
            LocalDateTime result = DateTimeProcess.parseDateTime("2023-12-15 1700");
            assertEquals(LocalDateTime.of(2023, 12, 15, 17, 0), result);
        });
    }

    @Test
    public void testParseDateTime_dateOnly() {
        assertDoesNotThrow(() -> {
            LocalDateTime result = DateTimeProcess.parseDateTime("15/12/2023");
            assertEquals(LocalDateTime.of(2023, 12, 15, 0, 0), result);
        });
    }

    @Test
    public void testRoundTripFormatting() throws PhucException {
        LocalDateTime original = LocalDateTime.of(2023, 12, 15, 17, 30);

        String fileFormat = DateTimeProcess.formatForFile(original);
        LocalDateTime parsed = DateTimeProcess.parseDateTime(fileFormat);

        assertEquals(original, parsed);
    }

    @Test
    void testParseDateTimeValidFormats() throws PhucException {
        LocalDateTime result1 = DateTimeProcess.parseDateTime("25/12/2024 2359");
        assertEquals(LocalDateTime.of(2024, 12, 25, 23, 59), result1);

        LocalDateTime result2 = DateTimeProcess.parseDateTime("2024-12-25 2359");
        assertEquals(LocalDateTime.of(2024, 12, 25, 23, 59), result2);

        LocalDateTime result3 = DateTimeProcess.parseDateTime("25-12-2024 2359");
        assertEquals(LocalDateTime.of(2024, 12, 25, 23, 59), result3);
    }

    @Test
    void testParseDateTimeInvalidFormat() {
        assertThrows(PhucException.class, () -> {
            DateTimeProcess.parseDateTime("invalid date");
        });
    }

    @Test
    void testFormatForDisplay() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
        String formatted = DateTimeProcess.formatForDisplay(dateTime);
        assertTrue(formatted.contains("Dec 25 2024"));
        assertTrue(formatted.contains("11:59PM"));
    }

    @Test
    void testFormatForFile() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 23, 59);
        String formatted = DateTimeProcess.formatForFile(dateTime);
        assertEquals("25/12/2024 2359", formatted);
    }
}
