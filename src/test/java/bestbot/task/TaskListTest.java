package bestbot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Tests for TaskList operations.
 */
public class TaskListTest {

    @Test
    public void addAndRemoveAndSize_behavesCorrectly() {
        TaskList list = new TaskList(); // uses empty constructor
        assertEquals(0, list.size(), "Initial size should be 0");

        Todo t1 = new Todo("task one");
        Todo t2 = new Todo("task two");
        list.add(t1);
        list.add(t2);

        assertEquals(2, list.size(), "Size should be 2 after adding two tasks");

        // Remove second task (index 1)
        Task removed = list.remove(1);
        assertNotNull(removed, "Removed task should not be null");
        assertEquals("task two", removed.toString().contains("task two") ? "task two" : removed.toString().trim(), "Removed should match");

        assertEquals(1, list.size(), "Size should be 1 after removal");
    }

    @Test
    public void getTask_returnsCorrectTask() {
        TaskList list = new TaskList();
        Todo t = new Todo("alpha");
        list.add(t);
        Task fetched = list.getTask(0);
        assertSame(t, fetched, "getTask should return same object added");
    }
}
