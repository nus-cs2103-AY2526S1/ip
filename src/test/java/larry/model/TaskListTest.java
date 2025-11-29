package larry.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    void add_get_delete_basicFlow() {
        TaskList tl = new TaskList();
        assertEquals(0, tl.size());

        tl.add(new Todo("read"));
        tl.add(new Todo("write"));
        assertEquals(2, tl.size());

        Task t1 = tl.get(1);
        assertTrue(t1.toString().toLowerCase().contains("read"));

        Task removed = tl.delete(1);
        assertTrue(removed.toString().toLowerCase().contains("read"));
        assertEquals(1, tl.size());

        Task remaining = tl.get(1);
        assertTrue(remaining.toString().toLowerCase().contains("write"));
    }

    @Test
    void isValidIndex_bounds() {
        TaskList tl = new TaskList();
        tl.add(new Todo("one"));
        assertTrue(tl.isValidIndex(1));
        assertFalse(tl.isValidIndex(0));
        assertFalse(tl.isValidIndex(2));
        assertFalse(tl.isValidIndex(-5));
    }
}
