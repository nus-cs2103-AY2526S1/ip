package chirp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import chirp.exceptions.ChirpException;
import chirp.exceptions.TaskListOutOfBoundsException;

public class TaskListTest {
    @Test
    public void taskAdditionTest() throws ChirpException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Test1"));
        taskList.addTask(new Todo("Test2"));
        taskList.addTask(new Todo("Test3"));
        assertEquals(3, taskList.getNumOfTasks());
        assertEquals("[T][ ] Test1", taskList.getTask(0).toString());
        assertEquals("[T][ ] Test3", taskList.getTask(2).toString());
    }

    @Test
    public void taskMarkingTest() throws ChirpException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Test1"));
        taskList.markTask(0, true);
        assertEquals("[T][X] Test1", taskList.getTask(0).toString());
        taskList.markTask(0, false);
        assertEquals("[T][ ] Test1", taskList.getTask(0).toString());
        assertThrows(TaskListOutOfBoundsException.class, () -> {
            taskList.markTask(-1, false);
        });
        assertThrows(TaskListOutOfBoundsException.class, () -> {
            taskList.markTask(1, false);
        });
    }

    @Test
    public void taskDeletionTest() throws ChirpException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Test1"));
        taskList.addTask(new Todo("Test2"));
        taskList.addTask(new Todo("Test3"));
        assertEquals(3, taskList.getNumOfTasks());
        taskList.deleteTask(1);
        assertEquals(2, taskList.getNumOfTasks());
        assertEquals("[T][ ] Test1", taskList.getTask(0).toString());
        assertEquals("[T][ ] Test3", taskList.getTask(1).toString());
        assertThrows(TaskListOutOfBoundsException.class, () -> {
            taskList.deleteTask(-1);
        });
        assertThrows(TaskListOutOfBoundsException.class, () -> {
            taskList.deleteTask(2);
        });
    }

    @Test
    public void getTaskInvalidIndexTest() {
        // This test was generated completely using chatgpt
        TaskList taskList = new TaskList();
        assertThrows(TaskListOutOfBoundsException.class, () -> {
            taskList.getTask(0);
        });
    }

    @Test
    public void getNumOfTasksEmptyAndNonEmptyTest() throws ChirpException {
        // This test was generated completely using chatgpt
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getNumOfTasks());
        taskList.addTask(new Todo("Task"));
        assertEquals(1, taskList.getNumOfTasks());
    }

    @Test
    public void displayStrByDateTest() throws ChirpException {
        // This test was generated completely using chatgpt
        TaskList taskList = new TaskList();
        LocalDate today = LocalDate.now();
        String todayStr = today.toString();
        String tomorrowStr = today.plusDays(1).toString();

        taskList.addTask(new Deadline("Submit report", todayStr));
        taskList.addTask(new Event("Conference", todayStr, todayStr));
        taskList.addTask(new Deadline("Other task", tomorrowStr));

        String result = taskList.displayStr(today);
        // Only first two tasks are valid for today
        String expected = "1. [D][ ] Submit report (by: " + todayStr + ")\n"
                + "2. [E][ ] Conference (from: " + todayStr + " to: " + todayStr + ")\n";
        assertEquals(expected, result);
    }

    @Test
    public void displayStrByKeywordTest() throws ChirpException {
        // This test was generated completely using chatgpt
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Buy milk"));
        taskList.addTask(new Todo("Buy eggs"));
        taskList.addTask(new Todo("Clean room"));

        String result = taskList.displayStr("Buy");
        String expected = "1. [T][ ] Buy milk\n"
                + "2. [T][ ] Buy eggs\n";
        assertEquals(expected, result);
    }

    @Test
    public void displayStrNoMatchesTest() throws ChirpException {
        // This test was generated completely using chatgpt
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Wash car"));

        String result = taskList.displayStr("Nonexistent");
        assertEquals("\n", result); // should only be newline if no matches
    }
}
