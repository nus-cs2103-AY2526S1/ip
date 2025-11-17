package parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import command.ByeCommand;
import command.Command;
import command.InvalidCommand;
import command.ListCommand;
import task.Todo;
import tasklist.TaskList;

public class ParserTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        // Add some tasks for testing mark/unmark/delete
        taskList.addTask(new Todo("Test task 1"));
        taskList.addTask(new Todo("Test task 2"));
    }

    @Test
    public void testParseByeCommand() {
        Command command = Parser.parse("bye", taskList);
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void testParseByeCommand_extraArguments() {
        Command command = Parser.parse("bye extra", taskList);
        assertInstanceOf(InvalidCommand.class, command);
        InvalidCommand invalid = (InvalidCommand) command;
        String message = invalid.getMessage();
        assertTrue(message.contains("Please do not add anything behind bye command"));
    }

    @Test
    public void testParseListCommand() {
        Command command = Parser.parse("list", taskList);
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseListCommand_extraArguments() {
        Command command = Parser.parse("list extra", taskList);
        assertInstanceOf(InvalidCommand.class, command);
        InvalidCommand invalid = (InvalidCommand) command;
        String message = invalid.getMessage();
        assertTrue(message.contains("Please do not add anything behind list command"));
    }
}
