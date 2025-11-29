package larry.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void commandWord_trimsAndLowercases() {
        assertEquals("todo", Parser.commandWord("todo read"));
        assertEquals("todo", Parser.commandWord("  TODO   read"));
        assertEquals("list", Parser.commandWord("list"));
    }
    @Test
    void argTail_returnsRemainderAfterCommand() {
        assertEquals("read book", Parser.argTail("todo   read book", "todo"));
        assertEquals("", Parser.argTail("list", "list"));
    }
    @Test
    void parseIndex_validAndInvalid() {
        assertEquals(3, Parser.parseIndex("3"));
        assertEquals(10, Parser.parseIndex(" 10 "));
        assertEquals(-1, Parser.parseIndex("three"));
        assertEquals(-1, Parser.parseIndex(""));
    }
}
