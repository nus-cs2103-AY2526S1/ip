package watermelon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import watermelon.exception.InvalidDateTimeException;
import watermelon.exception.InvalidInputException;
import watermelon.task.Task;

// Used Claude to generate the following test cases for TaskList class
public class TaskListTest {

    @Test
    public void size_emptyList_returnsZero() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        assertEquals(0, taskList.getSize());
    }

    @Test
    public void size_listWithTasks_returnsCorrectSize() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.addTodo("buy milk");

        assertEquals(1, taskList.getSize());
    }

    @Test
    public void saveToFile_emptyList_writesNothing() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        taskList.saveToFile(printWriter);
        printWriter.close();

        assertEquals("", stringWriter.toString());
    }

    @Test
    public void saveToFile_listWithTasks_writesTasksToFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.addTodo("buy milk");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        taskList.saveToFile(printWriter);
        printWriter.close();

        String output = stringWriter.toString();
        assertTrue(output.contains("buy milk"));
    }

    @Test
    public void addTodo_validDescription_taskAdded() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        taskList.addTodo("buy groceries");

        assertEquals(1, taskList.getSize());
    }

    @Test
    public void addDeadline_validInput_taskAdded() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        taskList.addDeadline("submit assignment", "01122023 2359");

        assertEquals(1, taskList.getSize());
    }

    @Test
    public void addDeadline_invalidDate_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        try {
            taskList.addDeadline("submit assignment", "invalid-date");
            fail();
        } catch (InvalidDateTimeException e) {
            // exception thrown as expected
        }
    }

    @Test
    public void addEvent_validInput_taskAdded() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        taskList.addEvent("team meeting", "01122025 1400", "01122025 1600");

        assertEquals(1, taskList.getSize());
    }

    @Test
    public void addEvent_invalidDate_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        try {
            taskList.addEvent("team meeting", "invalid-date", "01122025 1600");
            fail();
        } catch (InvalidDateTimeException | InvalidInputException e) {
            // exception thrown as expected
        }
    }

    @Test
    public void markTask_validTaskNumber_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.addTodo("buy milk");

        taskList.markTask(1);

        // Test passes if no exception is thrown
    }

    @Test
    public void unmarkTask_validTaskNumber_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.addTodo("buy milk");

        taskList.unmarkTask(1);

        // Test passes if no exception is thrown
    }

    @Test
    public void deleteTask_validTaskNumber_taskRemoved() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        taskList.addTodo("buy milk");
        taskList.addTodo("walk dog");

        taskList.deleteTask(1);

        assertEquals(1, taskList.getSize());
    }
}
