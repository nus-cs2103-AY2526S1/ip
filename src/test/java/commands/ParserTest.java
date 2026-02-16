package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.others.FindCommand;
import commands.others.HelpCommand;
import commands.others.ListCommand;
import commands.others.TaskCommand;
import commands.task.DeleteCommand;
import commands.task.ExitCommand;
import commands.task.MarkCommand;
import ineffaexceptions.IneffaException;

/**
 * Unit tests for the {@link Parser} class.
 */
public class ParserTest {

    /**
     * Tests that the 'bye' command returns an instance of {@link ExitCommand}.
     */
    @Test
    public void testParse_exitCommand_returnsExitCommand() throws IneffaException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    /**
     * Tests that the 'list' command returns an instance of {@link ListCommand}.
     */
    @Test
    public void testParse_listCommand_returnsListCommand() throws IneffaException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    /**
     * Tests that the 'mark' command returns a {@link MarkCommand} with mark set to true.
     */
    @Test
    public void testParse_markCommand_returnsMarkCommand() throws IneffaException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        assertTrue(markCommand.isDone());
    }

    /**
     * Tests that the 'unmark' command returns a {@link MarkCommand} with mark set to false.
     */
    @Test
    public void testParse_unmarkCommand_returnsMarkCommand() throws IneffaException {
        Command command = Parser.parse("unmark 2");
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        assertFalse(markCommand.isDone());
    }

    /**
     * Tests that 'todo' returns a {@link TaskCommand} with the correct enum.
     */
    @Test
    public void testParse_todoCommand_returnsTaskCommand() throws IneffaException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TaskCommand.class, command);
        TaskCommand taskCommand = (TaskCommand) command;
        assertEquals(CommandsEnum.TODO, taskCommand.getCommandType());
    }

    /**
     * Tests that 'deadline' returns a {@link TaskCommand} with the correct enum.
     */
    @Test
    public void testParse_deadlineCommand_returnsTaskCommand() throws IneffaException {
        Command command = Parser.parse("deadline submit report /by Friday");
        assertInstanceOf(TaskCommand.class, command);
        TaskCommand taskCommand = (TaskCommand) command;
        assertEquals(CommandsEnum.DEADLINE, taskCommand.getCommandType());
    }

    /**
     * Tests that 'event' returns a {@link TaskCommand} with the correct enum.
     */
    @Test
    public void testParse_eventCommand_returnsTaskCommand() throws IneffaException {
        Command command = Parser.parse("event meeting /at Monday 2pm");
        assertInstanceOf(TaskCommand.class, command);
        TaskCommand taskCommand = (TaskCommand) command;
        assertEquals(CommandsEnum.EVENT, taskCommand.getCommandType());
    }

    /**
     * Tests that 'delete' returns a {@link DeleteCommand}.
     */
    @Test
    public void testParse_deleteCommand_returnsDeleteCommand() throws IneffaException {
        Command command = Parser.parse("delete 3");
        assertInstanceOf(DeleteCommand.class, command);
    }

    /**
     * Tests that 'find' returns a {@link FindCommand}.
     */
    @Test
    public void testParse_findCommand_returnsFindCommand() throws IneffaException {
        Command command = Parser.parse("find book");
        assertInstanceOf(FindCommand.class, command);
    }

    /**
     * Tests that 'help' returns a {@link HelpCommand}.
     */
    @Test
    public void testParse_helpCommand_returnsHelpCommand() throws IneffaException {
        Command command = Parser.parse("help todo");
        assertInstanceOf(HelpCommand.class, command);
    }

    /**
     * Tests that an unknown command throws a {@link IneffaException}.
     */
    @Test
    public void testParse_invalidCommand_throwsIneffaException() {
        assertThrows(IneffaException.class, () -> Parser.parse("unknowncommand"));
    }
}
