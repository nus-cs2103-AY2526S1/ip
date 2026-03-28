package vicky.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import vicky.storage.Storage;

public class TaskListTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testTaskListConstructor() {
        assertNotNull(taskList.tasks);
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testAddTask() {
        Todo todo = new Todo("Test Task");
        taskList.addTask(todo);
        assertEquals(1, taskList.len());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void testGetTask() {
        Todo todo = new Todo("Test Task");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void testMarkTask() {
        Todo todo = new Todo("Test Task");
        taskList.addTask(todo);
        taskList.markTask(0);
        assertTrue(todo.isDone());
    }

    @Test
    public void testUnmarkTask() {
        Todo todo = new Todo("Test Task", true);
        taskList.addTask(todo);
        taskList.unmarkTask(0);
        assertFalse(todo.isDone());
    }

    @Test
    public void testDeleteTask() {
        Todo todo1 = new Todo("Test Task 1");
        Todo todo2 = new Todo("Test Task 2");
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        Task deletedTask = taskList.deleteTask(1);
        assertEquals(todo1, deletedTask);
        assertEquals(1, taskList.len());
    }

    @Test
    public void testToString() {
        Todo todo1 = new Todo("Test Task 1");
        Todo todo2 = new Todo("Test Task 2");
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        String expected = "    1. [T] [ ] Test Task 1\n    2. [T] [ ] Test Task 2\n";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testMarkTaskOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTask(999));
    }

    @Test
    public void testUnmarkTaskOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.unmarkTask(999));
    }

    @Test
    public void testDeleteTaskOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(999));
    }
}
