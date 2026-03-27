package joe.parser;

import joe.exception.InvalidJoeInputException;
import joe.storage.Storage;
import joe.task.Deadline;
import joe.task.Event;
import joe.task.TaskList;
import joe.task.ToDo;
import joe.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class ParserTest {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(null);
        storage = mock(Storage.class); // mock storage so we don't write to file
        ui = mock(Ui.class); // mock UI
        parser = new Parser(taskList, storage, ui);
    }

    @Test
    void testTodoCommandAddsTask() throws InvalidJoeInputException {
        parser.executeCommand("todo Buy milk");

        assertEquals(1, taskList.getLength());
        assertTrue(taskList.getTask(0) instanceof ToDo);
        assertEquals("[T][0] Buy milk", taskList.getTask(0).toString());

        verify(storage).logTodoList(taskList);
    }

    @Test
    void testDeadlineCommandAddsTask() throws InvalidJoeInputException {
        parser.executeCommand("deadline Submit report /by 2025-05-20 2200");

        assertEquals(1, taskList.getLength());
        assertTrue(taskList.getTask(0) instanceof Deadline);
        assertEquals("[D][0] Submit report (by: May 20 2025, 10:00 pm)", taskList.getTask(0).toString());

        verify(storage).logTodoList(taskList);
    }

    @Test
    void testEventCommandAddsTask() throws InvalidJoeInputException {
        parser.executeCommand("event Conference /from 2025-01-01 /to 2025-01-05");

        assertEquals(1, taskList.getLength());
        assertTrue(taskList.getTask(0) instanceof Event);
        assertEquals("[E][0] Conference (from: Jan 01 2025 to: Jan 05 2025)", taskList.getTask(0).toString());

        verify(storage).logTodoList(taskList);
    }

    @Test
    void testMarkCommandMarksTaskAsDone() throws InvalidJoeInputException {
        parser.executeCommand("todo Read book");
        parser.executeCommand("mark 1");

        assertEquals("[T][1] Read book", taskList.getTask(0).toString());

        verify(storage, times(2)).logTodoList(taskList);
    }

    @Test
    void testUnmarkCommandMarksTaskAsNotDone() throws InvalidJoeInputException {
        parser.executeCommand("todo Read book");
        parser.executeCommand("mark 1");
        parser.executeCommand("unmark 1");

        assertEquals("[T][0] Read book", taskList.getTask(0).toString());

        verify(storage, times(3)).logTodoList(taskList);
    }

    @Test
    void testDeleteCommandRemovesTask() throws InvalidJoeInputException {
        parser.executeCommand("todo Exercise");
        parser.executeCommand("delete 1");

        assertEquals(0, taskList.getLength());

        verify(storage, times(2)).logTodoList(taskList);
    }

    @Test
    void testInvalidCommandThrowsException() {
        Exception exception = assertThrows(InvalidJoeInputException.class, () -> {
            parser.executeCommand("foobar");
        });

        assertNotNull(exception.getMessage());
    }
}
