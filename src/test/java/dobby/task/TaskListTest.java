package dobby;

import dobby.exceptions.InvalidTaskException;
import dobby.task.Task;
import dobby.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TaskListTest {

    /**
     * Tests that deleting a valid index removes the correct task,
     * and that deleting an invalid index throws InvalidTaskException.
     */
    @Test
    void delete_validAndInvalidIndex() throws InvalidTaskException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        // Delete valid index
        assertEquals("Task 1", tasks.deleteTask(0).getDescription());
        assertEquals(1, tasks.size());

        // Delete invalid index
        Exception exception = assertThrows(InvalidTaskException.class, () -> {
            tasks.deleteTask(5);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    /**
     * Tests that getTask returns the correct task for a valid index,
     * and throws InvalidTaskException for an invalid index.
     */
    @Test
    void getTask_validAndInvalidIndex() throws InvalidTaskException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task A"));

        // Valid index
        assertEquals("Task A", tasks.getTask(0).getDescription());

        // Invalid index
        Exception exception = assertThrows(InvalidTaskException.class, () -> {
            tasks.getTask(2);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }

    /**
     * Tests that getTasks returns a copy of the task list
     * and not the internal mutable list.
     */
    @Test
    void getTasks_returnsCopy() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task X"));

        ArrayList<Task> copy = tasks.getTasks();
        assertEquals(1, copy.size());

        // Modify copy
        copy.clear();
        assertEquals(1, tasks.size(), "Original list should remain unchanged");
    }

    /**
     * Tests that findTasks returns tasks containing the keyword (case-insensitive),
     * and returns an empty list if no matches are found.
     */
    @Test
    void findTasks_withMatchesAndNoMatches() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Buy groceries"));
        tasks.addTask(new ToDo("Read book"));

        // Case-insensitive match
        ArrayList<Task> matches = tasks.findTasks("buy");
        assertEquals(1, matches.size());
        assertEquals("Buy groceries", matches.get(0).getDescription());

        // No matches
        ArrayList<Task> noMatches = tasks.findTasks("exercise");
        assertTrue(noMatches.isEmpty());
    }
}
