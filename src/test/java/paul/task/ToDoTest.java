package paul.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for the To Do class
 */
public class ToDoTest {

    /**
     * Tests that the markTask() correctly sets the isDone field to true.
     */
    @Test
    public void markAsDone_shouldSetIsDoneTrue() {
        ToDo todo = new ToDo("Finish homework");
        // Check initial state
        assertFalse(todo.isDone, "Todo should be not done yet");

        // Mark as done and check
        todo.markTask();
        assertTrue(todo.isDone, "Todo should be marked as done");
    }

    /**
     * Tests that the toString() returns the correct format based on the completion status.
     */
    @Test
    public void toString_shouldShowCorrectFormat() {
        ToDo todo = new ToDo("Finish homework");
        // Not done
        assertEquals("[T][ ] Finish homework", todo.toString());

        // Done
        todo.markTask();
        assertEquals("[T][X] Finish homework", todo.toString());
    }
}
