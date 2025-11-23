package diablo.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods of the Parser class.
 */
public class ParserTest {

    /**
     * Tests whether a string deadline input without /by produces the right parsed output.
     */
    @Test
    public void parse_deadlineWithNoDate_errorMessage() {
        Parser parser = new Parser();
        String[] output = parser.parse("deadline i wanna go home");
        assertEquals("Please indicate a deadline!", output[2]);
    }

    /**
     * Tests whether a string deadline input with the wrong date format produces the right parsed output.
     */
    @Test
    public void parse_deadlineWithWrongDateFormat_errorMessage() {
        Parser parser = new Parser();
        String[] output = parser.parse("deadline i wanna go home /by 19 January 2024");
        assertEquals("Please write the date in the format yyyy-mm-dd", output[2]);
    }
    /**
     * Tests whether a string deadline input with /by but no date produces the right parsed output.
     */
    @Test
    public void parse_deadlineWithMissingDate_errorMessage() {
        Parser parser = new Parser();
        String[] output = parser.parse("deadline i wanna go home /by ");
        assertEquals("Please write the date in the format yyyy-mm-dd", output[2]);
    }
}
