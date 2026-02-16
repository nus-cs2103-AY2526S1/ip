package meo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import meo.MeoException;
import meo.command.Command;
import meo.command.DeadlineCommand;
import meo.command.DeleteCommand;
import meo.command.EventCommand;
import meo.command.FindCommand;
import meo.command.MarkCommand;
import meo.command.TodoCommand;
import meo.command.UnmarkCommand;

public class CommandParserTest {
    private final CommandParser parser = new CommandParser();

    @Test
    public void parse_taskWithDescription_success() {
        try {
            // parse a todo command
            Command command = parser.parser("todo eat grass");
            assertTrue(command instanceof TodoCommand);

            // parse a deadline command
            command = parser.parser("deadline eat grass /by Monday");
            assertTrue(command instanceof DeadlineCommand);

            // parse an event command
            command = parser.parser("event eat grass /from Aug 2 /to Aug 3");
            assertTrue(command instanceof EventCommand);

        } catch (MeoException e) {
            fail();
        }
    }

    @Test
    public void parse_todoWithNoDescription_exceptionThrown() {
        assertThrows(MeoException.class, () -> parser.parser("todo"));
    }

    @Test
    public void parse_deadlineWithNoTime_exceptionThrown() {
        assertThrows(MeoException.class, () -> parser.parser("deadline eat grass"));
    }

    /**
     * Tests written using the help of ChatGPT
     */

    @Test
    public void parse_eventWithMissingTags_execptionThrown() {
        MeoException e = assertThrows(MeoException.class, () -> parser.parser("event project meeting"));
        assertEquals(MeoException.eventTime, e.getMessage());
    }

    @Test
    public void testMarkCommand_success() throws MeoException {
        Command cmd = parser.parser("mark 2");
        assertTrue(cmd instanceof MarkCommand);
    }

    @Test
    public void testUnmarkCommand_success() throws MeoException {
        Command cmd = parser.parser("unmark 3");
        assertTrue(cmd instanceof UnmarkCommand);
    }

    @Test
    public void testDeleteCommand_success() throws MeoException {
        Command cmd = parser.parser("delete 1");
        assertTrue(cmd instanceof DeleteCommand);
    }

    @Test
    public void testFindCommand_success() throws MeoException {
        Command cmd = parser.parser("find homework");
        assertTrue(cmd instanceof FindCommand);
    }
}
