package poopiemeow.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new TestTask("Test task");
        assertEquals("Test task", task.description);
        assertFalse(task.isDone);
    }

    @Test
    void testMarkAsDone() {
        Task task = new TestTask("Test task");
        task.markAsDone();
        assertTrue(task.isDone);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void testMarkAsUndone() {
        Task task = new TestTask("Test task");
        task.markAsDone();
        task.markAsUndone();
        assertFalse(task.isDone);
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    void testToString() {
        Task task = new TestTask("Test task");
        assertEquals("[ ] Test task", task.toString());

        task.markAsDone();
        assertEquals("[X] Test task", task.toString());
    }

    // Concrete test implementation of abstract Task class
    private static class TestTask extends Task {
        public TestTask(String description) {
            super(description);
        }

        @Override
        public String toFileString() {
            return "TEST|" + (isDone ? "1" : "0") + "|" + description;
        }
    }
}
