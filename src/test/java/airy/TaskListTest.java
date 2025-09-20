package airy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for the TaskList class.
 * Verifies that outputs match expected results.
 */
public class TaskListTest {

    /**
     * Tests that adding a Task to TaskList
     * increases the list size and stores the task correctly.
     */
    @Test
    public void addTask_validTask_success() {
        // Empty ArrayList
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Read book");

        tasks.addTask(todo);

        assertEquals(1, tasks.getSize());
        assertEquals(todo, tasks.getTasks().get(0));
    }

    /**
     * Tests that marking a task at a valid index in TaskList updates its status.
     */
    @Test
    public void markTask_validIndex_taskMarked() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Borrow book");

        tasks.addTask(todo);
        Task taskMark = tasks.markTask(0);

        assertEquals("X", taskMark.getStatus());
    }
}
