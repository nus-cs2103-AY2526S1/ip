package finixx.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Task class.
 */
public class TaskTest {
    @Test
    public void testMarkAsDone_state() {
        TodoTask t = new TodoTask("read book", false, "");
        t.markAsDone();
        assertEquals(true, t.isDone());
    }

    @Test
    public void testMarkAsDone_string() {
        TodoTask t = new TodoTask("read book", false, "");
        t.markAsDone();
        assertEquals("[T][X] read book", t.toString());
    }

    @Test
    public void testMarkAsNotDone_state() {
        DeadlineTask d = new DeadlineTask("submit assignment", true, "CS2103T", "2025-10-30");
        d.markAsNotDone();
        assertEquals(false, d.isDone());
    }

    @Test
    public void testMarkAsNotDone_string() {
        DeadlineTask d = new DeadlineTask("submit assignment", true, "CS2103T", "2025-10-30");
        d.markAsNotDone();
        assertEquals("[D][ ] submit assignment (by: Oct 30 2025) <CS2103T>", d.toString());
    }

    @Test
    public void testGetNote() {
        TodoTask t = new TodoTask("read book", false, "Harry Potter");
        assertEquals("Harry Potter", t.getNote());
    }

    @Test
    public void testGetDescription() {
        TodoTask t = new TodoTask("read book", false, "Harry Potter");
        assertEquals("read book", t.getDescription());
    }
}
