package HawkerUncle.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import HawkerUncle.task.*;


public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList tasklist = new TaskList();
        Task task = new ToDo("Test task", false);
        tasklist.add(task);
        assertEquals(1, tasklist.size());
        assertTrue(tasklist.contains(task));
    }

    @Test
    public void testAddMultipleTasks() {
        TaskList tasklist = new TaskList();
        Task task1 = new ToDo("First task", false);
        Task task2 = new ToDo("Second task", true);
        tasklist.add(task1);
        tasklist.add(task2);
        assertEquals(2, tasklist.size());
    }
}
