package phuc.parser;

import org.junit.jupiter.api.Test;
import phuc.exception.PhucException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testFormatForDisplay() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 15, 17, 0);
        String result = DateTimeProcess.formatForDisplay(dateTime);

        assertTrue(result.contains("Dec"));
        assertTrue(result.contains("15"));
        assertTrue(result.contains("2023"));
        assertTrue(result.contains("5:00PM"));
    }

    @Test
    public void testFormatForFile() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 15, 17, 0);
        String result = DateTimeProcess.formatForFile(dateTime);

        assertEquals("15/12/2023 1700", result);
    }

    @Test
    public void testRoundTripFormatting() throws PhucException {
        LocalDateTime original = LocalDateTime.of(2023, 12, 15, 17, 30);

        String fileFormat = DateTimeProcess.formatForFile(original);
        LocalDateTime parsed = DateTimeProcess.parseDateTime(fileFormat);

        assertEquals(original, parsed);
    }
}