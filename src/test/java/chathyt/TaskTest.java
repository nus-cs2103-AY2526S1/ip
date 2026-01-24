package chathyt;

import chathyt.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the {@link Task} class.
 * Tests marking and unmarking of tasks, and correct string formatting.
 */
public class TaskTest {

    /**
     * Tests that calling {@link Task#markAsDone()} correctly
     * sets the task as done and updates the status icon.
     */
    @Test
    public void markAsDone_setIsDoneTrue() {
        Task task = new Task("read book");
        task.markAsDone();
        Assertions.assertTrue(task.isDone, "Task should be marked as done");
        Assertions.assertEquals("X", task.getStatusIcon());
    }

    /**
     * Tests that calling {@link Task#unmarkAsDone()} correctly
     * sets the task as not done and updates the status icon.
     */
    @Test
    public void unmarkAsDone_setIsDoneFalse() {
        Task task = new Task("read book");
        task.markAsDone();
        task.unmarkAsDone();
        Assertions.assertFalse(task.isDone, "Task should be unmarked as done");
        Assertions.assertEquals(" ", task.getStatusIcon());
    }

    /**
     * Tests that {@link Task#toString()} formats the task correctly,
     * reflecting its description and done status.
     */
    @Test
    public void toString_formatCorretly() {
        Task task = new Task("read book");
        Assertions.assertEquals("[ ] read book", task.toString());
        task.markAsDone();
        Assertions.assertEquals("[X] read book", task.toString());
    }
}
