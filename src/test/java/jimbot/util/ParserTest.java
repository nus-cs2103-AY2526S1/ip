package jimbot.util;

import jimbot.exceptions.InvalidDateTimeException;
import jimbot.exceptions.InvalidIndexException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseIndex_validInput_success() throws InvalidIndexException {
        int taskCount = 5;
        int index = Parser.parseIndex("delete 2", "delete", taskCount);
        assertEquals(1, index);
    }

    @Test
    public void parseIndex_invalidInput_exceptionThrown() throws InvalidIndexException {
        int taskCount = 3;
        assertThrows(InvalidIndexException.class,
                () -> Parser.parseIndex("mark 4", "mark", taskCount));
    }

    @Test
    public void parseDateTime_dateAndTime_success() throws InvalidDateTimeException {
        LocalDateTime expected = LocalDateTime.of(2025, 8, 27, 12, 43);
        LocalDateTime actual = Parser.parseDateTime("27/08/2025 1243");
        assertEquals(expected, actual);
    }

    @Test
    public void parseDateTime_dateOnly_success() throws InvalidDateTimeException {
        LocalDate expected = LocalDate.of(2025, 8, 27);
        LocalDateTime actual = Parser.parseDateTime("27/08/2025");
        assertEquals(expected.atStartOfDay(), actual);
    }

    @Test
    public void parseDateTime_timeOnly_success() throws InvalidDateTimeException {
        LocalDate today = LocalDate.now();
        LocalDateTime actual = Parser.parseDateTime("1123");
        assertEquals(today.atTime(11, 23), actual);
    }

    @Test
    public void parseDateTime_invalidFormat_exceptionThrown() throws InvalidDateTimeException {
        assertThrows(InvalidDateTimeException.class, () -> Parser.parseDateTime("12 Aug 2025 2pm"));
    }

}
