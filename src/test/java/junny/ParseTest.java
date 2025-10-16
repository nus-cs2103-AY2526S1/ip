package junny;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;

import junny.Ui.Ui;
import junny.command.Command;
import junny.command.TodoCommand;
import junny.command.DeadlineCommand;

// test on parsing todo, deadline, event
// test on mark, unmark, delete
class ParserTest {
    private Ui ui = new Ui();
    private Parser parser = new Parser(ui);

    @Test
    void testParseTodoCommand() {
        Command c = parser.parse("todo read book");
        assertNotNull(c);
        assertEquals(TodoCommand.class, c.getClass());
    }

    void testParseDeadlineCommand() {
        Command c = parser.parse("deadline read book /by 2025-08-30");
        assertNotNull(c);
        assertEquals(DeadlineCommand.class, c.getClass());
    }


    @Test
    void testParseByeCommand() {
        Command c = parser.parse("bye");
        assertNotNull(c);
        assertTrue(c.isExit()); // ByeCommand overrides isExit() to return true
    }

    @Test
    void testParseInvalidCommand() {
        Command c = parser.parse("invalid");
        assertNull(c); // Parser returns null for unknown command
    }
}
