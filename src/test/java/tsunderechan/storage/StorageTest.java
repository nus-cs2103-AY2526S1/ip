package tsunderechan.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tsunderechan.task.Deadline;
import tsunderechan.task.Event;
import tsunderechan.task.Task;
import tsunderechan.task.TaskListStub;
import tsunderechan.task.Todo;

public class StorageTest {
    @Test
    public void tasksToString_emptyTaskList_success() {
        // empty taskList should return empty ArrayList
        assertEquals(new ArrayList<String>(),
                new Storage("").tasksToString(new TaskListStub()));
    }

    @Test
    public void taskToString_todoTaskList_success() {
        // todo task should return in proper format
        List<String> expected = new ArrayList<>();
        expected.add("T | 0 | homework");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getTodoTask()));
    }

    @Test
    public void taskToString_deadlineTaskList_success() {
        // deadline task should return in proper format
        List<String> expected = new ArrayList<>();
        expected.add("D | 0 | homework | Mar 29 2025 11:22 pm");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getDeadlineTask()));
    }

    @Test
    public void taskToString_eventTaskList_success() {
        // event task should return in proper format
        List<String> expected = new ArrayList<>();
        expected.add("E | 0 | CCA | Mar 29 2025 11:22 pm | Mar 30 2025 12:22 am");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getEventTask()));
    }

    @Test
    public void taskToString_multipleTaskList_success() {
        List<String> expected = new ArrayList<>();
        expected.add("E | 0 | CCA | Mar 29 2025 11:22 pm | Mar 30 2025 12:22 am");
        expected.add("D | 0 | homework | Mar 29 2025 11:22 pm");
        expected.add("T | 0 | homework");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getMultipleTask()));
    }

    @Test
    public void taskToString_markedTaskList_success() {
        List<String> expected = new ArrayList<>();
        expected.add("E | 1 | CCA | Mar 29 2025 11:22 pm | Mar 30 2025 12:22 am");
        expected.add("D | 1 | homework | Mar 29 2025 11:22 pm");
        expected.add("T | 1 | homework");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getMarkedTask()));
    }

    @Test
    public void stringToTasks_emptyString_exceptionThrown() {
        try {
            ArrayList<Task> expected = new ArrayList<>();
            ArrayList<Task> actual = new ArrayList<>();
            new Storage("").stringToTasks("", actual);
            assertEquals(expected, actual);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("O-oops! The save data has been corrupted..."
                    + "I guess I owe you one, so let me off this time, okay? "
                    + "I'll start you off with a clean list.", e.getMessage());
        }
    }

    @Test
    public void stringToTasks_todoString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("homework"));

        ArrayList<Task> actual = new ArrayList<>();
        new Storage("").stringToTasks("T | 0 | homework", actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void stringToTasks_deadlineString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Deadline("homework", "2025-03-29 23:22"));

        ArrayList<Task> actual = new ArrayList<>();
        new Storage("").stringToTasks("D | 0 | homework | Mar 29 2025 11:22 pm", actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void stringToTasks_eventString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Event("CCA", "2025-03-29 23:22", "2025-03-30 00:22"));

        ArrayList<Task> actual = new ArrayList<>();
        new Storage("").stringToTasks("E | 0 | CCA | Mar 29 2025 11:22 pm | Mar 30 2025 12:22 am", actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void stringToTasks_multipleString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Event("CCA", "2025-03-29 23:22", "2025-03-30 00:22"));
        expected.add(new Deadline("homework", "2025-03-29 23:22"));
        expected.add(new Todo("homework"));

        ArrayList<Task> actual = new ArrayList<>();
        Storage storage = new Storage("");
        storage.stringToTasks("E | 0 | CCA | Mar 29 2025 11:22 pm | Mar 30 2025 12:22 am", actual);
        storage.stringToTasks("D | 0 | homework | Mar 29 2025 11:22 pm", actual);
        storage.stringToTasks("T | 0 | homework", actual);
        assertEquals(expected.toString(), actual.toString());
    }
}
