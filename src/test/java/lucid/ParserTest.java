package lucid;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Improved using ChatGPT - 18 September 2025
public class ParserTest {

    // Split from original test success method using ChatGPT
    @Test
    public void parseDateAndTimeString_dateOnly_success() throws DateTimeParseException {
        assertArrayEquals(
                new String[]{"2025-08-19", null},
                Parser.parseDateTimeString("2025-08-19")
        );
    }

    // Split from original test success method using ChatGPT
    @Test
    public void parseDateAndTimeString_dateAndTime_success() throws DateTimeParseException {
        assertArrayEquals(
                new String[]{"2025-11-24", "1735"},
                Parser.parseDateTimeString("2025-11-24-1735")
        );
    }

    // Used ChatGPT to split from original test method, and use assertThrows instead of try-catch
    @Test
    public void parseDateAndTimeString_slashInsteadOfDash_exceptionThrown() {
        // CHECKSTYLE.OFF: SeparatorWrap
        DateTimeParseException e = assertThrows(
                DateTimeParseException.class,
                () -> Parser.parseDateTimeString("2025/08/19")
        );
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals("I'm sorry! Something went wrong with the date and time", e.getMessage());
    }

    // Used ChatGPT to split from original test method, and use assertThrows instead of try-catch
    @Test
    public void parseDateAndTimeString_invalidTimeFormat_exceptionThrown() {
        // CHECKSTYLE.OFF: SeparatorWrap
        DateTimeParseException e = assertThrows(
                DateTimeParseException.class,
                () -> Parser.parseDateTimeString("2025-11-24-99:99")
        );
        // CHECKSTYLE.ON: SeparatorWrap
        assertEquals("I'm sorry! Something went wrong with the date and time", e.getMessage());
    }

    // Used ChatGPT to improve test suite, identifying lack of testing for empty string input
    @Test
    public void parseDateAndTimeString_emptyString_exceptionThrown() {
        // CHECKSTYLE.OFF: SeparatorWrap
        assertThrows(DateTimeParseException.class,
                () -> Parser.parseDateTimeString(""));
        // CHECKSTYLE.ON: SeparatorWrap
    }

}
