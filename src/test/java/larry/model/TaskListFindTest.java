package larry.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TaskListFindTest {

    @Test
    void find_isCaseInsensitive_andPartial() {
        TaskList tl = new TaskList();
        tl.add(new Todo("Read Book"));
        tl.add(new Deadline("submit report", "2025-12-31"));
        tl.add(new Event("team meeting", "2025-01-01 1000", "2025-01-01 1100"));

        List<Task> a = tl.find("book");
        assertEquals(1, a.size());
        assertTrue(a.get(0).toString().toLowerCase().contains("read"));

        List<Task> b = tl.find("MEET");
        assertEquals(1, b.size());
        assertTrue(b.get(0).toString().toLowerCase().contains("meet"));

        List<Task> c = tl.find("xyz");
        assertTrue(c.isEmpty());
    }

    @Test
    void get_delete_indexBounds() {
        TaskList tl = new TaskList();
        tl.add(new Todo("one"));
        tl.add(new Todo("two"));

        assertEquals("one", tl.get(1).getDescription());
        Task removed = tl.delete(1);
        assertTrue(removed.toString().contains("one"));
        assertEquals(1, tl.size());
        assertEquals("two", tl.get(1).getDescription());

        assertFalse(tl.isValidIndex(0));
        assertFalse(tl.isValidIndex(-3));
        assertFalse(tl.isValidIndex(99));
    }
}
