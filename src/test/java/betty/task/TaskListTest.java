package betty.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import betty.exception.BettyException;

// Test class for TaskList, test case generated using GitHub Copilot
class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddAndSize() throws BettyException {
        assertEquals(0, taskList.size());
        taskList.addTodo(new Todo("Read book", false));
        assertEquals(1, taskList.size());
        taskList.addDeadline(new Deadline("Submit report", LocalDate.parse("2024-06-01"), false));
        assertEquals(2, taskList.size());
        taskList.addEvent(new Event("Meeting", LocalDate.parse("2024-06-02"), LocalDate.parse("2024-06-02"), false));
        assertEquals(3, taskList.size());
    }

    @Test
    void testGet() throws BettyException {
        Todo todo = new Todo("Read book", false);
        taskList.addTodo(todo);
        assertEquals(todo, taskList.get(0));
    }

    @Test
    void testMarkDoneAndUndone() throws BettyException {
        Todo todo = new Todo("Read book", false);
        taskList.addTodo(todo);
        taskList.markDone(1);
        assertEquals("X", taskList.get(0).getStatusIcon());
        taskList.markUndone(1);
        assertEquals(" ", taskList.get(0).getStatusIcon());
    }

    @Test
    void testDeleteTask() throws BettyException {
        taskList.addTodo(new Todo("Read book", false));
        taskList.addTodo(new Todo("Write code", false));
        assertEquals(2, taskList.size());
        taskList.deleteTask(1);
        assertEquals(1, taskList.size());
        assertEquals("Write code", taskList.get(0).getDescription());
    }

    @Test
    void testFind() throws BettyException {
        taskList.addTodo(new Todo("Read book", false));
        taskList.addTodo(new Todo("Write code", false));
        TaskList found = taskList.find("Write");
        assertEquals(1, found.size());
        assertEquals("Write code", found.get(0).getDescription());
    }

    @Test
    void testToStringAndToSaveString() throws BettyException {
        taskList.addTodo(new Todo("Read book", false));
        String str = taskList.toString();
        String saveStr = taskList.toSaveString();
        assertTrue(str.contains("Read book"));
        assertTrue(saveStr.contains("Read book"));
    }
}
