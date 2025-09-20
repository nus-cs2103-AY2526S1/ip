package airy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the Deadline class.
 * Verifies that outputs match expected results after executing commands on given inputs.
 */
public class DeadlineTest {

    /**
     * Tests that a Deadline object is created correctly.
     * Verifies that the task name, deadline, and string match the expected values.
     */
    @Test
    public void testDeadlineCreation_validDeadline_success() {
        Deadline d = new Deadline("Return book", "2025-06-02");

        assertEquals("Return book", d.getTaskName());
        assertEquals("2025-06-02", d.getExtraDetailsForStorage());
        assertEquals("[D][ ] Return book (by: Jun 02 2025)", d.toString());
    }

    /**
     * Tests that marking a Deadline task as completed will update its status and String correctly.
     */
    @Test
    public void markCompleted_deadlineTask_updated() {
        Deadline d = new Deadline("Return book", "2025-06-02");
        d.markCompleted();

        assertEquals("X", d.getStatus());
        assertEquals("[D][X] Return book (by: Jun 02 2025)", d.toString());
    }
}
