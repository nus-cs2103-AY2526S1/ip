package yang.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import yang.YangException;

/**
 * Tests the {@link TaskList} class.
 */
public class TaskListTest {

    @Test
    void markAndUnmark_toggleDoneFlag() throws YangException {
        TaskList tl = new TaskList();
        Todo t = new Todo("read book");
        tl.add(t);

        assertFalse(t.isDone());
        tl.mark(0);
        assertTrue(t.isDone());
        tl.unmark(0);
        assertFalse(t.isDone());
    }

    @Test
    void addAndRemove_updatesSizeAndReturnsRemovedTask() throws YangException {
        TaskList tl = new TaskList();
        tl.add(new Todo("a"));
        tl.add(new Todo("b"));
        assertEquals(2, tl.size());

        Task removed = tl.remove(0);
        assertEquals(1, tl.size());
        assertTrue(removed.toString().contains("a")); // sanity on removed item
    }
}
