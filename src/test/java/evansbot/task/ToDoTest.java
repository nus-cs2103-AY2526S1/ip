package evansbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testConstructorWithDescription() {
        ToDo todo = new ToDo("read book");
        assertEquals("read book", todo.getDescription());
        assertFalse(todo.isDone(), "Task should not be done by default");
    }

    @Test
    public void testConstructorWithDescriptionAndStatus() {
        ToDo todo = new ToDo("write code", true);
        assertEquals("write code", todo.getDescription());
        assertTrue(todo.isDone(), "Task should be marked as done");
    }

    @Test
    public void testToString_NotDone() {
        ToDo todo = new ToDo("go jogging");
        assertEquals("[T][ ] go jogging", todo.toString());
    }

    @Test
    public void testToString_Done() {
        ToDo todo = new ToDo("buy groceries", true);
        assertEquals("[T][X] buy groceries", todo.toString());
    }
}