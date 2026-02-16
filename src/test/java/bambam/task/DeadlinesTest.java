package bambam.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains unit tests for testing the behaviour of Deadline class.
 */
public class DeadlinesTest {
    /**
     * Test that the printTaskString() correctly prints the string required
     * for the printing of list to users.
     */
    @Test
    public void printTaskStringTest() {
        Deadlines deadline = new Deadlines("Return Book", "2025-01-01");
        String taskString = deadline.printTaskString();
        assertEquals("[D][ ] Return Book (by: Jan 1 2025)", taskString);
    }
}
