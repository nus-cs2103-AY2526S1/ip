package timmy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ToDo}.
 */
public class ToDoTest {

    @Test
    void creation_setsDescriptionAndIncomplete() {
        ToDo todo = new ToDo("Write tests");

        assertEquals("Write tests", todo.toString());
        assertFalse(todo.isDone, "New ToDo should not be marked as done");
    }

    @Test
    void markAsDone_reflectsInOutput() {
        ToDo todo = new ToDo("Finish homework");
        todo.markAsDone();

        assertTrue(todo.isDone);
        assertEquals("[T][X] Finish homework", todo.toCompleteString());
        assertEquals("T | 1 | Finish homework", todo.toFileString());
    }

    @Test
    void markAsNotDone_reflectsInOutput() {
        ToDo todo = new ToDo("Review code");
        todo.markAsDone();
        todo.markAsNotDone();

        assertFalse(todo.isDone);
        assertEquals("[T][ ] Review code", todo.toCompleteString());
        assertEquals("T | 0 | Review code", todo.toFileString());
    }
}
