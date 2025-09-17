package bob.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.InvalidCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;

public class ParserTest {
    // Basic command parsing tests
    @Test
    public void parse_listCommand_returnsListCommand() {
        Command command = Parser.parse("list");
        assertEquals(ListCommand.class, command.getClass());
    }

    @Test
    public void parse_byeCommand_returnsExitCommand() {
        Command command = Parser.parse("bye");
        assertEquals(ExitCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommandWithSingleNumber_returnsMarkCommand() {
        Command command = Parser.parse("mark 1");
        assertEquals(MarkCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommandWithSingleNumber_returnsUnmarkCommand() {
        Command command = Parser.parse("unmark 1");
        assertEquals(UnmarkCommand.class, command.getClass());
    }

    @Test
    public void parse_todoCommandWithDescription_returnsAddCommand() {
        Command command = Parser.parse("todo read");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_deadlineCommandWithValidFormat_returnsAddCommand() {
        Command command = Parser.parse("deadline return book /by 01/01/2025 1200");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_eventCommandWithValidFormat_returnsAddCommand() {
        Command command = Parser.parse("event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommandWithSingleNumber_returnsDeleteCommand() {
        Command command = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    @Test
    public void parse_findCommandWithKeyword_returnsFindCommand() {
        Command command = Parser.parse("find book");
        assertEquals(FindCommand.class, command.getClass());
    }

    @Test
    public void parse_unknownCommand_returnsInvalidCommand() {
        Command command = Parser.parse("blah");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    // Edge cases and error handling tests
    @Test
    public void parse_markCommandWithoutNumber_returnsInvalidCommand() {
        Command command = Parser.parse("mark");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommandWithoutNumber_returnsInvalidCommand() {
        Command command = Parser.parse("unmark");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommandWithoutNumber_returnsInvalidCommand() {
        Command command = Parser.parse("delete");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_findCommandWithoutKeyword_returnsInvalidCommand() {
        Command command = Parser.parse("find");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommandWithInvalidNumber_returnsInvalidCommand() {
        Command command = Parser.parse("mark abc");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommandWithInvalidNumber_returnsInvalidCommand() {
        Command command = Parser.parse("unmark xyz");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommandWithInvalidNumber_returnsInvalidCommand() {
        Command command = Parser.parse("delete notanumber");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommandWithMultipleNumbers_returnsMarkCommand() {
        Command command = Parser.parse("mark 1 2 3");
        assertEquals(MarkCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommandWithMultipleNumbers_returnsUnmarkCommand() {
        Command command = Parser.parse("unmark 1 2 3");
        assertEquals(UnmarkCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommandWithMultipleNumbers_returnsDeleteCommand() {
        Command command = Parser.parse("delete 1 2 3");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    // Case sensitivity tests
    @Test
    public void parse_upperCaseCommand_returnsCorrectCommand() {
        Command command = Parser.parse("LIST");
        assertEquals(ListCommand.class, command.getClass());
    }

    @Test
    public void parse_mixedCaseCommand_returnsCorrectCommand() {
        Command command = Parser.parse("ByE");
        assertEquals(ExitCommand.class, command.getClass());
    }

    // Empty and whitespace tests
    @Test
    public void parse_emptyString_returnsInvalidCommand() {
        Command command = Parser.parse("");
        assertEquals(InvalidCommand.class, command.getClass());
    }

    @Test
    public void parse_whitespaceOnly_returnsInvalidCommand() {
        Command command = Parser.parse("   ");
        assertEquals(InvalidCommand.class, command.getClass());
    }
}
