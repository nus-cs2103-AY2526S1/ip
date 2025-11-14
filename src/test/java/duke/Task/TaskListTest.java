package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for adding tasks to the Tasklist.
 */
public class TaskListTest {

    /**
     * Tests if addToList correctly adds a task.
     */
    @Test
    public void addTask_validTask_taskAdded() {
        Tasklist taskList = new Tasklist();
        Task task = new Todo("read book");
        taskList.addToList(task);

        assertEquals(1, Tasklist.listSize());
    }
}
