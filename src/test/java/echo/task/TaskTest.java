package echo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testIsDoneStatus() {
        Task task = new Task("description");
        assertFalse(task.isDone);

        task.markAsDone();
        assertTrue(task.isDone);

        task.markAsUndone();
        assertFalse(task.isDone);
    }

    @Test
    public void testStatusIcon() {
        Task task = new Task("description");
        assertEquals(" ", task.getStatusIcon());

        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testStatusNumber() {
        Task task = new Task("description");
        assertEquals("0", task.getStatusNumber());

        task.markAsDone();
        assertEquals("1", task.getStatusNumber());
    }
}
