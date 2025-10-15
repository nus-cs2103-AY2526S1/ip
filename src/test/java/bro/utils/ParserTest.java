package bro.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bro.commands.Command;
import bro.commands.CommandError;

public class ParserTest {
    @Test
    public void getCommand_byeCommand_returnsByeCommand() {
        Command cmd = new Parser().getCommand("bye");
        assertTrue(cmd.getClass().getName().contains("ByeCommand"), "Class name should contain 'ByeCommand'");
    }

    @Test
    public void getCommand_listCommand_returnsListCommand() {
        Command cmd = new Parser().getCommand("list");
        assertTrue(cmd.getClass().getName().contains("ListCommand"), "Class name should contain 'ListCommand'");
    }

    @Test
    public void getCommand_validMarkCommand_returnsMarkCommand() {
        Command cmd = new Parser().getCommand("mark 2");
        assertTrue(cmd.getClass().getName().contains("MarkCommand"), "Class name should contain 'MarkCommand'");
    }

    @Test
    public void getCommand_validUnmarkCommand_returnsUnmarkCommand() {
        Command cmd = new Parser().getCommand("unmark 1");
        assertTrue(cmd.getClass().getName().contains("UnmarkCommand"), "Class name should contain 'UnmarkCommand'");
    }

    @Test
    public void getCommand_validDeleteCommand_returnsDeleteCommand() {
        Command cmd = new Parser().getCommand("delete 3");
        assertTrue(cmd.getClass().getName().contains("DeleteCommand"), "Class name should contain 'DeleteCommand'");
    }

    @Test
    public void getCommand_validTasksOnCommand_returnsTasksOnCommand() {
        Command cmd = new Parser().getCommand("tasks on 2/12/2019");
        assertTrue(cmd.getClass().getName().contains("TasksOnCommand"), "Class name should contain 'TasksOnCommand'");
    }

    @Test
    public void getCommand_validTodoCommand_returnsTodoCommand() {
        Command cmd = new Parser().getCommand("todo something");
        assertTrue(cmd.getClass().getName().contains("TodoCommand"), "Class name should contain 'TodoCommand'");
    }

    @Test
    public void getCommand_validFindCommand_returnsFindCommand() {
        Command cmd = new Parser().getCommand("find keyword");
        assertTrue(cmd.getClass().getName().contains("FindCommand"), "Class name should contain 'FindCommand'");
    }

    @Test
    public void getCommand_unknownCommand_returnsUnknownCommand() {
        Command cmd = new Parser().getCommand("foobar");
        assertTrue(cmd.getClass().getName().contains("UnknownCommand"), "Class name should contain 'UnknownCommand'");
    }

    @Test
    public void getCommand_invalidDeadlineCommand_returnsCommandError() {
        Command cmd = new Parser().getCommand("deadline test /by 99/99/9999 9999");
        assertTrue(cmd instanceof CommandError);
    }
}
