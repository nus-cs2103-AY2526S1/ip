package seedu.mani.task;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import seedu.mani.storage.Storage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the TaskList components.
 */
public class TaskListTest {

    /**
     * Tests the addTask functionality by verifying if task is added.
     */
    @Test
    public void addTaskTest() throws IOException {
        TaskList taskList = new TaskList(Storage.loadTasks("data/test.txt"));

        String output = taskList.addTask(new Todo("rad book"), "data/test.txt");

        assertEquals(1, taskList.getNum());
        assertTrue(taskList.toString().contains("rad book"));
        assertTrue(output.contains("I've added this task"));
        taskList.deleteTask(0, "data/test.txt");
    }

    /**
     * Tests deleteTask functionality by verifying a task is removed.
     */
    @Test
    public void deleteTaskTest() throws IOException {
        TaskList taskList = new TaskList(Storage.loadTasks("data/test.txt"));
        taskList.addTask(new Todo("read book"), "data/test.txt");

        String output = taskList.deleteTask(0, "data/test.txt");

        assertEquals(0, taskList.getNum());
        assertFalse(taskList.toString().contains("read book"));
        assertTrue(output.contains("I've removed this task"));
    }
}
