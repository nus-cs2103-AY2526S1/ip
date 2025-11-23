package conversal.task;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import conversal.exception.ConversalException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link TaskList}.
 *
 * Ensures that core task operations behave correctly:
 * - Adding tasks increases size and preserves order
 * - Getting tasks returns the correct one, or throws if invalid
 * - Deleting tasks returns the removed task, updates size, or throws if invalid
 * - Marking/unmarking tasks updates status correctly, or throws if invalid
 * - Size and getList reflect the current state of the task list
 *
 * Each test is on one behavior, with {@link ConversalException}
 * expected to be thrown when indexes are out of range.
 */
class TaskListTest {

    /** Helper: checks if the string form of a task shows it as complete. */
    private static boolean looksComplete(Task t) {
        String s = t.toString();
        return s.contains("[X]");
    }

    /** Helper: checks if the string form of a task shows it as incomplete. */
    private static boolean looksIncomplete(Task t) {
        String s = t.toString();
        return s.contains("[ ]");
    }

    /** Adding tasks */
    @Test
    void addTask_addOne_increasesSizeByOne() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("buy milk"));
        assertEquals(1, list.size());
    }

    @Test
    void addTask_keepsOrder() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        Task t1 = new Todo("first");
        Task t2 = new Todo("second");
        list.addTask(t1);
        list.addTask(t2);
        assertSame(t1, list.get(0));
        assertSame(t2, list.get(1));
    }

    /** Getting tasks. */
    @Test
    void get_validIndex_returnsThatTask() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        Task t = new Todo("hello");
        list.addTask(t);
        assertSame(t, list.get(0));
    }

    @Test
    void get_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("only one"));
        assertThrows(ConversalException.class, () -> list.get(1));
    }

    /** Deleting tasks. */
    @Test
    void deleteTask_validIndex_returnsRemovedTask() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        Task t1 = new Todo("a");
        Task t2 = new Todo("b");
        list.addTask(t1);
        list.addTask(t2);

        Task removed = list.deleteTask(0);

        assertSame(t1, removed);
    }

    @Test
    void deleteTask_validIndex_decreasesSize() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("a"));
        list.addTask(new Todo("b"));
        list.deleteTask(0);
        assertEquals(1, list.size());
    }

    @Test
    void deleteTask_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("a"));
        assertThrows(ConversalException.class, () -> list.deleteTask(5));
    }

    /** Marking Complete and Incomplete test */
    @Test
    void markTaskComplete_setsTaskToComplete() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("do it"));
        list.markTaskComplete(0);
        assertTrue(looksComplete(list.get(0)));
    }

    @Test
    void markTaskIncomplete_setsTaskBackToIncomplete() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("do it"));
        list.markTaskComplete(0);
        list.markTaskIncomplete(0);
        assertTrue(looksIncomplete(list.get(0)));
    }

    @Test
    void markTaskComplete_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        assertThrows(ConversalException.class, () -> list.markTaskComplete(0));
    }

    @Test
    void markTaskIncomplete_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        assertThrows(ConversalException.class, () -> list.markTaskIncomplete(0));
    }

    /** Size and list test */
    @Test
    void size_returnsZeroForNewList() {
        TaskList list = new TaskList(new ArrayList<>());
        assertEquals(0, list.size());
    }

    @Test
    void getList_reflectsChanges() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("x"));
        assertEquals(1, list.getList().size());
    }
}
