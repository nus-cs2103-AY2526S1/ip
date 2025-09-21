import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import sofi.TaskList;
import sofi.Todo;
import sofi.Deadline;
import sofi.Task;

public class TaskListTest {
    private TaskList taskList;
    private Todo todo1;
    private Todo todo2;
    private Deadline deadline1;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        todo1 = new Todo("read book");
        todo2 = new Todo("write essay");
        deadline1 = new Deadline("submit assignment", "2019-12-15");
    }

    @Test
    public void testEmptyTaskList() {
        assertEquals(0, taskList.size());
        assertTrue(taskList.getTasks().isEmpty());
    }

    @Test
    public void testAddTask() {
        taskList.addTask(todo1);
        assertEquals(1, taskList.size());
        assertEquals(todo1, taskList.getTask(0));
        assertTrue(taskList.getTasks().contains(todo1));
    }

    @Test
    public void testAddMultipleTasks() {
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        taskList.addTask(deadline1);
        
        assertEquals(3, taskList.size());
        assertEquals(todo1, taskList.getTask(0));
        assertEquals(todo2, taskList.getTask(1));
        assertEquals(deadline1, taskList.getTask(2));
    }

    @Test
    public void testRemoveTask() {
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        taskList.addTask(deadline1);
        
        Task removed = taskList.removeTask(1);
        assertEquals(todo2, removed);
        assertEquals(2, taskList.size());
        assertEquals(todo1, taskList.getTask(0));
        assertEquals(deadline1, taskList.getTask(1));
    }

    @Test
    public void testRemoveTaskFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.removeTask(0);
        });
    }

    @Test
    public void testRemoveTaskInvalidIndex() {
        taskList.addTask(todo1);
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.removeTask(5);
        });
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.removeTask(-1);
        });
    }

    @Test
    public void testGetTask() {
        taskList.addTask(todo1);
        taskList.addTask(deadline1);
        
        assertEquals(todo1, taskList.getTask(0));
        assertEquals(deadline1, taskList.getTask(1));
    }

    @Test
    public void testGetTaskInvalidIndex() {
        taskList.addTask(todo1);
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getTask(5);
        });
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getTask(-1);
        });
    }

    @Test
    public void testMarkTaskAsDone() {
        taskList.addTask(todo1);
        assertFalse(todo1.isDone());
        
        taskList.markTask(0, true);
        assertTrue(todo1.isDone());
    }

    @Test
    public void testMarkTaskAsNotDone() {
        taskList.addTask(todo1);
        todo1.markAsDone();
        assertTrue(todo1.isDone());
        
        taskList.markTask(0, false);
        assertFalse(todo1.isDone());
    }

    @Test
    public void testMarkTaskInvalidIndex() {
        taskList.addTask(todo1);
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.markTask(5, true);
        });
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.markTask(-1, true);
        });
    }

    @Test
    public void testIsValidIndex() {
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        
        assertTrue(taskList.isValidIndex(0));
        assertTrue(taskList.isValidIndex(1));
        assertFalse(taskList.isValidIndex(2));
        assertFalse(taskList.isValidIndex(-1));
        assertFalse(taskList.isValidIndex(5));
    }

    @Test
    public void testIsValidIndexEmptyList() {
        assertFalse(taskList.isValidIndex(0));
        assertFalse(taskList.isValidIndex(-1));
        assertFalse(taskList.isValidIndex(1));
    }

    @Test
    public void testConstructorWithTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo1);
        tasks.add(deadline1);
        
        TaskList newTaskList = new TaskList(tasks);
        assertEquals(2, newTaskList.size());
        assertEquals(todo1, newTaskList.getTask(0));
        assertEquals(deadline1, newTaskList.getTask(1));
    }

    @Test
    public void testGetTasksReturnsCorrectList() {
        taskList.addTask(todo1);
        taskList.addTask(deadline1);
        
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(todo1));
        assertTrue(tasks.contains(deadline1));
    }
}
