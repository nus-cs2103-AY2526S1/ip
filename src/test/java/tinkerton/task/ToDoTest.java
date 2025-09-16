package tinkerton.task;

import org.junit.jupiter.api.Test;
import tinkerton.util.Date;
import tinkerton.core.TinkertonException;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    @Test
    void testToString_notCompleted_success() {
        ToDo todo = new ToDo("Read book", false);
        assertEquals("[T][ ] Read book", todo.toString());
    }

    @Test
    void testToString_completed_success() {
        ToDo todo = new ToDo("Read book", true);
        assertEquals("[T][X] Read book", todo.toString());
    }

    @Test
    void testToFile_notCompleted_success() {
        ToDo todo = new ToDo("Read book", false);
        assertEquals("T | 0 | Read book", todo.toFile());
    }

    @Test
    void testToFile_completed_success() {
        ToDo todo = new ToDo("Read book", true);
        assertEquals("T | 1 | Read book", todo.toFile());
    }

    @Test
    void testOnDate_alwaysFalse_success() {
        ToDo todo = new ToDo("Read book", false);
        Date date = null;
        try {
            date = new Date("2025-08-25 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(todo.onDate(date));
    }
}
