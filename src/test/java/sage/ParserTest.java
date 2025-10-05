package sage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import sage.command.*;
import sage.exception.SageException;
import sage.parser.Parser;

/**
 * Test class for the Parser functionality.
 * Tests parsing of various user commands and error handling.
 */
public class ParserTest {

    @Test
    public void parse_exitCommand_returnsExitCommand() throws SageException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
        assertTrue(command.isExit());
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws SageException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_helpCommand_returnsHelpCommand() throws SageException {
        Command command = Parser.parse("help");
        assertTrue(command instanceof HelpCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_todoCommand_returnsAddTodoCommand() throws SageException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddTodoCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_emptyTodoCommand_throwsException() {
        assertThrows(SageException.class, () -> Parser.parse("todo"));
        assertThrows(SageException.class, () -> Parser.parse("todo   "));
    }

    @Test
    public void parse_deadlineCommand_returnsAddDeadlineCommand() throws SageException {
        Command command = Parser.parse("deadline submit assignment /by 15/3/2024 2359");
        assertTrue(command instanceof AddDeadlineCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_invalidDeadlineFormat_throwsException() {
        assertThrows(SageException.class, () -> Parser.parse("deadline submit assignment"));
        assertThrows(SageException.class, () -> Parser.parse("deadline submit assignment /by"));
        assertThrows(SageException.class, () -> Parser.parse("deadline /by 15/3/2024 2359"));
    }

    @Test
    public void parse_eventCommand_returnsAddEventCommand() throws SageException {
        Command command = Parser.parse("event team meeting /from 15/3/2024 1400 /to 15/3/2024 1600");
        assertTrue(command instanceof AddEventCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_invalidEventFormat_throwsException() {
        assertThrows(SageException.class, () -> Parser.parse("event team meeting"));
        assertThrows(SageException.class, () -> Parser.parse("event team meeting /from 15/3/2024 1400"));
        assertThrows(SageException.class, () -> Parser.parse("event /from 15/3/2024 1400 /to 15/3/2024 1600"));
    }

    @Test
    public void parse_markCommand_returnsMarkCommand() throws SageException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_invalidMarkCommand_throwsException() {
        assertThrows(SageException.class, () -> Parser.parse("mark"));
        assertThrows(SageException.class, () -> Parser.parse("mark abc"));
        assertThrows(SageException.class, () -> Parser.parse("mark   "));
    }

    @Test
    public void parse_unmarkCommand_returnsUnmarkCommand() throws SageException {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof UnmarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws SageException {
        Command command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_findCommand_returnsFindCommand() throws SageException {
        Command command = Parser.parse("find book");
        assertTrue(command instanceof FindCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void parse_emptyFindCommand_throwsException() {
        assertThrows(SageException.class, () -> Parser.parse("find"));
        assertThrows(SageException.class, () -> Parser.parse("find   "));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(SageException.class, () -> Parser.parse("unknown"));
        assertThrows(SageException.class, () -> Parser.parse("randomcommand"));
    }

    @Test
    public void parseDateTime_validFormat_returnsLocalDateTime() throws SageException {
        LocalDateTime expected = LocalDateTime.of(2024, 3, 15, 23, 59);
        LocalDateTime actual = Parser.parseDateTime("15/3/2024 2359");
        assertEquals(expected, actual);
    }

    @Test
    public void parseDateTime_invalidFormat_throwsException() {
        assertThrows(SageException.class, () -> Parser.parseDateTime("invalid-date"));
        assertThrows(SageException.class, () -> Parser.parseDateTime("15-03-2024 2359"));
        assertThrows(SageException.class, () -> Parser.parseDateTime("15/3/24 2359"));
    }

    @Test
    public void parse_commandWithExtraSpaces_handlesCorrectly() throws SageException {
        Command todoCommand = Parser.parse("todo   read book   ");
        assertTrue(todoCommand instanceof AddTodoCommand);

        Command markCommand = Parser.parse("mark   1   ");
        assertTrue(markCommand instanceof MarkCommand);
    }
}