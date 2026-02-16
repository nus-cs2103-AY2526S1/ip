package momo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Deadline} class.
 *
 * <p>These tests verify that the string representation and save format of
 * deadlines are correctly generated for both completed and uncompleted tasks.</p>
 */
public class DeadlineTest {

    /**
     * Tests the {@link Deadline#toString()} method when the deadline is marked as done.
     * Verifies that the output includes the "[X]" done marker and correctly formatted date/time.
     */
    @Test
    public void toString_done() {
        String description = "return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = true;
        Deadline deadline = new Deadline(description, by, isDone);
        String expected = "[D][X] " + description + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
        assertEquals(expected, deadline.toString());
    }

    /**
     * Tests the {@link Deadline#toString()} method when the deadline is not done.
     * Verifies that the output includes the "[ ]" marker and correctly formatted date/time.
     */
    @Test
    public void toString_notDone() {
        String description = "return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = false;
        Deadline deadline = new Deadline(description, by, isDone);
        String expected = "[D][ ] " + description + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
        assertEquals(expected, deadline.toString());
    }

    /**
     * Tests the {@link Deadline#convertToSaveFormat()} method for a completed deadline.
     * Ensures the returned string correctly encodes the task type, done status, description, and datetime.
     */
    @Test
    public void convertToSaveFormat_done() {
        String description = "return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = true;
        Deadline deadline = new Deadline(description, by, isDone);
        assertEquals("D | 1 | return book | 2025-12-02 1200", deadline.convertToSaveFormat());
    }

    /**
     * Tests the {@link Deadline#convertToSaveFormat()} method for an uncompleted deadline.
     * Ensures the returned string correctly encodes the task type, not-done status, description, and datetime.
     */
    @Test
    public void convertToSaveFormat_notDone() {
        String description = "return book";
        LocalDateTime by = LocalDateTime.of(2025, 12, 2, 12, 0);
        boolean isDone = false;
        Deadline deadline = new Deadline(description, by, isDone);
        assertEquals("D | 0 | return book | 2025-12-02 1200", deadline.convertToSaveFormat());
    }
}
