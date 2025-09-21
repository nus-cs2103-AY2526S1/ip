package pengu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import pengu.exception.InvalidFieldException;
import pengu.exception.PenguException;

class DateTimeParserTest {
    @Test
    void testFromDateTimeStringValid() throws PenguException {
        String input = "2025-07-23 14:30";
        LocalDateTime expected = LocalDateTime.of(2025, 7, 23, 14, 30);

        LocalDateTime result = DateTimeParser.fromDateTimeString(input);

        assertEquals(expected, result);
    }

    @Test
    void fromDateTimeString_invalidFormat_exceptionThrown() {
        String input = "23-07-2025 14:30"; // wrong format

        try {
            DateTimeParser.fromDateTimeString(input);
            fail();
        } catch (InvalidFieldException e) {
            assertEquals("Oh no! Your field(s) provided caused the following error:\n"
                    + "Expected: date time string in format yyyy-MM-dd HH:mm (e.g. 2025-07-23 14:30)\n"
                    + "Got: 23-07-2025 14:30", e.getMessage());
        }
    }

    @Test
    void testToInputFormatString() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 7, 23, 14, 30);

        String result = DateTimeParser.toInputFormatString(dateTime);

        assertEquals("2025-07-23 14:30", result);
    }

    @Test
    void testToOutputFormatString() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 7, 23, 14, 30);

        String result = DateTimeParser.toOutputFormatString(dateTime);

        // Default formatter: "MMM dd yyyy HH:mm"
        assertEquals("Jul 23 2025 14:30", result);
    }
}
