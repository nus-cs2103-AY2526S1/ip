package robert.parser;

import org.junit.jupiter.api.Test;
import robert.command.*;
import robert.exception.RobertException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserCommandTest {

    @Test
    public void parse_byeCommand_returnsByeCommand() throws RobertException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
        assertTrue(command.isExit());
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws RobertException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_markCommand_returnsMarkCommand() throws RobertException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_todoCommand_returnsAddTodoCommand() throws RobertException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddTodoCommand);
    }

    @Test
    public void parse_deadlineCommand_returnsAddDeadlineCommand() throws RobertException {
        Command command = Parser.parse("deadline return book /by 2019-12-01 1800");
        assertTrue(command instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_eventCommand_returnsAddEventCommand() throws RobertException {
        Command command = Parser.parse("event meeting /from 2019-12-01 1400 /to 1600");
        assertTrue(command instanceof AddEventCommand);
    }

    @Test
    public void parse_findCommand_returnsFindCommand() throws RobertException {
        Command command = Parser.parse("find book");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(RobertException.class, () -> Parser.parse("unknown"));
    }

    @Test
    public void parse_emptyInput_throwsException() {
        assertThrows(RobertException.class, () -> Parser.parse(""));
    }
}