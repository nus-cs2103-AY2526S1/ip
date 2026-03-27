package pepe.task.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.task.EmptyTask;
import pepe.task.Task;
import pepe.task.ToDos;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        taskList.addTask(new ToDos("Task 1"));
        taskList.addTask(new ToDos("Task 2"));
        taskList.addTask(new ToDos("Task 3"));
    }

    @Test
    void testAddAndSize() {
        assertEquals(3, taskList.size());
        taskList.addTask(new ToDos("Task 4"));
        assertEquals(4, taskList.size());
    }

    @Test
    void testDeleteTask() {
        Task deleted = taskList.deleteTask(1);
        assertEquals("Task 2", deleted.getName());
        assertTrue(taskList.get(1) instanceof EmptyTask);
    }

    @Test
    void testWipe() {
        taskList.deleteTask(0);
        taskList.deleteTask(2);
        taskList.wipe();
        assertEquals(1, taskList.size());
        assertEquals("Task 2", taskList.get(0).getName());
    }

    @Test
    void testIsEmpty() throws PepeExceptions {
        assertFalse(taskList.isEmpty());
        TaskList emptyList = new TaskList();
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void testGet() {
        Task task = taskList.get(0);
        assertEquals("Task 1", task.getName());
    }

    @Test
    void testFindTask() {
        TaskList found = taskList.findTask("1");
        assertEquals(1, found.size());
        assertEquals("Task 1", found.get(0).getName());

        TaskList notFound = taskList.findTask("xyz");
        assertEquals(0, notFound.size());
    }

    @Test
    void testGetTaskList() {
        ArrayList<Task> underlyingList = taskList.getTaskList();
        assertEquals(3, underlyingList.size());
        assertEquals("Task 1", underlyingList.get(0).getName());
    }

    @Test
    void testDeleteTaskIndexOutOfBounds() {
        assertThrows(AssertionError.class, () -> taskList.deleteTask(-1));
        assertThrows(AssertionError.class, () -> taskList.deleteTask(3));
    }

    @Test
    void testGetIndexOutOfBounds() {
        assertThrows(AssertionError.class, () -> taskList.get(-1));
        assertThrows(AssertionError.class, () -> taskList.get(3));
    }
}
