package bambam.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contains unit tests for testing the behaviour of Task class.
 */
public class TaskTest {
    /**
     * Tests that the markAsDone() method correctly marks input task as done.
     */
    @Test
    public void markAsDoneTest() {
        Task todoTask = new ToDos("Return Book");
        todoTask.markAsDone();
        assertTrue(todoTask.getIsDone());
    }
}
