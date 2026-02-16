package momo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Todo} class.
 *
 * <p>These tests verify that the {@code Todo} class correctly formats
 * its string representation and save format for both completed and
 * incomplete tasks.</p>
 */
public class TodoTest {

    /**
     * Tests the {@link Todo#toString()} method for a completed task.
     * Verifies that the string representation includes the "[X]" mark.
     */
    @Test
    public void toString_done() {
        String description = "read book";
        boolean isDone = true;
        Todo todo = new Todo(description, isDone);
        assertEquals("[T][X] read book", todo.toString());
    }

    /**
     * Tests the {@link Todo#toString()} method for an incomplete task.
     * Verifies that the string representation includes the "[ ]" mark.
     */
    @Test
    public void toString_notDone() {
        String description = "read book";
        boolean isDone = false;
        Todo todo = new Todo(description, isDone);
        assertEquals("[T][ ] read book", todo.toString());
    }

    /**
     * Tests the {@link Todo#convertToSaveFormat()} method for a completed task.
     * Verifies that the returned string uses "1" to indicate completion.
     */
    @Test
    public void convertToSaveFormat_done() {
        String description = "read book";
        boolean isDone = true;
        Todo todo = new Todo(description, isDone);
        assertEquals("T | 1 | read book", todo.convertToSaveFormat());
    }

    /**
     * Tests the {@link Todo#convertToSaveFormat()} method for an incomplete task.
     * Verifies that the returned string uses "0" to indicate the task is not done.
     */
    @Test
    public void convertToSaveFormat_notDone() {
        String description = "read book";
        boolean isDone = false;
        Todo todo = new Todo(description, isDone);
        assertEquals("T | 0 | read book", todo.convertToSaveFormat());
    }
}
