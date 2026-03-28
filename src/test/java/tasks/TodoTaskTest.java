package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.RomidasException;

public class TodoTaskTest {
    private TodoTask todoTask;

    @BeforeEach
    public void setUp() {
        todoTask = new TodoTask("Read book");
    }

    @Test
    public void testTodoTaskCreation() {
        assertEquals("Read book", todoTask.getDescription());
        assertFalse(todoTask.isDone);
        assertEquals("[ ]", todoTask.getStatusIcon());
    }

    @Test
    public void testGetStatus() {
        assertEquals("[T]", todoTask.getStatus());
    }

    @Test
    public void testToText_NotDone() {
        String expected = "T | 0 | Read book";
        assertEquals(expected, todoTask.toText());
    }

    @Test
    public void testToText_Done() {
        todoTask.setIsDone(true);
        String expected = "T | 1 | Read book";
        assertEquals(expected, todoTask.toText());
    }

    @Test
    public void testToString_NotDone() {
        String expected = "[T][ ] Read book";
        assertEquals(expected, todoTask.toString());
    }

    @Test
    public void testToString_Done() {
        todoTask.setIsDone(true);
        String expected = "[T][X] Read book";
        assertEquals(expected, todoTask.toString());
    }

    @Test
    public void testSetIsDone() {
        assertFalse(todoTask.isDone);
        todoTask.setIsDone(true);
        assertTrue(todoTask.isDone);
        assertEquals("[X]", todoTask.getStatusIcon());
    }

    @Test
    public void testToTask_ValidInput_NotDone() throws RomidasException {
        String[] parts = {"T", "0", "Write code"};
        Task task = TodoTask.toTask(parts);
        
        assertEquals("Write code", task.getDescription());
        assertFalse(task.isDone);
        assertEquals("[T]", task.getStatus());
    }

    @Test
    public void testToTask_ValidInput_Done() throws RomidasException {
        String[] parts = {"T", "1", "Complete assignment"};
        Task task = TodoTask.toTask(parts);
        
        assertEquals("Complete assignment", task.getDescription());
        assertTrue(task.isDone);
        assertEquals("[T]", task.getStatus());
    }

    @Test
    public void testToTask_InvalidNumberOfArguments_TooFew() {
        String[] parts = {"T", "0"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            TodoTask.toTask(parts);
        });
        
        assertEquals("Invalid number of arguments. Expected 3 but got 2", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidNumberOfArguments_TooMany() {
        String[] parts = {"T", "0", "Task", "Extra"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            TodoTask.toTask(parts);
        });
        
        assertEquals("Invalid number of arguments. Expected 3 but got 4", exception.getMessage());
    }

    @Test
    public void testToTask_EmptyDescription() throws RomidasException {
        String[] parts = {"T", "0", ""};
        Task task = TodoTask.toTask(parts);
        
        assertEquals("", task.getDescription());
        assertFalse(task.isDone);
    }
}
