package jeff.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jeff.storage.JeffException;

class ParserTest {

    @Test
    void testParseCommand() throws JeffException {
        Parser.Result result = Parser.parseCommand("list");
        assertEquals(Command.LIST, result.command);

        result = Parser.parseCommand("todo groceries");
        assertEquals(Command.TODO, result.command);
        assertEquals("groceries", result.description);
    }

    @Test
    void testInvalidCommands() {
        assertThrows(JeffException.class, () -> Parser.parseCommand(""));
        assertThrows(JeffException.class, () -> Parser.parseCommand("invalid"));
    }
}
