package tinkerton.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    void testComplete_success() {
        ToDo todo = new ToDo("Read book", false);
        todo.complete();
        assertEquals("[T][X] Read book", todo.toString());
    }

    @Test
    void testUncomplete_success() {
        ToDo todo = new ToDo("Read book", true);
        todo.uncomplete();
        assertEquals("[T][ ] Read book", todo.toString());
    }

    @Test
    void testIsComplete_success() {
        ToDo todo = new ToDo("Read book", false);

        assertFalse(todo.isCompleted());

        todo.complete();

        assertTrue(todo.isCompleted());
    }

    @Test
    void testName_success() {
        ToDo todo = new ToDo("Read book", false);

        assertEquals("Read book", todo.name());
    }
}
