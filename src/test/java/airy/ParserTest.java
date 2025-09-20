package airy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the Parser class.
 * Verifies that outputs match expected results after executing commands on given inputs.
 */
public class ParserTest {
    // Parser instance used for all tests
    private final Parser parser = new Parser();

    /**
     * Tests that parse() correctly trims whitespace and splits a command with arguments.
     */
    @Test
    void parse_withCommandAndArgs_trimsAndSplitsCorrectly() {
        String[] result = parser.parse("  Deadline  return book /by Sunday  ");
        assertArrayEquals(new String[]{"deadline", "return book /by Sunday"}, result);
    }
    /**
     * Tests that parse() returns empty arguments when only a command is given.
     */
    @Test
    void parse_withOnlyCommand_returnsEmptyArgs() {
        String[] result = parser.parse("LIST");
        assertArrayEquals(new String[]{"list", ""}, result);
    }
    /**
     * Tests that parseTaskNum() converts a valid user input to a zero-based index.
     */
    @Test
    void parseTaskNum_validNumber_returnsZeroBasedIndex() {
        int index = parser.parseTaskNum("mark", " 3 ", 5);
        assertEquals(2, index);
    }
    /**
     * Tests that parseTaskNum() throws AiryException for out-of-range inputs.
     */
    @Test
    void parseTaskNum_outOfBounds_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseTaskNum("mark", "10", 3));
        assertThrows(AiryException.class, () -> parser.parseTaskNum("mark", "0", 3));
    }
    /**
     * Tests that checkArg() throws AiryException when arguments are empty.
     */
    @Test
    void checkArg_emptyArgs_throwsException() {
        AiryException ex = assertThrows(AiryException.class, () -> parser.checkArg("todo", ""));
        assertTrue(ex.getMessage().contains("Please enter a task name"));
    }
    /**
     * Tests that parseDeadline() correctly splits and trims the input.
     */
    @Test
    void parseDeadline_validInput_splitsAndTrims() {
        String[] result = parser.parseDeadline("return book   /by  Sunday ");
        assertArrayEquals(new String[]{"return book", "Sunday"}, result);
    }
    /**
     * Tests that parseDeadline() throws AiryException if "/by" is missing.
     */
    @Test
    void parseDeadline_missingBy_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseDeadline("return book Sunday"));
    }
    /**
     * Tests that parseEvent() correctly splits and trims the input.
     */
    @Test
    void parseEvent_validInput_splitsAndTrims() {
        String[] result = parser.parseEvent("project meeting /from Mon 2pm /to 4pm");
        assertArrayEquals(new String[]{"project meeting", "Mon 2pm", "4pm"}, result);
    }
    /**
     * Tests that parseEvent() throws AiryException if "/from" or "/to" is missing.
     */
    @Test
    void parseEvent_missingFromOrTo_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseEvent("meeting /from Mon 2pm"));
    }
    /**
     * Tests that parseDelete() throws AiryException when the input is empty.
     */
    @Test
    void parseDelete_emptyArgs_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseDelete("", 5));
    }
    /**
     * Tests that parseDelete() throws AiryException for invalid task numbers.
     */
    @Test
    void parseDelete_invalidNumber_throwsException() {
        assertThrows(AiryException.class, () -> parser.parseDelete("6", 3));
    }
    /**
     * Tests that descDeleteArray() sorts the delete indexes in descending order.
     */
    @Test
    void descDeleteArray_sortsDescending() {
        int[] result = parser.descDeleteArray(new int[]{2, 0, 5});
        assertArrayEquals(new int[]{5, 2, 0}, result);
    }
    /**
     * Tests that trimParts() removes leading and trailing whitespace from all elements.
     */
    @Test
    void trimParts_removesWhitespace() {
        String[] result = parser.trimParts(new String[]{" hello ", " world "});
        assertArrayEquals(new String[]{"hello", "world"}, result);
    }
}
