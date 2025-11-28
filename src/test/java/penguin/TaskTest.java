package penguin;

import org.junit.jupiter.api.Test;

import penguin.tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetDescription() {
        Task task = new Task("testing task");
        assertEquals("testing task", task.getDescription());
    }

    @Test
    public void testGetStatusIcon() {
        Task task = new Task("testing task");
        assertEquals("[ ]", task.getStatusIcon());
        task.markAsDone();
        assertEquals("[X]", task.getStatusIcon());
        task.markAsUndone();
        assertEquals("[ ]", task.getStatusIcon());
    }
}
