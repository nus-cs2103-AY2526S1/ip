package nailongbot.utils;

import nailongbot.exception.*;
import nailongbot.task.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// Tester for Task Class
class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTodo() throws EmptyDescriptionException, InvalidTaskNumberException {
        taskList.addTodo("Read book");
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0) instanceof Todo);
        assertEquals("Read book", taskList.getTask(0).getDescription());
    }

    @Test
    void testAddTodoEmptyDescription() {
        assertThrows(EmptyDescriptionException.class, () -> taskList.addTodo("   "));
    }

    @Test
    void testAddDeadline() throws EmptyDescriptionException, InvalidFormatException, InvalidTaskNumberException {
        taskList.addDeadline("Submit report /by 28/8/2025 2359");
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0) instanceof Deadline);
    }


    @Test
    void testAddDeadlineInvalidFormat() {
        assertThrows(InvalidFormatException.class,
                () -> taskList.addDeadline("Submit report 28/8/2025 2359"));
    }

    @Test
    void testAddEvent() throws EmptyDescriptionException, InvalidFormatException, InvalidTaskNumberException{
        taskList.addEvent("Conference /from 28/8/2025 1000 /to 29/8/2025 1800");
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0) instanceof Event);
        assertEquals("Conference", taskList.getTask(0).getDescription());
    }

    @Test
    void testAddEventInvalidFormat() {
        assertThrows(InvalidFormatException.class,
                () -> taskList.addEvent("Conference 28/8/2025 1000 29/8/2025 1800"));
    }

    @Test
    void testMarkAndUnmarkTask() throws EmptyDescriptionException, InvalidTaskNumberException {
        taskList.addTodo("Read book");
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
        taskList.unmarkTask(0);
        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    void testDeleteTask() throws EmptyDescriptionException, InvalidTaskNumberException {
        taskList.addTodo("Read book");
        Task deleted = taskList.deleteTask(0);
        assertEquals("Read book", deleted.getDescription());
        assertTrue(taskList.isEmpty());
    }

    @Test
    void testGetTaskInvalidIndex() {
        assertThrows(InvalidTaskNumberException.class, () -> taskList.getTask(0));
    }

    @Test
    void testGetTasksOnDate() throws EmptyDescriptionException, InvalidFormatException {
        taskList.addDeadline("Submit report /by 28/8/2025 2359");
        taskList.addEvent("Conference /from 27/8/2025 1000 /to 29/8/2025 1800");

        String output = taskList.getTasksOnDate("28/8/2025");
        assertTrue(output.contains("Submit report"));
        assertTrue(output.contains("Conference"));
    }

    @Test
    void testGetTasksOnDateNoTasks() throws InvalidFormatException {
        String output = taskList.getTasksOnDate("1/1/2050");
        assertTrue(output.contains("No tasks/events found on 1/1/2050"));
    }

    @Test
    void testGetTasksOnDateInvalidFormat() {
        assertThrows(InvalidFormatException.class,
                () -> taskList.getTasksOnDate("28.8.2025")); // invalid format with zero-padding
    }
}
