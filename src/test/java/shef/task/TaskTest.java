package shef.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void taskCorrectDescriptionTest() {
        Task todoTask = new TodoTask("test number one");
        assertEquals("test number one", todoTask.getDescription());
    }

    @Test
    public void toStringTest() {
        Task todoTask = new TodoTask("test");
        assertEquals("[T][ ] test", todoTask.toString());
        todoTask.markAsDone();
        assertEquals("[T][X] test", todoTask.toString());
    }

    @Test
    public void taskNotDoneByDefaultTest() {
        Task todoTask = new TodoTask("test Tasks");
        assertFalse(todoTask.getIsDone());
        assertEquals(' ', todoTask.getStatusIcon());
    }

    @Test
    public void markTaskTest() {
        Task todoTask = new TodoTask("test");
        todoTask.markAsDone();
        assertTrue(todoTask.getIsDone());
        assertEquals('X', todoTask.getStatusIcon());
    }
}
