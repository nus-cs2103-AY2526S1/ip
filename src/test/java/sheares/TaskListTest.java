package sheares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sheares.task.Event;
import sheares.task.Task;
import sheares.task.Todo;

//these tests were generated with the help of DeepSeek
public class TaskListTest {

    private TaskList taskList;
    private Todo testTask;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        testTask = new Todo("Test task");
    }

    @Test
    public void testConstructorWithEmptyArrayList() {
        TaskList emptyTaskList = new TaskList(new ArrayList<>());
        assertEquals(0, emptyTaskList.size());
    }

    @Test
    public void testConstructorWithPrepopulatedArrayList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));

        TaskList prepopulatedTaskList = new TaskList(tasks);
        assertEquals(2, prepopulatedTaskList.size());
    }

    @Test
    public void testDefaultConstructor() {
        TaskList defaultTaskList = new TaskList();
        assertEquals(0, defaultTaskList.size());
    }

    @Test
    public void testSizeOnEmptyList() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void testSizeAfterAddingTasks() {
        taskList.add(testTask);
        assertEquals(1, taskList.size());

        taskList.add(new Todo("Another task"));
        assertEquals(2, taskList.size());
    }

    @Test
    public void testSizeAfterAddingAndRemoving() {
        taskList.add(testTask);
        taskList.add(new Todo("Task to remove"));
        assertEquals(2, taskList.size());

        taskList.delete(0);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testAddSingleTask() {
        taskList.add(testTask);
        assertEquals(1, taskList.size());
        assertEquals(testTask, taskList.get(0));
    }

    @Test
    public void testAddMultipleTasks() {
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        Todo task3 = new Todo("Task 3");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        assertEquals(3, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        assertEquals(task3, taskList.get(2));
    }

    @Test
    public void testDeleteFirstTask() {
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);

        taskList.delete(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    @Test
    public void testDeleteLastTask() {
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);

        taskList.delete(1);
        assertEquals(1, taskList.size());
        assertEquals(task1, taskList.get(0));
    }

    @Test
    public void testDeleteMiddleTask() {
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        Todo task3 = new Todo("Task 3");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        taskList.delete(1);
        assertEquals(2, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task3, taskList.get(1));
    }

    @Test
    public void testDeleteFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.delete(0);
        });
    }

    @Test
    public void testDeleteWithNegativeIndex() {
        taskList.add(testTask);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.delete(-1);
        });
    }

    @Test
    public void testDeleteWithIndexOutOfBounds() {
        taskList.add(testTask);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.delete(1); // Only index 0 exists
        });
    }

    @Test
    public void testGetFirstTask() {
        taskList.add(testTask);
        Todo anotherTask = new Todo("Another task");
        taskList.add(anotherTask);

        assertEquals(testTask, taskList.get(0));
    }

    @Test
    public void testGetLastTask() {
        taskList.add(testTask);
        Todo anotherTask = new Todo("Another task");
        taskList.add(anotherTask);

        assertEquals(anotherTask, taskList.get(1));
    }

    @Test
    public void testGetFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.get(0);
        });
    }

    @Test
    public void testGetWithNegativeIndex() {
        taskList.add(testTask);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.get(-1);
        });
    }

    @Test
    public void testGetWithIndexOutOfBounds() {
        taskList.add(testTask);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.get(1); // Only index 0 exists
        });
    }

    @Test
    public void testTaskOrderPreservation() {
        Todo task1 = new Todo("First task");
        Todo task2 = new Todo("Second task");
        Todo task3 = new Todo("Third task");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        assertEquals(task3, taskList.get(2));
    }

    @Test
    public void testMultipleOperationsSequence() {
        // Test a sequence of operations
        assertEquals(0, taskList.size());

        Todo task1 = new Todo("Task 1");
        taskList.add(task1);
        assertEquals(1, taskList.size());
        assertEquals(task1, taskList.get(0));

        Todo task2 = new Todo("Task 2");
        taskList.add(task2);
        assertEquals(2, taskList.size());
        assertEquals(task2, taskList.get(1));

        taskList.delete(0);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));

        Todo task3 = new Todo("Task 3");
        taskList.add(task3);
        assertEquals(2, taskList.size());
        assertEquals(task3, taskList.get(1));
    }

    @Test
    public void testTaskEqualityAfterStorageAndRetrieval() {
        Todo originalTask = new Todo("Test task description");
        taskList.add(originalTask);

        Task retrievedTask = taskList.get(0);
        assertEquals(originalTask, retrievedTask);
        assertEquals(originalTask.getDescription(), retrievedTask.getDescription());
    }

    public void sizeAndAddTest() {
        TaskList ls = new TaskList();
        ls.add(new Todo("read books"));
        ls.add(new Event("party", "2pm", "6pm"));
        assertEquals(2, ls.size());
    }

    @Test
    public void getTest() {
        TaskList ls = new TaskList();
        ls.add(new Todo("read books"));
        ls.add(new Event("party", "2pm", "6pm"));
        Task retrieve = ls.get(0);
        assertEquals("[T][ ] read books", retrieve.toString());
    }

    @Test
    public void deleteTest() {
        TaskList ls = new TaskList();
        ls.add(new Todo("read books"));
        ls.add(new Event("party", "2pm", "6pm"));
        ls.delete(0);
        Task retrieve = ls.get(0);
        assertEquals("[E][ ] party (from: 2pm to: 6pm)", retrieve.toString());
    }
}
