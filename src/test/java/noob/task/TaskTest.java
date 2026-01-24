package noob.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import noob.exception.NoobException;

public class TaskTest {

    @Test
    public void addFourTasksToTaskList() {
        TaskList tasks = new TaskList();

        try {
            // Hard-coded list of tasks
            String date1 = "2025-06-06";
            String date2 = "2025-08-06";

            TodoTask task1 = new TodoTask("task 1");
            DeadlineTask task2 = new DeadlineTask("task 2", date1);
            EventTask task3 = new EventTask("task 3", date1, date2);
            DeadlineTask task4 = new DeadlineTask("task 4", date2);

            tasks.addTask(task1);
            tasks.addTask(task2);
            tasks.addTask(task3);
            tasks.addTask(task4);

            assertEquals(4, tasks.getNumTasks());
            assertEquals(task1, tasks.getTask(1));
            assertEquals(task2, tasks.getTask(2));
            assertEquals(task3, tasks.getTask(3));
            assertEquals(task4, tasks.getTask(4));
        } catch (NoobException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void markTasksInTaskList() {
        TaskList tasks = new TaskList();

        try {
            // Hard-coded list of tasks
            String date1 = "2025-06-06";
            String date2 = "2025-08-06";

            TodoTask task1 = new TodoTask("task 1");
            DeadlineTask task2 = new DeadlineTask("task 2", date1);
            EventTask task3 = new EventTask("task 3", date1, date2);
            DeadlineTask task4 = new DeadlineTask("task 4", date2);

            tasks.addTask(task1);
            tasks.addTask(task2);
            tasks.addTask(task3);
            tasks.addTask(task4);

            tasks.markTask(2, true);
            tasks.markTask(3, true);
            assertTrue(tasks.getTask(2).isDone());
            assertTrue(tasks.getTask(3).isDone());

            tasks.markTask(2, false);
            assertFalse(tasks.getTask(2).isDone());
        } catch (NoobException e) {
            fail(e.getMessage());
        }
    }
}
