package clippy.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import clippy.ClippyException;
import clippy.command.AddTodoCommand;
import clippy.command.Command;

class ParserTest {
    @Test
    void parseTodoCommand_returnsTodoTask() {
        String input = "todo read book";
        try {
            Command command = Parser.parse(input);
            assertInstanceOf(AddTodoCommand.class, command);
        } catch (ClippyException e) {
            fail("Parsing failed with exception: " + e.getMessage());
        }
    }

    @Test
    void parseInvalidCommand_throwsException() {
        String input = "invalid command";
        assertThrows(ClippyException.class, () -> Parser.parse(input));
    }
}
