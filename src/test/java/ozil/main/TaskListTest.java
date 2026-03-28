package ozil.main;

import ozil.exception.OzilException;
import ozil.task.DeadlineTask;
import ozil.task.TodoTask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void taskListTest() throws OzilException {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getNumberOfTasks(), "Initial size should be 0");

        TodoTask task = new TodoTask("Test task");
        tasks.addTaskToList(task);
        assertEquals(task, tasks.getTask(1), "Insert task to task list.");

        DeadlineTask task2 = new DeadlineTask("Test", "22-09-2025 1800");
        tasks.addTaskToList(task2);
        assertEquals(2, tasks.getNumberOfTasks(), "Number of tasks should be 2");
    }
}
