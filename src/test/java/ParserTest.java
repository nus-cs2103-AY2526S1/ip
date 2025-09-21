import org.junit.jupiter.api.Test;
import thopz.Command;
import thopz.Parser;
import thopz.AddTodoCommand;
import thopz.AddDeadlineCommand;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Unit tests for the {@link } class.
 * Tests that input strings are correctly parsed into the appropriate {@link Command} objects,
 * or throw exceptions when invalid.
 */
public class ParserTest {

    /**
     * Tests that a valid "todo" input string is correctly parsed
     * into an {@link AddTodoCommand}.
     */
    @Test
    public void parseTodo_returnsAddTodoCommand() {
        Command c = Parser.parse("todo homework");
        assertTrue(c instanceof AddTodoCommand, "Expected AddTodoCommand for todo input");
    }

    /**
     * Tests that a valid "deadline" input string is correctly parsed
     * into an {@link AddDeadlineCommand}.
     */
    @Test
    public void parseDeadline_returnsAddDeadlineCommand() {
        Command c = Parser.parse("deadline homework / 2025-09-04T21:56");
        assertTrue(c instanceof AddDeadlineCommand, "Expected AddDeadlineCommand for deadline input");
    }

    /**
     * Tests that an invalid input string that does not correspond to any known command
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    public void parseInvalidCommand_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Parser.parse("invalid something"),
                "Expected IllegalArgumentException for invalid command");
    }
}
