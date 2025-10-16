package monet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDeadline_validInput_success() throws MonetException {
        // Test a perfectly valid command with a description, date, and priority.
        String input = "deadline return book /by 2025-10-15 1800 /p 1";
        Object[] result = Parser.parseDeadline(input);

        assertEquals("return book", result[0]);
        assertEquals("2025-10-15 1800", result[1]);
        assertEquals(Priority.HIGH, result[2]);
    }

    @Test
    public void parseDeadline_missingDateKeyword_exceptionThrown() {
        // Test a malformed command where the "/by" keyword is missing.
        String input = "deadline return book 2025-10-15 1800";
        MonetException exception = assertThrows(MonetException.class, () -> {
            Parser.parseDeadline(input);
        });
        assertEquals("Invalid format. Prithee useth: deadline <description> /by <date>", exception.getMessage());
    }

    @Test
    public void parseDeadline_emptyDescription_exceptionThrown() {
        // Test a command where the description is missing.
        String input = "deadline /by 2025-10-15 1800";
        assertThrows(MonetException.class, () -> {
            Parser.parseDeadline(input);
        });
    }
}
