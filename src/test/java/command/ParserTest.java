package command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import misc.PepeException;

public class ParserTest {
    @Test
    public void testParseEmptyUserInput() {
        assertThrows(PepeException.class, () -> Parser.parse(""));
    }

    @Test
    public void testParseByeCommand() {
        assertDoesNotThrow(() -> Parser.parse("bye"));
    }

    @Test
    public void testParseInputUnrecognizedInput() {
        PepeException pepeException = assertThrows(PepeException.class, () -> Parser.parse("abc"));
        assertTrue(pepeException.getMessage().contains("Unknown command: abc"));
    }
}
