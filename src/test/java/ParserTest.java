import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import floydai.FloydException;
import floydai.command.AddTodoCommand;
import floydai.command.Command;
import floydai.command.MarkCommand;
import floydai.parser.Parser;

class ParserTest {

    @Test
    void testParseTodoCommand() throws FloydException {
        Command cmd = Parser.parse("todo read book");
        assertInstanceOf(AddTodoCommand.class, cmd);
    }

    @Test
    void testParseInvalidCommandThrows() {
        Exception exception = assertThrows(FloydException.class, () -> {
            Parser.parse("foobar");
        });

        String expectedMessage = "I don’t understand that command.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testParseMarkCommand() throws FloydException {
        Command cmd = Parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, cmd);
    }
}
