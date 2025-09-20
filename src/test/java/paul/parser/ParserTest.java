package paul.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests that getCommandType() returns the correct command when received a to do command.
     */
    @Test
    public void getCommandType_todoCommand_shouldReturnToDo() {
        String input = "todo finish homework";
        Parser.CommandType result = Parser.getCommandType(input);
        assertEquals(Parser.CommandType.TODO, result, "Should return TODO for 'todo' command");
    }

    /**
     * Tests that getCommandType() returns the correct command when received an unknown command.
     */
    @Test
    public void getCommandType_unknownCommand_shouldReturnUnknown() {
        String input = "paul";
        Parser.CommandType result = Parser.getCommandType(input);
        assertEquals(Parser.CommandType.UNKNOWN, result, "Should return UNKNOWN for unknown command");
    }
}
