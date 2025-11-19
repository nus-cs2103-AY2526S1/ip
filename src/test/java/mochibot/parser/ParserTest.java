package mochibot.parser;

import mochibot.MochiBotException;

import mochibot.command.AddCommand;
import mochibot.command.Command;
import mochibot.command.DeleteCommand;
import mochibot.command.ExitCommand;
import mochibot.command.ListCommand;
import mochibot.command.MarkCommand;
import mochibot.command.UnmarkCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parse_listCommand_returnListCommandObject() throws MochiBotException {
        Command c = Parser.parse("list");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void parse_markCommand_returnMarkCommandObject() throws MochiBotException {
        Command c = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, c);
    }

    @Test
    public void parse_markCommandWithoutIndex_exceptionThrown() {
        assertThrows(MochiBotException.MissingTaskIndexException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void parse_markCommandNonIntegerIndex_exceptionThrown() {
        assertThrows(MochiBotException.InvalidTaskIndexException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void parse_unmarkCommand_returnUnmarkCommandObject() throws MochiBotException {
        Command c = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, c);
    }

    @Test
    public void parse_unmarkCommandWithoutIndex_exceptionThrown() {
        assertThrows(MochiBotException.MissingTaskIndexException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void parse_unmarkCommandNonIntegerIndex_exceptionThrown() {
        assertThrows(MochiBotException.InvalidTaskIndexException.class, () -> Parser.parse("unmark abc"));
    }

    @Test
    public void parse_todoCommand_returnAddCommandObject() throws MochiBotException {
        Command c = Parser.parse("todo eat bread");
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void parse_todoCommandWithoutTaskName_exceptionThrown() {
        assertThrows(MochiBotException.MissingTaskNameException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_deadlineCommand_returnAddCommandObject() throws MochiBotException {
        Command c = Parser.parse("deadline return book /by 2025-12-13 11:12");
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void parse_eventCommand_returnAddCommandObject() throws MochiBotException {
        Command c = Parser.parse("event project meeting /from 2021-02-21 16:48 /to 2021-03-01 01:59");
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void parse_deleteCommand_returnDeleteCommandObject() throws MochiBotException {
        Command c = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, c);
    }

    @Test
    public void parse_exitCommand_returnExitCommandObject() throws MochiBotException {
        Command c = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, c);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        assertThrows(MochiBotException.InvalidCommandException.class, () -> Parser.parse("hello"));
    }
}
