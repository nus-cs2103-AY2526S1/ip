package jinbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jinbot.command.Command;

/**
 * Contains unit tests for Parser class.
 */
public class ParserTest {

    /**
     * Tests that parsing "bye" returns a ByeCommand.
     */
    @Test
    void parse_byeCommand_returnsByeCommand() throws JinBotException {
        Command c = Parser.parse("bye");
        assertEquals("ByeCommand", c.getClass().getSimpleName());
    }

    /**
     * Tests that parsing "list" returns a ListCommand.
     */
    @Test
    void parse_listCommand_returnsListCommand() throws JinBotException {
        Command c = Parser.parse("list");
        assertEquals("ListCommand", c.getClass().getSimpleName());
    }

    /**
     * Tests that parsing "mark" returns a MarkCommand.
     */
    @Test
    void parse_markCommand_returnsMarkCommand() throws JinBotException {
        Command c = Parser.parse("mark 1");
        assertEquals("MarkCommand", c.getClass().getSimpleName());
    }

    /**
     * Tests that parsing "unmark" returns a UnmarkCommand.
     */
    @Test
    void parse_unmarkCommand_returnsUnmarkCommand() throws JinBotException {
        Command c = Parser.parse("unmark 2");
        assertEquals("UnmarkCommand", c.getClass().getSimpleName());
    }

    /**
     * Tests that parsing "todo" returns a TodoCommand.
     */
    @Test
    void parse_todoCommand_returnsTodoCommand() throws JinBotException {
        Command c = Parser.parse("todo read book");
        assertEquals("TodoCommand", c.getClass().getSimpleName());
    }

    /**
     * Tests that parsing "deadline" returns a DeadlineCommand.
     */
    @Test
    void parse_deadlineCommand_returnsDeadlineCommand() throws JinBotException {
        Command c = Parser.parse("deadline finish assignment /by 2025-12-31");
        assertEquals("DeadlineCommand", c.getClass().getSimpleName());
    }

    /**
     * Tests that parsing "event" returns a EventCommand.
     */
    @Test
    void parse_eventCommand_returnsEventCommand() throws JinBotException {
        Command c = Parser.parse("event conference /from 2025-12-31 /to 2026-01-02");
        assertEquals("EventCommand", c.getClass().getSimpleName());
    }

    /**
     * Tests that parsing "delete" returns a DeleteCommand.
     */
    @Test
    void parse_deleteCommand_returnsDeleteCommand() throws JinBotException {
        Command c = Parser.parse("delete 3");
        assertEquals("DeleteCommand", c.getClass().getSimpleName());
    }
}
