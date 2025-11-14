package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for removing tasks from the Tasklist.
 */
public class RemoveListTest {

    /**
     * Tests if removeFromList correctly removes a task.
     */
    @Test
    public void removeTask_validTask_taskRemoved() {
        Tasklist taskList = new Tasklist();
        Task task = new Todo("read book");
        taskList.addToList(task);
        taskList.removeFromList(1);

        assertEquals(0, Tasklist.listSize());
    }
}

