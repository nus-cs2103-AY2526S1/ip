package poopiemeow.task;

import poopiemeow.exception.EmptyDescriptionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testTodoCreation() throws EmptyDescriptionException {
        Todo todo = new Todo("Test todo");
        assertEquals("Test todo", todo.description);
        assertFalse(todo.isDone);
    }

    @Test
    void testTodoWithEmptyDescription() {
        assertThrows(EmptyDescriptionException.class, () -> {
            new Todo("");
        });
    }

    @Test
    void testTodoWithWhitespaceOnly() {
        assertThrows(EmptyDescriptionException.class, () -> {
            new Todo("   ");
        });
    }

    @Test
    void testTodoToFileString() throws EmptyDescriptionException {
        Todo todo = new Todo("Test todo");
        assertEquals("T|0|Test todo", todo.toFileString());

        todo.markAsDone();
        assertEquals("T|1|Test todo", todo.toFileString());
    }

    @Test
    void testTodoToString() throws EmptyDescriptionException {
        Todo todo = new Todo("Test todo");
        assertEquals("[ ] Test todo", todo.toString());

        todo.markAsDone();
        assertEquals("[X] Test todo", todo.toString());
    }

    @Test
    void testTodoMarkAsDone() throws EmptyDescriptionException {
        Todo todo = new Todo("Test todo");
        assertFalse(todo.isDone);
        assertEquals(" ", todo.getStatusIcon());

        todo.markAsDone();
        assertTrue(todo.isDone);
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    void testTodoMarkAsUndone() throws EmptyDescriptionException {
        Todo todo = new Todo("Test todo");
        todo.markAsDone();
        assertTrue(todo.isDone);

        todo.markAsUndone();
        assertFalse(todo.isDone);
        assertEquals(" ", todo.getStatusIcon());
    }
}
