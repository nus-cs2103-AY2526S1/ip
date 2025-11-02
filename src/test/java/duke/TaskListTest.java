package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {
    @Test
    public void constructor_emptyList_sizeIsZero() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void add_oneTask_sizeIsOne() {
        TaskList taskList = new TaskList();
        Task task = new Todo("test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
    }
}
