package watermelon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import watermelon.command.Command;
import watermelon.command.DeadlineCommand;
import watermelon.command.DeleteCommand;
import watermelon.command.EventCommand;
import watermelon.command.ListCommand;
import watermelon.command.MarkCommand;
import watermelon.command.TodoCommand;
import watermelon.command.UnmarkCommand;
import watermelon.exception.WatermelonException;
import watermelon.task.Task;

// Used Claude to generate the following test cases for Parser class
public class ParserTest {
    @Test
    public void parseCommand_listCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        Command command = parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_todoCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        Command command = parser.parseCommand("todo buy groceries");
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    public void parseCommand_todoEmptyDescription_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("todo");
            fail(); // test should not reach this line
        } catch (WatermelonException e) {
            assertEquals("todo task description is empty!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        Command command = parser.parseCommand("deadline submit assignment /by 2025-12-01");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void parseCommand_deadlineEmptyDescription_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("deadline /by 2025-12-01");
            fail();
        } catch (WatermelonException e) {
            assertEquals("deadline task description is empty!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineMissingDate_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("deadline submit assignment");
            fail();
        } catch (WatermelonException e) {
            // exception thrown as expected
            assertEquals("missing date/time!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        Command command = parser.parseCommand("event team meeting /from 2pm /to 4pm");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parseCommand_eventEmptyDescription_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("event /from 2pm /to 4pm");
            fail();
        } catch (WatermelonException e) {
            assertEquals("event task description is empty!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventMissingStartDate_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("event team meeting /to 4pm");
            fail();
        } catch (WatermelonException e) {
            assertEquals("start date/time is missing!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventMissingEndDate_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("event team meeting /from 2pm");
            fail();
        } catch (WatermelonException e) {
            assertEquals("end date/time is missing!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTodo("test task"); // add a task to mark
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        Command command = parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parseCommand_markMissingTaskNumber_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("mark");
            fail();
        } catch (WatermelonException e) {
            assertEquals("missing task number!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markInvalidTaskNumber_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("mark abc");
            fail();
        } catch (WatermelonException e) {
            assertEquals("not a valid integer!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markTaskNumberOutOfRange_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("mark 5");
            fail();
        } catch (WatermelonException e) {
            assertEquals("not a valid task number!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTodo("test task");
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        Command command = parser.parseCommand("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void parseCommand_unmarkMissingTaskNumber_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("unmark");
            fail();
        } catch (WatermelonException e) {
            assertEquals("missing task number!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteCommand_success() throws Exception {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTodo("test task");
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        Command command = parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_deleteMissingTaskNumber_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("delete");
            fail();
        } catch (WatermelonException e) {
            assertEquals("missing task number!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        Parser parser = new Parser(taskList, storage, ui);

        try {
            parser.parseCommand("invalidcommand");
            fail();
        } catch (WatermelonException e) {
            // exception thrown as expected
            assertEquals("invalid command!", e.getMessage());
        }
    }
}
