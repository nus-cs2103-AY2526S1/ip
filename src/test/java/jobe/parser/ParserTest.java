package jobe.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jobe.command.Command;
import jobe.command.DeadlineCommand;
import jobe.command.DeleteCommand;
import jobe.command.EventCommand;
import jobe.command.FindCommand;
import jobe.command.ListCommand;
import jobe.command.MarkCommand;
import jobe.command.TodoCommand;
import jobe.command.UnmarkCommand;
import jobe.exception.JobeException;
import jobe.task.DeadlineTask;
import jobe.task.EventTask;
import jobe.task.Task;
import jobe.task.TodoTask;

public class ParserTest {
    
    @Test
    public void testParseIndex() throws JobeException {
        assertEquals(0, Parser.parseTaskIndex("1"));
        assertEquals(5, Parser.parseTaskIndex("6"));
    }
    
    @Test
    public void testParseIndex_missingIndex_throwsException() {
        JobeException exception = assertThrows(JobeException.class, () -> Parser.parseTaskIndex(""));
        assertEquals("OOPS!!!! You have forgotten to input the task index!", exception.getMessage());
    }
    
    @Test
    public void testParseIndex_multipleIndex_throwsException() {
        JobeException exception = assertThrows(JobeException.class, () -> Parser.parseTaskIndex("1 1 1"));
        assertEquals("OOPS!!!! You have input multiple indexes! Please only input one index.",
                exception.getMessage());
    }
    
    @Test
    public void testParseIndex_nonNumberIndex_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> Parser.parseTaskIndex("e")
        );
        assertEquals("OOPS!!!! The index must be a number!", exception.getMessage());
    }
    
    @Test
    void parseTask_deadlineTask_returnsDeadlineTask() throws JobeException {
        String input = "D / [X] / Finish homework / 24/09/2025 1800";
        Task task = Parser.parseTask(input);
        assertInstanceOf(DeadlineTask.class, task);
    }
    
    @Test
    void parseTask_eventTask_returnsEventTask() throws JobeException {
        String input = "E / [X] / Project meeting / 24/09/2025 1800 / 24/09/2025 2000";
        Task task = Parser.parseTask(input);
        assertInstanceOf(EventTask.class, task);
    }
    
    @Test
    void parseTask_todoTask_returnsEventTask() throws JobeException {
        String input = "T / [X] / Read book";
        Task task = Parser.parseTask(input);
        assertInstanceOf(TodoTask.class, task);
    }
    
    @Test
    void parseTask_invalidTask_throwsException() {
        String input = "R / [X] / Read book";
        assertThrows(JobeException.class, () -> Parser.parseTask(input));
    }
    
    @Test
    void parse_listCommand_returnsListCommand() throws JobeException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }
    
    @Test
    void parse_markCommand_returnsMarkCommand() throws JobeException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }
    
    @Test
    void parse_unmarkCommand_returnsMarkCommand() throws JobeException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }
    
    @Test
    void parse_todoCommand_returnsTodoCommand() throws JobeException {
        Command command = Parser.parse("todo mark homework");
        assertInstanceOf(TodoCommand.class, command);
    }
    
    @Test
    void parse_deadlineCommand_returnsDeadlineCommand() throws JobeException {
        Command command = Parser.parse("deadline mark homework /by 22/02/2022 2222");
        assertInstanceOf(DeadlineCommand.class, command);
    }
    
    @Test
    void parse_eventCommand_returnsEventCommand() throws JobeException {
        Command command = Parser.parse("event project meeting /from 22/02/2022 2222 /to 22/02/2022 2333");
        assertInstanceOf(EventCommand.class, command);
    }
    
    @Test
    void parse_deleteCommand_returnsDeleteCommand() throws JobeException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }
    
    @Test
    void parse_findCommand_returnsFindCommand() throws JobeException {
        Command command = Parser.parse("find read book");
        assertInstanceOf(FindCommand.class, command);
    }
    
    @Test
    void parse_invalidCommand_throwsException() throws Exception {
        assertThrows(JobeException.class, () -> Parser.parse("help"));
    }
}
