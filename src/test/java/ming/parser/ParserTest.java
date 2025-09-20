package ming.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import ming.command.Command;
import ming.command.TodoCommand;
import ming.exception.MingException;

/**
 * Test class for the Parser.
 */
public class ParserTest {
    /**
     * Tests parsing a valid todo command.
     * Expects a TodoCommand to be returned.
     */
    @Test
    public void parseTodo_validInput_success() throws Exception {
        String input = "todo read book";
        Command command = Parser.parse(input);

        assertInstanceOf(TodoCommand.class, command);
    }

    /**
     * Tests parsing an invalid command.
     * Expects a MingException to be thrown with the message "Invalid task index: 0".
     */
    @Test
    public void parseMark_invalidIndex_throwsException() {
        String input = "mark 0";
        try {
            Parser.parse(input);
        } catch (MingException e) {
            assertEquals("Invalid task index: 0", e.getMessage());
        }
    }
}

