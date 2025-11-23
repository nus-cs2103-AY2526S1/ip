package stewie.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import stewie.command.CommandType;
import stewie.exceptions.InvalidCommandException;

/**
 * Tests for {@link Helper}.
 */
class HelperTest {

    @Test
    void parseIndexOrThrow_validInteger_returnsParsedInt() throws InvalidCommandException {
        int result = Helper.parseIndexOrThrow("42", "usage");
        assertEquals(42, result);
    }

    @Test
    void parseIndexOrThrow_blank_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () ->
                Helper.parseIndexOrThrow("   ", "usage"));
    }

    @Test
    void parseIndexOrThrow_nonInteger_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () ->
                Helper.parseIndexOrThrow("abc", "usage"));
    }

    @Test
    void parseCommandType_knownCommand_returnsCorrectEnum() {
        assertEquals(CommandType.TODO, Helper.parseCommandType("todo"));
    }

    @Test
    void parseCommandType_unknownCommand_returnsUnknown() {
        assertEquals(CommandType.UNKNOWN, Helper.parseCommandType("foobar"));
    }

    @Test
    void parseDateTime_validDateTimeString_returnsLocalDateTime() {
        LocalDateTime dt = Helper.parseDateTime("21/09/2025 10:00");
        assertEquals(LocalDateTime.of(2025, 9, 21, 10, 0), dt);
    }

    @Test
    void parseDateTime_validDateOnly_returnsMidnight() {
        LocalDateTime dt = Helper.parseDateTime("21/09/2025");
        assertEquals(LocalDateTime.of(2025, 9, 21, 0, 0), dt);
    }

    @Test
    void parseDateTime_invalid_returnsNull() {
        assertNull(Helper.parseDateTime("invalid-date"));
    }

    @Test
    void dateTimeToString_startOfDay_formatsAsDateOnly() {
        LocalDateTime dt = LocalDateTime.of(2025, 9, 21, 0, 0);
        assertEquals("21 Sep 2025", Helper.dateTimeToString(dt));
    }

    @Test
    void dateTimeToString_nonStartOfDay_formatsAsDateTime() {
        LocalDateTime dt = LocalDateTime.of(2025, 9, 21, 10, 30);
        assertEquals("21 Sep 2025 10:30", Helper.dateTimeToString(dt));
    }

    @Test
    void dateTimeToFileFormat_validDateTime_correctFormat() {
        LocalDateTime dt = LocalDateTime.of(2025, 9, 21, 10, 30);
        assertEquals("21/09/2025 10:30", Helper.dateTimeToFileFormat(dt));
    }
}
