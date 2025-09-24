package cortana.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cortana.exception.CortanaException;

/**
 * Unit tests for the TaskList class.
 * Tests adding, deleting tasks and exception handling.
 */
class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up a fresh TaskList instance before each test.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests that adding any task increases the size of the task list.
     */
    @Test
    void add_anyTask_sizeShouldIncrease() {
        taskList.add(new ToDo("Read book"));
        assertEquals(1, taskList.size());
        taskList.add(new Deadline("Return book", LocalDateTime.now().plusHours(1)));
        assertEquals(2, taskList.size());
        taskList.add(new Event("Report book findings", LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2)));
        assertEquals(3, taskList.size());
    }

    /**
     * Tests that adding a task returns a string containing an added message and the task count.
     */
    @Test
    void add_anyTask_returnStringShouldContainAddedMessage() {
        String result = taskList.add(new ToDo("Read book"));
        assertTrue(result.contains("Added:"));
        assertTrue(result.contains("1"));
    }

    /**
     * Tests that adding a duplicate task does not increase the size of the task list.
     * Adding a duplicate task should return a string indicating the duplicate.
     */
    @Test
    void add_duplicateTask_returnStringShouldIndicateDuplicate() {
        taskList.add(new ToDo("Read book"));
        String result = taskList.add(new ToDo("Read book"));
        assertTrue(result.contains("already in your list"));
        assertEquals(1, taskList.size());
        // Use a fixed date to ensure equality
        LocalDateTime by = LocalDateTime.now().plusHours(1);
        taskList.add(new Deadline("Return book", by));
        result = taskList.add(new Deadline("Return book", by));
        assertTrue(result.contains("already in your list"));
        assertEquals(2, taskList.size());
        // Use fixed dates to ensure equality
        LocalDateTime from = LocalDateTime.now().plusHours(1);
        LocalDateTime to = LocalDateTime.now().plusHours(2);
        taskList.add(new Event("Report book findings", from, to));
        result = taskList.add(new Event("Report book findings", from, to));
        assertTrue(result.contains("already in your list"));
        assertEquals(3, taskList.size());
    }

    /**
     * Tests that deleting a valid task reduces the task list size.
     * Deleting a task should not throw a CortanaException.
     */
    @Test
    void delete_validTaskNumber_sizeShouldDecrease() {
        taskList.add(new ToDo("Read book"));
        try {
            taskList.delete(1);
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertEquals(0, taskList.size());
    }

    /**
     * Tests that deleting with invalid task numbers throws a CortanaException.
     * Covers edge cases like 0-index input and inputs beyond current size.
     */
    @Test
    void delete_invalidTaskNumber_exceptionThrown() {
        taskList.add(new ToDo("Read book"));
        // User may think task number starts from 0
        assertThrows(CortanaException.class, () -> taskList.delete(0));
        // User may mistakenly think there are more items than present
        assertThrows(CortanaException.class, () -> taskList.delete(2));
    }

    /**
     * Tests that deleting a valid task returns a string containing a deleted message.
     * Deleting a task should not throw a CortanaException.
     */
    @Test
    void delete_validTaskNumber_returnStringShouldContainDeletedMessage() {
        taskList.add(new ToDo("Read book"));
        String result = null;
        try {
            result = taskList.delete(1);
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertTrue(result.contains("Deleted:"));
        assertTrue(result.contains("0"));
    }
    /**
     * Tests that marking a valid task updates its status as done.
     */
    @Test
    void mark_validTaskNumber_xShouldAppearInBrackets() {
        taskList.add(new ToDo("Read book"));
        String result = null;
        try {
            result = taskList.mark(1);
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertTrue(result.contains("[X]"));
    }
    /**
     * Tests that marking a valid task returns a string containing a marked message.
     * Marking a task should not throw a CortanaException.
     */
    @Test
    void mark_invalidTaskNumber_exceptionThrown() {
        taskList.add(new ToDo("Read book"));
        // User may think task number starts from 0
        assertThrows(CortanaException.class, () -> taskList.mark(0));
        // User may mistakenly think there are more items than present
        assertThrows(CortanaException.class, () -> taskList.mark(2));
    }
    /**
     * Tests that marking an already marked task throws a CortanaException.
     */
    @Test
    void mark_alreadyMarkedTask_exceptionThrown() {
        taskList.add(new ToDo("Read book"));
        try {
            taskList.mark(1);
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertThrows(CortanaException.class, () -> taskList.mark(1));
    }
    /**
     * Tests that unmarking a valid task updates its status as not done.
     */
    @Test
    void unmark_validTaskNumber_blankShouldAppearInBrackets() {
        taskList.add(new ToDo("Read book"));
        String result = null;
        try {
            taskList.mark(1);
            result = taskList.unmark(1);
        } catch (CortanaException e) {
            // This should not happen
            fail();
        }
        assertTrue(result.contains("[ ]"));
    }

    /**
     * Tests that unmarking a valid task returns a string containing a marked message.
     * Marking a task should not throw a CortanaException.
     */
    @Test
    void unmark_invalidTaskNumber_exceptionThrown() {
        taskList.add(new ToDo("Read book"));
        // User may think task number starts from 0
        assertThrows(CortanaException.class, () -> taskList.unmark(0));
        // User may mistakenly think there are more items than present
        assertThrows(CortanaException.class, () -> taskList.unmark(2));
    }
    /**
     * Tests that unmarking an already unmarked task throws a CortanaException.
     */
    @Test
    void unmark_alreadyUnmarkedTask_exceptionThrown() {
        taskList.add(new ToDo("Read book"));
        assertThrows(CortanaException.class, () -> taskList.unmark(1));
    }
}
