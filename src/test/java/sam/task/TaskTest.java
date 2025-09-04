package sam.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("Test task");
        String taskString = task.toString();
        String description = taskString.substring(taskString.indexOf("]") + 2).trim();
        assertEquals("Test task", description);
        assertFalse(task.isDone());
    }

    @Test
    public void testMarkDone() {
        Task task = new Task("Test task");
        assertFalse(task.isDone());

        task.markDone();
        assertTrue(task.isDone());
    }
}
