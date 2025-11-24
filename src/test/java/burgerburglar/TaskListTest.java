package burgerburglar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for TaskList.
 */
public class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new Todo("Eat burger");
        task2 = new Todo("Drink milkshake");
    }

    // Test constructors
    @Test
    public void testEmptyConstructor() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void testConstructorWithInitialTasks() {
        List<Task> initialTasks = new ArrayList<>();
        initialTasks.add(task1);
        initialTasks.add(task2);
        TaskList nonEmptyTaskList = new TaskList(initialTasks);
        assertEquals(2, nonEmptyTaskList.size());
        assertTrue(nonEmptyTaskList.getTasks().contains(task1));
        assertTrue(nonEmptyTaskList.getTasks().contains(task2));
    }

    // Test addTask
    @Test
    public void testAddTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTasks().contains(task1));
    }

    @Test
    public void testAddMultipleTasks() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.size());
        assertTrue(taskList.getTasks().contains(task1));
        assertTrue(taskList.getTasks().contains(task2));
    }

    // Test markTask (mark as done and undone)
    @Test
    public void testMarkTaskAsDone() {
        taskList.addTask(task1);
        Task updatedTask = taskList.markTask(0, true);
        assertTrue(updatedTask.isDone());
    }

    @Test
    public void testMarkTaskAsUndone() {
        taskList.addTask(task1);
        taskList.markTask(0, true);
        Task updatedTask = taskList.markTask(0, false);
        assertFalse(updatedTask.isDone());
    }

    // Test deleteTask
    @Test
    public void testDeleteTask() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        Task removed = taskList.deleteTask(1); // delete first task (1-based index)
        assertEquals(task1, removed);
        assertEquals(1, taskList.size());
        assertFalse(taskList.getTasks().contains(task1));
    }

    @Test
    public void testDeleteTaskFromEmptyList() {
        assertThrows(AssertionError.class, () -> taskList.deleteTask(1));
    }

    @Test
    public void testDeleteTaskInvalidIndex() {
        taskList.addTask(task1);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0)); // too low
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(2)); // too high
    }

    // Test size
    @Test
    public void testSize() {
        assertEquals(0, taskList.size());
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
    }

    // Test getTasks returns a copy (not the original list)
    @Test
    public void testGetTasksReturnsCopy() {
        taskList.addTask(task1);
        List<Task> tasks = taskList.getTasks();
        tasks.add(task2);
        // Original taskList should not be affected
        assertEquals(1, taskList.size());
        assertFalse(taskList.getTasks().contains(task2));
    }

    @Test
    public void testGetTasksNotSameReference() {
        taskList.addTask(task1);
        List<Task> tasks = taskList.getTasks();
        assertNotSame(taskList.getTasks(), tasks); // should be a different object
    }

    // Test toString
    @Test
    public void testToStringEmptyList() {
        String expected = "ALL DONE. BURGER!\n";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testToStringNonEmptyList() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        String result = taskList.toString();
        assertTrue(result.contains("1. " + task1));
        assertTrue(result.contains("2. " + task2));
        assertTrue(result.startsWith("YOU SHOULD GET STARTED SOON:"));
    }
}
