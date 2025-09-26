package winnie.tasklist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import winnie.task.Todo;
import winnie.task.Task;

public class TaskListTest {
    private TaskList taskList;
    private Task testTask;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        testTask = new Todo("Test task");
    }

    @Test
    public void constructor_emptyTaskList_returnsEmptyList() {
        assertEquals(0, taskList.getTaskCount());
    }

    @Test
    public void addTask_validTask_increasesTaskCount() {
        taskList.addTask(testTask);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    public void getTasks_afterAddingTasks_returnsCorrectTasks() {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");

        taskList.addTask(task1);
        taskList.addTask(task2);

        Task[] tasks = taskList.getTasks();
        assertEquals(2, tasks.length);
        assertEquals("Task 1", tasks[0].getDescription());
        assertEquals("Task 2", tasks[1].getDescription());
    }

    @Test
    public void markTask_validIndex_marksTaskAsDone() {
        taskList.addTask(testTask);
        Task markedTask = taskList.markTask(0);

        assertNotNull(markedTask);
        assertTrue(markedTask.isDone());
    }

    @Test
    public void unmarkTask_validIndex_marksTaskAsNotDone() {
        taskList.addTask(testTask);
        testTask.markAsDone();

        Task unmarkedTask = taskList.unmarkTask(0);

        assertNotNull(unmarkedTask);
        assertFalse(unmarkedTask.isDone());
    }

    @Test
    public void getTask_validIndex_returnsCorrectTask() {
        taskList.addTask(testTask);
        Task retrievedTask = taskList.getTask(0);

        assertNotNull(retrievedTask);
        assertEquals(testTask.getDescription(), retrievedTask.getDescription());
    }

    @Test
    public void getTask_invalidIndex_returnsNull() {
        Task result = taskList.getTask(0);
        assertNull(result);
    }

    @Test
    public void deleteTask_validIndex_removesTaskAndDecreasesCount() {
        taskList.addTask(testTask);
        Task deletedTask = taskList.deleteTask(0);

        assertNotNull(deletedTask);
        assertEquals(testTask.getDescription(), deletedTask.getDescription());
        assertEquals(0, taskList.getTaskCount());
    }

    @Test
    public void deleteTask_invalidIndex_returnsNull() {
        Task result = taskList.deleteTask(0);
        assertNull(result);
    }
}
