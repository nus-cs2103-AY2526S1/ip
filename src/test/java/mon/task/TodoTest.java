package mon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TodoTest {
    @Test
    void testTodoCreation() {
        Todo todo = new Todo("Test todo");
        assertEquals("Test todo", todo.getTaskName());
        assertFalse(todo.getStatus());
    }

    @Test
    void testTodoCreationWithStatus() {
        Todo todo = new Todo("Test todo", true);
        assertEquals("Test todo", todo.getTaskName());
        assertTrue(todo.getStatus());
    }

    @Test
    void testToFileString() {
        Todo todo = new Todo("Test todo");
        assertEquals("T | 0 | Test todo", todo.toFileString());
        
        todo.setStatus(true);
        assertEquals("T | 1 | Test todo", todo.toFileString());
    }

    @Test
    void testToString() {
        Todo todo = new Todo("Test todo");
        assertEquals("[T][ ] Test todo", todo.toString());
        
        todo.setStatus(true);
        assertEquals("[T][X] Test todo", todo.toString());
    }

    @Test
    void testToTodoTask() {
        String taskString = "T | 1 | Read book";
        Todo todo = Todo.toTodoTask(taskString);
        assertEquals("Read book", todo.getTaskName());
        assertTrue(todo.getStatus());
    }
}
