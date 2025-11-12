package seedu.haru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testMarkUnmark() {
        Task todo = new ToDo("read book", Type.TODO);
        assertFalse(todo.isDone);
        todo.mark();
        assertTrue(todo.isDone);
        todo.unmark();
        assertFalse(todo.isDone);
    }

    @Test
    void testToString() {
        Task todo = new ToDo("read book", Type.TODO);
        assertEquals("[T][ ] read book", todo.toString());
        todo.mark();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    void testToFileString() {
        Task todo = new ToDo("read book", Type.TODO);
        assertEquals("T | 0 | read book", todo.toFileString());
        todo.mark();
        assertEquals("T | 1 | read book", todo.toFileString());
    }

    @Test
    void testFromFileStringToDo() {
        String line = "T | 1 | read book";
        Task task = Task.fromFileString(line);
        assertTrue(task instanceof ToDo);
        assertTrue(task.isDone);
        assertEquals("read book", task.name);
    }

    @Test
    void testFromFileStringDeadline() {
        String line = "D | 0 | submit assignment | 2025-10-30";
        Task task = Task.fromFileString(line);
        assertTrue(task instanceof Deadline);
        assertFalse(task.isDone);
        assertEquals("submit assignment", task.name);
        assertEquals("2025-10-30", ((Deadline) task).end.toString());
    }

    @Test
    void testFromFileStringEvent() {
        String line = "E | 1 | meeting | 12:00 | 10:00";
        Task task = Task.fromFileString(line);
        assertTrue(task instanceof Event);
        assertTrue(task.isDone);
        assertEquals("meeting", task.name);
        assertEquals("10:00", ((Event) task).start);
        assertEquals("12:00", ((Event) task).end);
    }

    @Test
    void testFromFileStringInvalid() {
        String line = "X | 0 | unknown";
        Task task = Task.fromFileString(line);
        assertNull(task);
    }
}

