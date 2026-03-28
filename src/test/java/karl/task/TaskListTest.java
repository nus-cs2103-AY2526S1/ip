package karl.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import karl.exception.KarlException;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void add_singleTask_taskListIncreasesInSize() {
        TaskList list = new TaskList();
        assertEquals(0, list.size());
        list.add(new Todo("Read book"));
        assertEquals(1, list.size());
    }

    @Test
    public void delete_existingIndex_removesAndReturnsTask() throws KarlException {
        TaskList list = new TaskList();
        Todo t = new Todo("sample");
        list.add(t);
        Task removed = list.delete(0);
        assertEquals(t, removed);
        assertEquals(0, list.size());
    }

    @Test
    public void delete_invalidIndex_throwsKarlException() {
        TaskList list = new TaskList();
        assertThrows(KarlException.class, () -> list.delete(0));
    }

    @Test
    public void mark_markTask_taskIsMarkedDone() throws KarlException {
        TaskList list = new TaskList();
        list.add(new Todo("Task"));
        assertFalse(list.get(0).isDone());
        list.mark(0);
        assertTrue(list.get(0).isDone());
    }

    @Test
    public void mark_invalidIndex_throwsKarlException() {
        TaskList list = new TaskList();
        assertThrows(KarlException.class, () -> list.mark(1));
    }

    @Test
    public void unmark_unmarkTask_taskIsMarkedNotDone() throws KarlException {
        TaskList list = new TaskList();
        list.add(new Todo("Task"));
        list.mark(0); // Now it is done
        list.unmark(0);
        assertFalse(list.get(0).isDone());
    }

    @Test
    public void unmark_invalidIndex_throwsKarlException() {
        TaskList list = new TaskList();
        assertThrows(KarlException.class, () -> list.unmark(1));
    }

    @Test
    public void get_existingIndex_returnsTask() {
        TaskList list = new TaskList();
        Todo t = new Todo("Check get");
        list.add(t);
        assertEquals(t, list.get(0));
    }

    @Test
    public void size_afterAddingTasks_returnsCorrectSize() {
        TaskList list = new TaskList();
        list.add(new Todo("One"));
        list.add(new Todo("Two"));
        assertEquals(2, list.size());
    }
}

// Used AI to help clean up code and naming conventions etc, mainly small issues brought up through code reviews