package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testConstructorAndGetters() {
        Task t = new Task("Test task", TaskType.TODO);
        assertEquals("Test task", t.getDescription());
        assertFalse(t.getStatus());
    }

    @Test
    void testMarkAsDoneAndUndone() {
        Task t = new Task("Test task", TaskType.TODO);
        t.markAsDone();
        assertTrue(t.getStatus());
        t.markAsUndone();
        assertFalse(t.getStatus());
    }

    @Test
    void testToString() {
        Task t = new Task("Test task", TaskType.TODO);
        assertEquals("[ ] Test task", t.toString());
        t.markAsDone();
        assertEquals("[X] Test task", t.toString());
    }

    @Test
    void testEquals() {
        Task t1 = new Task("Task", TaskType.TODO);
        Task t2 = new Task("Task", TaskType.TODO);
        Task t3 = new Task("Task", TaskType.DEADLINE);
        Task t4 = new Task("Other task", TaskType.TODO);

        assertEquals(t1, t2);
        assertNotEquals(t1, t3);
        assertNotEquals(t1, t4);
        assertNotEquals(t1, null);
        assertNotEquals(t1, "Not a task");
    }
}
