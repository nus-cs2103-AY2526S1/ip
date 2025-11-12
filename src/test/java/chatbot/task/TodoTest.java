package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Finish homework");
        // Newly created todos should not be marked as done
        assertFalse(todo.isDone());
        assertEquals("[T][ ] Finish homework", todo.toString());
    }

    @Test
    public void testMarkDone() {
        Todo todo = new Todo("Finish homework");
        todo.markDone();
        assertTrue(todo.isDone());
        assertEquals("[T][X] Finish homework", todo.toString());
    }

    @Test
    public void testUnmarkDone() {
        Todo todo = new Todo("Finish homework");
        todo.markDone();
        todo.unmarkDone();
        assertFalse(todo.isDone());
        assertEquals("[T][ ] Finish homework", todo.toString());
    }
}
