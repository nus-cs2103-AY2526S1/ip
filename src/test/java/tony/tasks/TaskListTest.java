package tony.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class TaskListTest {
    @Test
    void testAddAndGetTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read book");

        taskList.addTask(task);

        assertEquals(1, taskList.getSize());
        assertEquals("[T][ ] Read book", taskList.getTask(1).toString());
    }

    @Test
    void testMarkDoneAndUndone() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Write code");
        taskList.addTask(task);

        // Initially undone
        assertFalse(taskList.getTask(1).isDone());

        // Mark done
        taskList.markDone(1);
        assertTrue(taskList.getTask(1).isDone());

        // Mark undone again
        taskList.markUndone(1);
        assertFalse(taskList.getTask(1).isDone());
    }

    @Test
    void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Task 1");
        Task task2 = new ToDo("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

        Task deleted = taskList.deleteTask(1);

        assertEquals("[T][ ] Task 1", deleted.toString());
        assertEquals(1, taskList.getSize());
        assertEquals("[T][ ] Task 2", taskList.getTask(1).toString());
    }
}
