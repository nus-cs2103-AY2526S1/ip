import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sofi.Parser;

public class ParserTest {

    @Test
    public void testParseCommandBye() {
        assertEquals("bye", Parser.parseCommand("bye"));
        assertEquals("unknown", Parser.parseCommand("bye "));
    }

    @Test
    public void testParseCommandList() {
        assertEquals("list", Parser.parseCommand("list"));
        assertEquals("unknown", Parser.parseCommand("list "));
    }

    @Test
    public void testParseCommandTodo() {
        assertEquals("todo", Parser.parseCommand("todo read book"));
        assertEquals("todo", Parser.parseCommand("todo"));
        assertEquals("todo", Parser.parseCommand("todo "));
    }

    @Test
    public void testParseCommandDeadline() {
        assertEquals("deadline", Parser.parseCommand("deadline return book /by 2019-12-15"));
        assertEquals("deadline", Parser.parseCommand("deadline"));
        assertEquals("deadline", Parser.parseCommand("deadline "));
    }

    @Test
    public void testParseCommandEvent() {
        assertEquals("event", Parser.parseCommand("event team meeting /from 2019-12-15 /to 2019-12-16"));
        assertEquals("event", Parser.parseCommand("event"));
        assertEquals("event", Parser.parseCommand("event "));
    }

    @Test
    public void testParseCommandMark() {
        assertEquals("mark", Parser.parseCommand("mark 1"));
        assertEquals("mark", Parser.parseCommand("mark"));
        assertEquals("mark", Parser.parseCommand("mark "));
    }

    @Test
    public void testParseCommandUnmark() {
        assertEquals("unmark", Parser.parseCommand("unmark 1"));
        assertEquals("unmark", Parser.parseCommand("unmark"));
        assertEquals("unmark", Parser.parseCommand("unmark "));
    }

    @Test
    public void testParseCommandDelete() {
        assertEquals("delete", Parser.parseCommand("delete 1"));
        assertEquals("delete", Parser.parseCommand("delete"));
        assertEquals("delete", Parser.parseCommand("delete "));
    }

    @Test
    public void testParseCommandUnknown() {
        assertEquals("unknown", Parser.parseCommand("hello"));
        assertEquals("unknown", Parser.parseCommand("random command"));
        assertEquals("unknown", Parser.parseCommand(""));
        assertEquals("unknown", Parser.parseCommand("   "));
    }

    @Test
    public void testParseTodoDescription() {
        assertEquals("read book", Parser.parseTodoDescription("todo read book"));
        assertEquals("write essay", Parser.parseTodoDescription("todo write essay"));
        assertEquals("", Parser.parseTodoDescription("todo"));
        assertEquals("", Parser.parseTodoDescription("todo "));
        assertEquals("", Parser.parseTodoDescription("todo   "));
        assertEquals("", Parser.parseTodoDescription("tod"));
    }

    @Test
    public void testParseDeadline() {
        String[] result = Parser.parseDeadline("deadline return book /by 2019-12-15");
        assertEquals("return book", result[0]);
        assertEquals("2019-12-15", result[1]);
    }

    @Test
    public void testParseDeadlineWithSpaces() {
        String[] result = Parser.parseDeadline("deadline   return book   /by   2019-12-15  ");
        assertEquals("return book", result[0]);
        assertEquals("2019-12-15", result[1]);
    }

    @Test
    public void testParseDeadlineEmptyDescription() {
        String[] result = Parser.parseDeadline("deadline /by 2019-12-15");
        assertEquals("", result[0]);
        assertEquals("2019-12-15", result[1]);
    }

    @Test
    public void testParseEvent() {
        String[] result = Parser.parseEvent("event team meeting /from 2019-12-15 /to 2019-12-16");
        assertEquals("team meeting", result[0]);
        assertEquals("2019-12-15", result[1]);
        assertEquals("2019-12-16", result[2]);
    }

    @Test
    public void testParseEventWithSpaces() {
        String[] result = Parser.parseEvent("event   team meeting   /from   2019-12-15   /to   2019-12-16  ");
        assertEquals("team meeting", result[0]);
        assertEquals("2019-12-15", result[1]);
        assertEquals("2019-12-16", result[2]);
    }

    @Test
    public void testParseEventEmptyDescription() {
        String[] result = Parser.parseEvent("event /from 2019-12-15 /to 2019-12-16");
        assertEquals("", result[0]);
        assertEquals("2019-12-15", result[1]);
        assertEquals("2019-12-16", result[2]);
    }

    @Test
    public void testParseTaskNumber() {
        assertEquals(0, Parser.parseTaskNumber("mark 1"));
        assertEquals(1, Parser.parseTaskNumber("mark 2"));
        assertEquals(9, Parser.parseTaskNumber("mark 10"));
    }

    @Test
    public void testParseTaskNumberWithSpaces() {
        assertEquals(0, Parser.parseTaskNumber("mark 1"));
        assertEquals(1, Parser.parseTaskNumber("mark 2"));
        assertEquals(9, Parser.parseTaskNumber("mark 10"));
    }

    @Test
    public void testParseTaskNumberInvalidFormat() {
        assertThrows(NumberFormatException.class, () -> {
            Parser.parseTaskNumber("mark abc");
        });
        
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Parser.parseTaskNumber("mark");
        });
        
        assertThrows(NumberFormatException.class, () -> {
            Parser.parseTaskNumber("mark ");
        });
    }

    @Test
    public void testParseTaskNumberArrayIndexOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Parser.parseTaskNumber("mark");
        });
        
        assertThrows(NumberFormatException.class, () -> {
            Parser.parseTaskNumber("mark ");
        });
    }
}
