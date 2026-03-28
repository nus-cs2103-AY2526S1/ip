package tweety.tasks;

import org.junit.jupiter.api.Test;
import tweety.exceptions.TweetyException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void deleteTask_invalidTaskNumber_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new ToDo("iP");
        tasks.add(task);
        TaskList taskList = new TaskList(tasks);

        int[] invalidNumbers = {-1, 0, 4, 5};

        for (int invalidNumber : invalidNumbers) {
            TweetyException exception = assertThrows(TweetyException.class,
                    () -> taskList.deleteTask(invalidNumber));
            assertEquals("Aww, hon, please give me a valid task number!", exception.getMessage());
        }
    }

    @Test
    public void deleteTask_validTaskNumber_todoTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new ToDo("iP");
        tasks.add(task);
        TaskList taskList = new TaskList(tasks);
        try {
            assertEquals(task, taskList.deleteTask(1));
            assertEquals(0, taskList.getAllTasks().size());
            assertTrue(taskList.getAllTasks().isEmpty());
        } catch (TweetyException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_validTaskNumber_deadlineTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Task task = new Deadline("iP", "2025-09-30");
            tasks.add(task);
            TaskList taskList = new TaskList(tasks);

            try {
                assertEquals(task, taskList.deleteTask(1));
                assertEquals(0, taskList.getAllTasks().size());
                assertTrue(taskList.getAllTasks().isEmpty());
            } catch (TweetyException e) {
                fail();
            }

        } catch (TweetyException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_validTaskNumber_eventTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Event("meeting", "3pm Monday", "5pm Monday");
        tasks.add(task);
        TaskList taskList = new TaskList(tasks);
        try {
            assertEquals(task, taskList.deleteTask(1));
            assertEquals(0, taskList.getAllTasks().size());
            assertTrue(taskList.getAllTasks().isEmpty());
        } catch (TweetyException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_multipleTasks_correctTaskRemoved() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new ToDo("Task 1");
        Task task2 = new ToDo("Task 2");
        Task task3 = new Event("Task 3", "3pm Monday", "5pm Monday");
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        TaskList taskList = new TaskList(tasks);

        try {
            assertEquals(task2, taskList.deleteTask(2));
            assertEquals(2, taskList.getAllTasks().size());
            assertEquals(task1, taskList.getAllTasks().get(0));
            assertEquals(task3, taskList.getAllTasks().get(1));
        } catch (TweetyException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_emptyList_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        TweetyException exception = assertThrows(TweetyException.class,
                () -> taskList.deleteTask(1));
        assertEquals("Aww, hon, please give me a valid task number!", exception.getMessage());
    }


}
