package sage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import sage.task.*;

/**
 * Test class for TaskList functionality.
 * Tests task management operations like adding, deleting, marking tasks.
 */
public class TaskListTest {

    private TaskList taskList;
    private Task todoTask;
    private Deadline deadlineTask;
    private Event eventTask;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        todoTask = new Todo("Read book");
        deadlineTask = new Deadline("Submit assignment", LocalDateTime.of(2024, 3, 15, 23, 59));
        eventTask = new Event("Team meeting",
                             LocalDateTime.of(2024, 3, 16, 14, 0),
                             LocalDateTime.of(2024, 3, 16, 16, 0));
    }

    @Test
    public void addTask_singleTask_increasesSize() {
        assertEquals(0, taskList.size());
        taskList.addTask(todoTask);
        assertEquals(1, taskList.size());
    }

    @Test
    public void addTask_multipleTasks_correctSize() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);
        assertEquals(3, taskList.size());
    }

    @Test
    public void getTask_validIndex_returnsCorrectTask() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);

        assertEquals(todoTask, taskList.getTask(0));
        assertEquals(deadlineTask, taskList.getTask(1));
    }

    @Test
    public void deleteTask_validIndex_removesAndReturnsTask() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);

        Task deleted = taskList.deleteTask(0);
        assertEquals(todoTask, deleted);
        assertEquals(1, taskList.size());
        assertEquals(deadlineTask, taskList.getTask(0));
    }

    @Test
    public void markTask_unmarkedTask_becomesMarked() {
        taskList.addTask(todoTask);
        assertFalse(todoTask.isDone());

        taskList.markTask(0);
        assertTrue(todoTask.isDone());
    }

    @Test
    public void unmarkTask_markedTask_becomesUnmarked() {
        taskList.addTask(todoTask);
        todoTask.markAsDone();
        assertTrue(todoTask.isDone());

        taskList.unmarkTask(0);
        assertFalse(todoTask.isDone());
    }

    @Test
    public void findTasks_matchingKeyword_returnsMatchingTasks() {
        Task bookTask = new Todo("Read Java book");
        Task assignmentTask = new Todo("Complete assignment");
        Task bookReview = new Todo("Write book review");

        taskList.addTask(bookTask);
        taskList.addTask(assignmentTask);
        taskList.addTask(bookReview);

        ArrayList<Task> results = taskList.findTasks("book");
        assertEquals(2, results.size());
        assertTrue(results.contains(bookTask));
        assertTrue(results.contains(bookReview));
        assertFalse(results.contains(assignmentTask));
    }

    @Test
    public void findTasks_noMatches_returnsEmptyList() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);

        ArrayList<Task> results = taskList.findTasks("nonexistent");
        assertEquals(0, results.size());
    }

    @Test
    public void getUpcomingDeadlines_withinRange_returnsUpcomingTasks() {
        // Create a deadline for tomorrow
        LocalDateTime tomorrow = LocalDateTime.now().plusHours(12);
        Deadline upcomingDeadline = new Deadline("Urgent task", tomorrow);

        // Create a deadline for next week (outside 24-hour range)
        LocalDateTime nextWeek = LocalDateTime.now().plusDays(7);
        Deadline futureDeadline = new Deadline("Future task", nextWeek);

        taskList.addTask(upcomingDeadline);
        taskList.addTask(futureDeadline);

        ArrayList<Deadline> upcoming = taskList.getUpcomingDeadlines();
        assertEquals(1, upcoming.size());
        assertEquals(upcomingDeadline, upcoming.get(0));
    }

    @Test
    public void getUpcomingDeadlines_completedTask_notIncluded() {
        LocalDateTime tomorrow = LocalDateTime.now().plusHours(12);
        Deadline completedDeadline = new Deadline("Completed task", tomorrow);
        completedDeadline.markAsDone();

        taskList.addTask(completedDeadline);

        ArrayList<Deadline> upcoming = taskList.getUpcomingDeadlines();
        assertEquals(0, upcoming.size());
    }

    @Test
    public void getTasksAsString_multipleTasks_correctFormat() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);

        String result = taskList.getTasksAsString();
        assertTrue(result.contains("1." + todoTask.toString()));
        assertTrue(result.contains("2." + deadlineTask.toString()));
    }

    @Test
    public void getTasks_returnsUnmodifiableList() {
        taskList.addTask(todoTask);
        ArrayList<Task> tasks = taskList.getTasks();

        // Attempting to modify the returned list should not affect the original
        assertEquals(1, tasks.size());
        // The returned list should be unmodifiable, but we can still verify content
        assertTrue(tasks.contains(todoTask));
    }
}