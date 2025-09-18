package jimbot.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jimbot.exception.NoSuchTaskException;
import jimbot.exception.TaskLimitException;

/**
 * Unit tests for the TaskList class.
 * Covers adding, retrieving, deleting, searching, and sorting tasks.
 * Note: AI assistance was used to aid in writing these test cases.
 *
 */
public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddAndGetTask() throws TaskLimitException {
        Task todo = new ToDo("Read book");
        taskList.addToList(todo);

        assertEquals(1, taskList.getTaskCount());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    void testAddBeyondLimit_throwsTaskLimitException() throws TaskLimitException {
        for (int i = 0; i < 99; i++) {
            taskList.addToList(new ToDo("Task " + i));
        }

        assertThrows(TaskLimitException.class, () ->
                taskList.addToList(new ToDo("Overflow task")));
    }

    @Test
    void testDeleteTask() throws TaskLimitException {
        Task task = new ToDo("Write tests");
        taskList.addToList(task);
        taskList.deleteFromList(task);

        assertEquals(0, taskList.getTaskCount());
    }

    @Test
    void testFindTasks_byDescription() throws TaskLimitException, NoSuchTaskException {
        Task todo = new ToDo("Read book");
        Task deadline = new Deadline("Submit report", LocalDateTime.now().plusDays(1));

        taskList.addToList(todo);
        taskList.addToList(deadline);

        TaskList results = taskList.findTasks("Read");

        assertEquals(1, results.getTaskCount());
        assertTrue(results.getTask(0).getDescription().contains("Read"));
    }

    @Test
    void testFindTasks_noMatch_throwsException() throws TaskLimitException {
        taskList.addToList(new ToDo("Go jogging"));

        assertThrows(NoSuchTaskException.class, () -> taskList.findTasks("Swim"));
    }

    @Test
    void testFindTasksAtDate_withDeadline() throws TaskLimitException, NoSuchTaskException {
        LocalDateTime dueDate = LocalDateTime.of(2025, 9, 18, 23, 59);
        Task deadline = new Deadline("Finish assignment", dueDate);

        taskList.addToList(deadline);

        TaskList results = taskList.findTasksAtDate(dueDate.toLocalDate());
        assertEquals(1, results.getTaskCount());
        assertEquals(deadline, results.getTask(0));
    }

    @Test
    void testFindTasksAtDate_withEvent() throws TaskLimitException, NoSuchTaskException {
        LocalDateTime start = LocalDateTime.of(2025, 9, 20, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 9, 22, 18, 0);
        Task event = new Event("Conference", start, end);

        taskList.addToList(event);

        TaskList results = taskList.findTasksAtDate(LocalDate.of(2025, 9, 21));
        assertEquals(1, results.getTaskCount());
        assertEquals(event, results.getTask(0));
    }

    @Test
    void testFindTasksAtDate_noMatch_throwsException() throws TaskLimitException {
        LocalDateTime start = LocalDateTime.of(2025, 9, 20, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 9, 22, 18, 0);
        Task event = new Event("Conference", start, end);

        taskList.addToList(event);

        assertThrows(NoSuchTaskException.class, () ->
                taskList.findTasksAtDate(LocalDate.of(2025, 10, 1)));
    }

    @Test
    void testSortTasks_ordersByDate() throws TaskLimitException {
        Task todo = new ToDo("General task");
        Task deadline = new Deadline("Submit report", LocalDateTime.of(2025, 9, 20, 12, 0));
        Task event = new Event("Seminar",
                LocalDateTime.of(2025, 9, 19, 9, 0),
                LocalDateTime.of(2025, 9, 19, 17, 0));

        taskList.addToList(todo);
        taskList.addToList(deadline);
        taskList.addToList(event);

        List<Task> sorted = taskList.getTaskList();

        assertEquals(event, sorted.get(0));
        assertEquals(deadline, sorted.get(1));
        assertEquals(todo, sorted.get(2));
    }
}
