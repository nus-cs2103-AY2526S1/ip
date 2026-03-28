package clare.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clare.exception.StringConvertExceptions;

class TaskListTest {
    private Todo todo1;
    private Deadline deadline1;
    private Event event1;

    @BeforeEach
    void setUp() {
        todo1 = new Todo("read book", false);
        try {
            deadline1 = new Deadline("submit report", "2024-08-30", false);
            event1 = new Event("team meeting", "2024-09-01", "2024-09-01", false);
        } catch (StringConvertExceptions e) {
            return;
        }
    }

    @Test
    void testAdd() {
        TaskList taskList = new TaskList();
        taskList.add(todo1);
        assertEquals(1, taskList.size());
        taskList.add(deadline1);
        assertEquals(2, taskList.size());
    }

    @Test
    void testDelete() throws StringConvertExceptions {
        TaskList taskList = new TaskList();
        taskList.add(todo1);
        taskList.add(deadline1);
        Task deletedTask = taskList.delete(0);
        assertEquals(1, taskList.size());
        assertEquals(todo1, deletedTask);
        assertEquals("1. [D][ ] submit report (by: Aug 30 2024)", taskList.getAllTaskString());
    }

    @Test
    void testMarkDone() throws StringConvertExceptions {
        TaskList taskList = new TaskList();
        taskList.add(todo1); // Initially undone
        Task markedTask = taskList.markDone(0);
        assertEquals("[T][X] read book", markedTask.toString());
        assertEquals("1. [T][X] read book", taskList.getAllTaskString());
    }

    @Test
    void testMarkUndone() throws StringConvertExceptions {
        TaskList taskList = new TaskList();
        Task doneTodo = new Todo("completed task", true);
        taskList.add(doneTodo); // Initially done
        Task unmarkedTask = taskList.markUndone(0);
        assertEquals("[T][ ] completed task", unmarkedTask.toString());
        assertEquals("1. [T][ ] completed task", taskList.getAllTaskString());
    }

    @Test
    void testSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
        taskList.add(todo1);
        assertEquals(1, taskList.size());
        taskList.add(deadline1);
        taskList.add(event1);
        assertEquals(3, taskList.size());
    }

    @Test
    void testGetAllTaskString() {
        TaskList taskList = new TaskList();
        taskList.add(todo1);
        taskList.add(deadline1);
        taskList.add(event1);
        String expected = "1. [T][ ] read book\n"
                + "2. [D][ ] submit report (by: Aug 30 2024)\n"
                + "3. [E][ ] team meeting (from: Sep 1 2024 to: Sep 1 2024)";
        assertEquals(expected, taskList.getAllTaskString());
    }

    @Test
    void testGetAllTaskSaveString() {
        TaskList taskList = new TaskList();
        taskList.add(todo1);
        taskList.add(deadline1);
        taskList.add(event1);
        String expected = "T|0|read book\n"
                + "D|0|submit report|2024-08-30\n"
                + "E|0|team meeting|2024-09-01|2024-09-01\n";
        assertEquals(expected, taskList.getAllTaskSaveString());
    }

    @Test
    void testFindTaskByDeadline_noMatchingDeadline() {
        TaskList taskList = new TaskList();
        taskList.add(todo1);
        taskList.add(event1);
        LocalDate searchDate = LocalDate.parse("2024-08-30");
        String result = taskList.findTaskByDeadline(searchDate);
        assertEquals("No task found.", result);
    }

    @Test
    void testFindTaskByDeadline_matchingDeadline() {
        TaskList taskList = new TaskList();
        taskList.add(todo1);
        taskList.add(deadline1);
        taskList.add(event1);
        LocalDate searchDate = LocalDate.parse("2024-08-30");
        String result = taskList.findTaskByDeadline(searchDate);
        String expected = "[D][ ] submit report (by: Aug 30 2024)";
        assertEquals(expected, result.trim());
    }

    @Test
    void testFindTaskByDeadline_multipleMatchingDeadlines() {
        try {
            TaskList taskList = new TaskList();
            taskList.add(new Deadline("urgent work", "2024-08-30", false));
            taskList.add(new Todo("another todo", false));
            taskList.add(new Deadline("follow up", "2024-08-30", true));
            LocalDate searchDate = LocalDate.parse("2024-08-30");
            String result = taskList.findTaskByDeadline(searchDate);
            String expected = "[D][ ] urgent work (by: Aug 30 2024)\n"
                    + "[D][X] follow up (by: Aug 30 2024)";
            assertEquals(expected, result.trim());
        } catch (StringConvertExceptions e) {
            assertEquals(1, 2); // make an assertion failure
        }
    }
}
