package sam.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("Test task");
        String taskString = task.toString();
        // Extract description from format: "[ ] [MEDIUM] Test task"
        String description = taskString.substring(taskString.lastIndexOf("]") + 2).trim();
        assertEquals("Test task", description);
        assertFalse(task.isDone());
        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    public void testMarkDone() {
        Task task = new Task("Test task");
        assertFalse(task.isDone());

        task.markDone();
        assertTrue(task.isDone());
    }
}
