package fish.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    public void todo_initiallyNotDone() {
        Todo todo = new Todo("read book");
        assertFalse(todo.isDone, "A new Todo should not be marked as done initially");
    }

    @Test
    public void markAsDone_todoShouldBeDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertTrue(todo.isDone, "Todo should be marked done after calling markAsDone()");
    }

    @Test
    public void toString_format() {
        Todo todo = new Todo("read book");
        String expected = "[T][ ] read book";
        assertEquals(expected, todo.toString());
    }
}
