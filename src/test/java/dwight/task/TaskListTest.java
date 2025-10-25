package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TaskList}.
 *
 * <p>Covers adding, deleting, marking/unmarking, filtering, numbering, and serialization.
 */
class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
    }

    /** Adding a unique task increases size by one. */
    @Test
    void addUniqueTaskIncreasesSize() throws Exception {
        ToDo t = new ToDo("Buy milk");

        this.taskList.add(t);

        assertEquals(1, this.taskList.size(), "Size should be 1 after adding one unique task");
    }

    /** Adding the exact same task twice throws {@link DuplicateTaskException}. */
    @Test
    void addDuplicateTaskThrowsException() throws Exception {
        Task task = new ToDo("Buy milk");
        this.taskList.add(task);

        assertThrows(
                DuplicateTaskException.class,
                () -> this.taskList.add(task),
                "Adding the same task twice should throw DuplicateTaskException");
    }

    /** Deleting by index returns the same instance and reduces size. */
    @Test
    void deleteRemovesTaskAndReducesSize() throws Exception {
        Task task = new ToDo("Read book");
        this.taskList.add(task);

        Task deleted = this.taskList.delete(0);

        assertEquals(task, deleted, "delete(int) should return the removed task");
        assertEquals(0, this.taskList.size(), "Size should be 0 after deleting the only task");
    }

    /** mark(int) and unmark(int) toggle completion state. */
    @Test
    void markAndUnmarkTogglesCompletion() throws Exception {
        Task task = new ToDo("Exercise");
        this.taskList.add(task);

        Task marked = this.taskList.mark(0);
        assertTrue(marked.isMarked(), "Task should be marked after mark()");

        Task unmarked = this.taskList.unmark(0);
        assertFalse(unmarked.isMarked(), "Task should be unmarked after unmark()");
    }

    /** filtered(String) returns only matching tasks and does not mutate the original list. */
    @Test
    void filteredReturnsMatchingTasksOnly() throws Exception {
        this.taskList.add(new ToDo("Buy milk"));
        this.taskList.add(new ToDo("Read book"));
        int originalSize = this.taskList.size();

        TaskList filtered = this.taskList.filtered("milk");

        assertEquals(1, filtered.size(), "Filtered list should contain only one matching task");
        assertTrue(
                filtered.toString().contains("Buy milk"),
                "Filtered view should include 'Buy milk'");
        assertEquals(originalSize, this.taskList.size(), "Original list should remain unchanged");
    }

    /** toString() numbers tasks starting from 1 with correct task formatting. */
    @Test
    void toStringNumbersTasksCorrectly() throws Exception {
        this.taskList.add(new ToDo("Task A"));
        this.taskList.add(new ToDo("Task B"));

        String result = this.taskList.toString();

        assertTrue(result.contains("1. [T][ ] Task A"), "First task should be numbered '1.'");
        assertTrue(result.contains("2. [T][ ] Task B"), "Second task should be numbered '2.'");
    }

    /** serialize() outputs one task per line. */
    @Test
    void serializeOutputsOneTaskPerLine() throws Exception {
        this.taskList.add(new ToDo("Task A"));
        this.taskList.add(new ToDo("Task B"));

        String serialized = this.taskList.serialize();

        assertTrue(serialized.contains("Task A"), "Serialized output should include 'Task A'");
        assertTrue(serialized.contains("Task B"), "Serialized output should include 'Task B'");
        assertTrue(
                serialized.contains("\n"), "Serialized output should contain a newline separator");
    }
}
