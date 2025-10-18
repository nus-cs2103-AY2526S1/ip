package charli.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Buy concert tickets");
        assertEquals("Buy concert tickets", todo.getDescription());
        assertFalse(todo.isDone());
        assertEquals("[T][ ] Buy concert tickets", todo.toString());
    }

    @Test
    public void testTodoMarkAsDone() {
        Todo todo = new Todo("Buy concert tickets");
        todo.markAsDone();
        assertTrue(todo.isDone());
        assertEquals("[T][✓] Buy concert tickets", todo.toString());
    }

    @Test
    public void testTodoMarkAsNotDone() {
        Todo todo = new Todo("Buy concert tickets");
        todo.markAsDone();
        todo.markAsNotDone();
        assertFalse(todo.isDone());
        assertEquals("[T][ ] Buy concert tickets", todo.toString());
    }
}