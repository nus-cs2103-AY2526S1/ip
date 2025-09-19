package sheares.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() {
        Task test = new Todo("play badminton");
        String res = test.toString();
        assertEquals("[T][ ] play badminton", res);
    }

    @Test
    public void testTaskToStr() {
        Task test = new Todo("read books");
        String res = test.taskToStr();
        assertEquals("T | 0 | read books", res);
    }

    @Test
    public void testMark() {
        Task test = new Todo("read books");
        test.mark();
        assertTrue(test.isDone);
    }

    @Test
    public void testUnMark() {
        Task test = new Todo("read books");
        test.unmark();
        assertFalse(test.isDone);
    }
}
