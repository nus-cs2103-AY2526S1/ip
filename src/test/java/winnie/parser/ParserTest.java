package winnie.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import winnie.command.*;
import winnie.exception.WinnieException;

public class ParserTest {

    @Test
    public void parse_validByeCommand_returnsByeCommand() throws WinnieException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void parse_validListCommand_returnsListCommand() throws WinnieException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_validTodoCommand_returnsTodoCommand() throws WinnieException {
        Command command = Parser.parse("todo buy groceries");
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    public void parse_emptyTodoCommand_throwsException() {
        assertThrows(WinnieException.class, () -> {
            Parser.parse("todo");
        });
    }

    @Test
    public void parse_validMarkCommand_returnsMarkCommand() throws WinnieException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_emptyMarkCommand_throwsException() {
        assertThrows(WinnieException.class, () -> {
            Parser.parse("mark");
        });
    }

    @Test
    public void parse_invalidMarkCommand_throwsException() {
        assertThrows(WinnieException.class, () -> {
            Parser.parse("mark abc");
        });
    }

    @Test
    public void parse_validDeadlineCommand_returnsDeadlineCommand() throws WinnieException {
        Command command = Parser.parse("deadline submit report /by 2023-12-01 2359");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void parse_deadlineCommandMissingBy_throwsException() {
        assertThrows(WinnieException.class, () -> {
            Parser.parse("deadline submit report");
        });
    }

    @Test
    public void parse_validEventCommand_returnsEventCommand() throws WinnieException {
        Command command = Parser.parse("event meeting /from 2023-12-01 1400 /to 2023-12-01 1600");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parse_eventCommandMissingFromTo_throwsException() {
        assertThrows(WinnieException.class, () -> {
            Parser.parse("event meeting");
        });
    }

    @Test
    public void parse_unknownCommand_returnsUnknownCommand() throws WinnieException {
        Command command = Parser.parse("invalid");
        assertTrue(command instanceof UnknownCommand);
    }

    @Test
    public void parse_validFindCommand_returnsFindCommand() throws WinnieException {
        Command command = Parser.parse("find book");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parse_emptyFindCommand_throwsException() {
        assertThrows(WinnieException.class, () -> {
            Parser.parse("find");
        });
    }

    @Test
    public void parse_findCommandWithOnlySpaces_throwsException() {
        assertThrows(WinnieException.class, () -> {
            Parser.parse("find   ");
        });
    }
}
