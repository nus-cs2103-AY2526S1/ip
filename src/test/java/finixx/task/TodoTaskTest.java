package finixx.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the TodoTask class.
 */
public class TodoTaskTest {
    @Test
    public void testToSaveFormatString_doneTask_withoutNotes() {
        TodoTask t = new TodoTask("read book", true, "");
        assertEquals("T | 1 | read book | ", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_doneTask_withNotes() {
        TodoTask t = new TodoTask("read book", true, "Harry Potter");
        assertEquals("T | 1 | read book | Harry Potter", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_undoneTask_withoutNotes() {
        TodoTask t = new TodoTask("read book", false, "");
        assertEquals("T | 0 | read book | ", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_undoneTask_withNotes() {
        TodoTask t = new TodoTask("read book", false, "Harry Potter");
        assertEquals("T | 0 | read book | Harry Potter", t.toSaveFormatString());
    }

    @Test
    public void testToString_doneTask_withoutNotes() {
        TodoTask t = new TodoTask("read book", true, "");
        assertEquals("[T][X] read book", t.toString());
    }

    @Test
    public void testToString_doneTask_withNotes() {
        TodoTask t = new TodoTask("read book", true, "Harry Potter");
        assertEquals("[T][X] read book <Harry Potter>", t.toString());
    }

    @Test
    public void testToString_undoneTask_withoutNotes() {
        TodoTask t = new TodoTask("read book", false, "");
        assertEquals("[T][ ] read book", t.toString());
    }

    @Test
    public void testToString_undoneTask_withNotes() {
        TodoTask t = new TodoTask("read book", false, "Harry Potter");
        assertEquals("[T][ ] read book <Harry Potter>", t.toString());
    }

    @Test
    public void testSaveAndLoadConsistency() {
        TodoTask original = new TodoTask("read book", false, "Harry Potter");
        String saveFormat = original.toSaveFormatString();
        Task loaded = Task.fromSaveFormat(saveFormat);

        assertEquals(original.toSaveFormatString(), loaded.toSaveFormatString());
    }
}
