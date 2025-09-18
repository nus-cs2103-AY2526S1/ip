package cheryl.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import cheryl.command.Command;
import cheryl.command.AddTodoCommand;
import cheryl.command.AddDeadlineCommand;
import cheryl.command.AddEventCommand;
import cheryl.command.MarkCommand;
import cheryl.command.ExitCommand;

class ParserTest {

    @Test
    void testParseTodoCommand() throws DukeException {
        Command command = Parser.parse("todo Read book");

        assertTrue(command instanceof AddTodoCommand);
    }

    @Test
    void testParseDeadlineCommand() throws DukeException {
        Command command = Parser.parse("deadline Submit report /by 2025-09-01");

        assertTrue(command instanceof AddDeadlineCommand);
    }

    @Test
    public void testParseEventCommand() throws Exception {
        Command cmd = Parser.parse("event project meeting /from 2025-09-20 /to 2025-09-21");
        assertTrue(cmd instanceof AddEventCommand);
    }

    @Test
    void testParseMarkCommand() throws DukeException {
        Command command = Parser.parse("mark 0");

        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void testParseExitCommand() throws DukeException {
        Command command = Parser.parse("bye");

        assertTrue(command instanceof ExitCommand);
    }

    @Test
    void testParseInvalidCommand() {
        assertThrows(DukeException.class, () -> Parser.parse("unknown command"));
    }
}
