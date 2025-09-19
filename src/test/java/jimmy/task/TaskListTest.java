package jimmy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testAddTask() {
        ArrayList<Task> storedTasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size(), "taskList is unable to initialise correctly");
        taskList.addTask(new ToDo("todo 1", false, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 2", false, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 3", false, Task.EMPTY_TAG));
        assertEquals(3, taskList.size(), "taskList is unable to add tasks correctly");
    }

    @Test
    public void testRemoveTask() {
        ArrayList<Task> storedTasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size(), "taskList is unable to initialise correctly");
        taskList.addTask(new ToDo("todo 1", false, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 2", false, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 3", false, Task.EMPTY_TAG));
        taskList.removeTask(2);
        assertEquals(2, taskList.size(), "taskList is unable to remove tasks correctly");
        taskList.removeTask(1);
        assertEquals(1, taskList.size(), "taskList is unable to remove tasks correctly");
        taskList.removeTask(0);
        assertEquals(0, taskList.size(), "taskList is unable to remove tasks correctly");
    }

    @Test
    public void testMarkTask() {
        ArrayList<Task> storedTasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size(), "taskList is unable to initialise correctly");
        taskList.addTask(new ToDo("todo 1", false, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 2", false, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 3", false, Task.EMPTY_TAG));
        taskList.markDone(1);
        assertTrue(taskList.getTask(1).getCompleted(), "taskList is unable to mark tasks correctly");
        taskList.markDone(2);
        assertTrue(taskList.getTask(2).getCompleted(), "taskList is unable to mark tasks correctly");
        taskList.markDone(3);
        assertTrue(taskList.getTask(3).getCompleted(), "taskList is unable to mark tasks correctly");
    }

    @Test
    public void testUnmarkTask() {
        ArrayList<Task> storedTasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size(), "taskList is unable to initialise correctly");
        taskList.addTask(new ToDo("todo 1", true, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 2", true, Task.EMPTY_TAG));
        taskList.addTask(new ToDo("todo 3", true, Task.EMPTY_TAG));
        taskList.markNotDone(1);
        assertFalse(taskList.getTask(1).getCompleted(), "taskList is unable to unmark tasks correctly");
        taskList.markNotDone(2);
        assertFalse(taskList.getTask(2).getCompleted(), "taskList is unable to unmark tasks correctly");
        taskList.markNotDone(3);
        assertFalse(taskList.getTask(3).getCompleted(), "taskList is unable to unmark tasks correctly");
    }
}
