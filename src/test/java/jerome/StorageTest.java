package jerome;

import jerome.task.Deadline;
import jerome.task.Event;
import jerome.task.Task;
import jerome.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    void parse_validTodo_success() {
        Storage storage = new Storage("data/test.txt");
        Task task = storage.parseTask("T | 0 | read book");
        assertTrue(task instanceof Todo);
        assertEquals("read book", task.getDescription());
        assertFalse(task.getStatus());
    }

    @Test
    void parse_validDeadline_success() {
        Storage storage = new Storage("data/test.txt");
        Task task = storage.parseTask("D | 1 | return book | 2025-12-02");
        assertTrue(task instanceof Deadline);
        assertEquals("return book", task.getDescription());
        assertTrue(task.getStatus());
    }

    @Test
    void parse_validEvent_success() {
        Storage storage = new Storage("data/test.txt");
        Task task = storage.parseTask("E | 0 | project meeting | from 2025-12-12 | to 2025-12-29");
        assertTrue(task instanceof Event);
        assertEquals("project meeting", task.getDescription());
    }

    @Test
    void parse_invalidTask_exceptionThrown() {
        Storage storage = new Storage("data/test.txt");
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> {
            Task task = storage.parseTask("invalid");
        });
    }

    @Test
    void parse_incompleteTodo_exceptionThrown() {
        Storage storage = new Storage("data/test.txt");
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> {
            Task task = storage.parseTask("T | 1");
        });
    }

}
