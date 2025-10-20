package larry.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserAliasTest {

    @Test
    void commandWord_aliases_and_passthrough() {
        assertEquals("todo", Parser.commandWord("t read"));
        assertEquals("deadline", Parser.commandWord("dl submit /by 2025-12-31"));
        assertEquals("event", Parser.commandWord("ev party /from 2025-10-10 1000 /to 2025-10-10 1200"));
        assertEquals("list", Parser.commandWord("ls"));
        assertEquals("mark", Parser.commandWord("mk 2"));
        assertEquals("unmark", Parser.commandWord("um 2"));
        assertEquals("delete", Parser.commandWord("del 3"));
        assertEquals("find", Parser.commandWord("f book"));

        assertEquals("foobar", Parser.commandWord("FoObAr arg"));
    }

    @Test
    void argTail_handlesSpacesAndEmpty() {
        assertEquals("x y", Parser.argTail("cmd   x y", "cmd"));
        assertEquals("", Parser.argTail("cmd", "cmd"));
        assertEquals("", Parser.argTail("   cmd   ", "cmd"));
    }

    @Test
    void parseIndex_acceptsNumbers_rejectsOthers() {
        assertEquals(42, Parser.parseIndex("42"));
        assertEquals(-1, Parser.parseIndex("  "));
        assertEquals(-1, Parser.parseIndex("abc"));
    }
}
