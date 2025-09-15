package beebong.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    *The test cases in this class were enhanced using ChatGPT.
 */
public class TaskListTest {
    private TaskList taskList;

    // Use BeforeEach and not BeforeAll
    // to ensure every test case has a
    // fresh taskList to work with
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    // Add Task
    @Test
    public void addTask_singleTask_taskListLengthIncreases() {
        Task todo = new ToDoTask("Read book");
        taskList.addTask(todo);
        assertEquals(1, taskList.length());
    }

    @Test
    public void addTask_multipleTasks_orderIsPreserved() {
        Task t1 = new ToDoTask("Task A");
        Task t2 = new ToDoTask("Task B");
        taskList.addTask(t1);
        taskList.addTask(t2);
        System.out.println(taskList);
        assertTrue(taskList.toString().contains("1. [T][ ] Task A"));
        assertTrue(taskList.toString().contains("2. [T][ ] Task B"));
    }

    // Mark/Unmark Task
    @Test
    public void markTask_validIndex_marksAsCompleted() {
        Task todo = new ToDoTask("Read book");
        taskList.addTask(todo);

        taskList.markTaskAs(0, true);
        assertTrue(todo.isCompleted());
    }

    @Test
    public void unmarkTask_validIndex_marksAsIncomplete() {
        Task todo = new ToDoTask("Read book");
        taskList.addTask(todo);

        taskList.markTaskAs(0, true);
        taskList.markTaskAs(0, false);
        assertFalse(todo.isCompleted());
    }

    @Test
    public void markTask_invalidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTaskAs(0, true));
    }

    // Delete Task
    @Test
    public void deleteTask_validIndex_taskRemoved() {
        Task todo = new ToDoTask("Read book");
        taskList.addTask(todo);

        Task deleted = taskList.deleteTask(0);
        assertEquals(todo, deleted);
        assertEquals(0, taskList.length());
    }

    @Test
    public void deleteTask_invalidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
    }

    // Find Task
    @Test
    public void findTasks_existingKeyword_returnsMatchingTasks() {
        taskList.addTask(new ToDoTask("Read book"));
        taskList.addTask(new ToDoTask("Write essay"));

        TaskList result = taskList.findTasks("book");
        assertEquals(1, result.length());
        assertTrue(result.toString().contains("Read book"));
    }

    @Test
    public void findTasks_caseInsensitiveMatch_returnsMatchingTasks() {
        taskList.addTask(new ToDoTask("Read book"));

        TaskList result = taskList.findTasks("BOOK");
        assertEquals(1, result.length());
    }

    @Test
    public void findTasks_nonExistingKeyword_returnsEmptyTaskList() {
        taskList.addTask(new ToDoTask("Read book"));

        TaskList result = taskList.findTasks("banana");
        assertEquals(0, result.length());
    }
}
