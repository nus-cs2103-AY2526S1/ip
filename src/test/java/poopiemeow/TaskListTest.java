package poopiemeow;

import poopiemeow.task.Task;
import poopiemeow.task.Todo;
import poopiemeow.exception.EmptyDescriptionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class TaskListTest {

    private TaskList taskList;
    private Task testTask;

    @BeforeEach
    void setUp() throws EmptyDescriptionException {
        taskList = new TaskList();
        testTask = new Todo("Test task");
    }

    @Test
    void testTaskListCreation() {
        assertNotNull(taskList);
        assertEquals(0, taskList.size());
    }

    @Test
    void testTaskListWithInitialTasks() throws EmptyDescriptionException {
        ArrayList<Task> initialTasks = new ArrayList<>();
        initialTasks.add(new Todo("Task 1"));
        initialTasks.add(new Todo("Task 2"));

        TaskList taskListWithTasks = new TaskList(initialTasks);
        assertEquals(2, taskListWithTasks.size());
    }

    @Test
    void testAddTask() throws EmptyDescriptionException {
        assertEquals(0, taskList.size());

        taskList.addTask(testTask);
        assertEquals(1, taskList.size());

        Task secondTask = new Todo("Second task");
        taskList.addTask(secondTask);
        assertEquals(2, taskList.size());
    }

    @Test
    void testDeleteTask() throws EmptyDescriptionException {
        taskList.addTask(testTask);
        Task secondTask = new Todo("Second task");
        taskList.addTask(secondTask);

        assertEquals(2, taskList.size());

        Task deletedTask = taskList.deleteTask(0);
        assertEquals(testTask, deletedTask);
        assertEquals(1, taskList.size());

        Task remainingTask = taskList.getTask(0);
        assertEquals(secondTask, remainingTask);
    }

    @Test
    void testGetTask() throws EmptyDescriptionException {
        taskList.addTask(testTask);

        Task retrievedTask = taskList.getTask(0);
        assertEquals(testTask, retrievedTask);
        assertEquals("Test task", retrievedTask.toString().substring(4)); // Remove "[ ] " prefix
    }

    @Test
    void testGetTasks() throws EmptyDescriptionException {
        taskList.addTask(testTask);
        Task secondTask = new Todo("Second task");
        taskList.addTask(secondTask);

        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(testTask));
        assertTrue(tasks.contains(secondTask));
    }

    @Test
    void testSize() throws EmptyDescriptionException {
        assertEquals(0, taskList.size());

        taskList.addTask(testTask);
        assertEquals(1, taskList.size());

        taskList.addTask(new Todo("Another task"));
        assertEquals(2, taskList.size());

        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
    }

    @Test
    void testTaskListOperations() throws EmptyDescriptionException {
        // Test adding multiple tasks
        for (int i = 0; i < 5; i++) {
            taskList.addTask(new Todo("Task " + i));
        }
        assertEquals(5, taskList.size());

        // Test deleting from middle
        Task deletedTask = taskList.deleteTask(2);
        assertEquals("Task 2", deletedTask.toString().substring(4)); // Remove "[ ] " prefix
        assertEquals(4, taskList.size());

        // Test deleting from end
        Task lastTask = taskList.deleteTask(3);
        assertEquals("Task 4", lastTask.toString().substring(4)); // Remove "[ ] " prefix
        assertEquals(3, taskList.size());

        // Test deleting from beginning
        Task firstTask = taskList.deleteTask(0);
        assertEquals("Task 0", firstTask.toString().substring(4)); // Remove "[ ] " prefix
        assertEquals(2, taskList.size());

        // Verify remaining tasks
        assertEquals("Task 1", taskList.getTask(0).toString().substring(4)); // Remove "[ ] " prefix
        assertEquals("Task 3", taskList.getTask(1).toString().substring(4)); // Remove "[ ] " prefix
    }

    @Test
    void testTaskListWithDifferentTaskTypes() throws EmptyDescriptionException {
        taskList.addTask(new Todo("Todo task"));
        taskList.addTask(new poopiemeow.task.Deadline("Deadline task", "2023-12-25 1430"));
        taskList.addTask(new poopiemeow.task.Event("Event task", "2023-12-25 1400", "2023-12-25 1600"));

        assertEquals(3, taskList.size());

        // Verify task types
        assertTrue(taskList.getTask(0) instanceof Todo);
        assertTrue(taskList.getTask(1) instanceof poopiemeow.task.Deadline);
        assertTrue(taskList.getTask(2) instanceof poopiemeow.task.Event);
    }
}
