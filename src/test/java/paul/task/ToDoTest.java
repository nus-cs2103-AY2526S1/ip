package paul.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {

    @Test
    public void markAsDone_shouldSetIsDoneTrue() {
        ToDo todo = new ToDo("Finish homework");
        // Check initial state
        assertFalse(todo.isDone, "Todo should be not done yet");

        // Mark as done and check
        todo.markTask();
        assertTrue(todo.isDone, "Todo should be marked as done");
    }

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
