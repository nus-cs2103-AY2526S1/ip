package pero.model;

import org.junit.jupiter.api.Test;
import pero.exception.PeroException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void markTask_index_shouldBeMarked() {
        TaskList tasks = new TaskList();
        Task newTestTask = null;

        try {
            newTestTask = tasks.addTaskFromInput("todo test");
        } catch (PeroException e) {
            fail("Unexpected PeroException: " + e.getMessage());
        }

        // Mark the task as done
        tasks.markTask(0);

        // Check that the task is marked
        assertTrue(newTestTask.isDone);
    }

}