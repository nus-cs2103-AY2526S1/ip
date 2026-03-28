package choicebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import choicebot.ChoiceBotException;

/**
 * Unit tests for the {@link Task} class.
 * <p>
 * These tests cover creating tasks, adding/deleting tasks from TaskList,
 * and loading tasks from saved string formats.
 * </p>
 */
public class TaskTest {

    /**
     * Tests that adding tasks to a TaskList increases its count.
     */
    @Test
    public void getCount_instantiateTask_increasesCount() {
        TaskList tasks = new TaskList();
        try {
            Task t1 = new Task("task1", false);
            Task t2 = new Task("task2", false);
            tasks.addTask(t1);
            tasks.addTask(t2);

            // Adding 2 tasks should result in count of 2
            assertEquals(2, tasks.getCount(), "TaskList count should be 2 after adding two tasks");

        } catch (ChoiceBotException e) {
            fail("Unexpected ChoiceBotException: " + e.getMessage());
        }
    }

    /**
     * Tests that deleting a task from TaskList decreases its count.
     */
    @Test
    public void deleteMessage_deleteTask_decreasesCount() {
        TaskList tasks = new TaskList();
        try {
            Task t1 = new Task("task1", false);
            Task t2 = new Task("task2", false);
            tasks.addTask(t1);
            tasks.addTask(t2);

            assertEquals(2, tasks.getCount(), "TaskList count should be 2 before deletion");

            tasks.deleteTask(t1);

            // Deleting 1 task should reduce count to 1
            assertEquals(1, tasks.getCount(), "TaskList count should be 1 after deletion");

        } catch (ChoiceBotException e) {
            fail("Unexpected ChoiceBotException: " + e.getMessage());
        }
    }

    /**
     * Tests loading a Todo task from its saved string representation.
     */
    @Test
    public void loadTask_loadTodoTask_printsToString() {
        try {
            Task task = Task.loadTask("T | 0 | mark work");
            assertEquals("[T][ ] mark work", task.toString(),
                    "Loaded Todo task should match expected string format");
        } catch (ChoiceBotException e) {
            fail("Unexpected ChoiceBotException: " + e.getMessage());
        }
    }

    /**
     * Tests loading an Event task from its saved string representation.
     */
    @Test
    public void loadTask_loadEventTask_printsToString() {
        try {
            Task task = Task.loadTask("E | 0 | school | 2025-09-14T14:00 | 2025-09-14T17:00");
            assertEquals("[E][ ] school (from: 2025-09-14 14:00 to: 2025-09-14 17:00)", task.toString(),
                    "Loaded Event task should match expected string format");
        } catch (ChoiceBotException e) {
            fail("Unexpected ChoiceBotException: " + e.getMessage());
        }
    }

    /**
     * Tests loading a Deadline task from its saved string representation.
     */
    @Test
    public void loadTask_loadDeadlineTask_printsToString() {
        try {
            Task task = Task.loadTask("D | 0 | work | 2019-10-15");
            assertEquals("[D][ ] work (by: Oct 15 2019)", task.toString(),
                    "Loaded Deadline task should match expected string format");
        } catch (ChoiceBotException e) {
            fail("Unexpected ChoiceBotException: " + e.getMessage());
        }
    }

    /**
     * Tests that loading an invalid task string throws ChoiceBotException.
     */
    @Test
    public void loadTask_invalidString_throwsChoiceBotException() {
        assertThrows(ChoiceBotException.class, () -> Task.loadTask("X | 0 | test"),
                "Invalid task type should throw ChoiceBotException");
    }
}
