package jinbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for Tasklist.
 */
public class TaskListTest {

    /**
     * Tests that adding a task correctly increases the task list size by one.
     */
    @Test
    public void addTask_correctlyIncreasesSize() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("test todo 1");

        assertEquals(0, taskList.getSize());
        taskList.addTask(task1);
        assertEquals(1, taskList.getSize());
    }

    /**
     * Tests that removing a task correctly decreases the task list size by one.
     */
    @Test
    public void removeTask_correctlyDecreasesSize() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("test todo 1");
        Task task2 = new Todo("test todo 2");

        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.getSize());
        Task removedTask = taskList.removeTask(0);
        assertEquals(task1, removedTask);
        assertEquals(1, taskList.getSize());
    }

    /**
     * Tests when the task list is empty.
     */
    @Test
    public void toNumberedListItems_emptyList_returnsNoTasksMessage() {
        TaskList taskList = new TaskList();

        String expected = "No tasks yet";
        assertEquals(expected, taskList.toNumberedListItems());
    }

    /**
     * Tests that when task list contains one task.
     */
    @Test
    public void toNumberedListItems_singleItem_returnsCorrectFormat() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("test todo 1");

        taskList.addTask(task1);
        String expected = "1. [T][ ] test todo 1 [MEDIUM]";
        assertEquals(expected, taskList.toNumberedListItems());
    }

    /**
     * Tests that when task list contains multiple tasks.
     */
    @Test
    public void toNumberedListItems_multipleItems_returnsCorrectFormat() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("test todo 1");
        Task task2 = new Todo("test todo 2");

        taskList.addTask(task1);
        taskList.addTask(task2);
        String expected = "1. [T][ ] test todo 1 [MEDIUM]\n2. [T][ ] test todo 2 [MEDIUM]";
        assertEquals(expected, taskList.toNumberedListItems());
    }
}
