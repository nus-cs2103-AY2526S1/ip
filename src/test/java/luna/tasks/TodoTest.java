package luna.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testMark() {
        Todo t = new Todo("Read book", false);
        assertFalse(t.isDone()); // initially not done
        t.mark();
        assertTrue(t.isDone()); // should now be done
    }

    @Test
    void testToFileString() {
        Todo todo = new Todo("Exercise", false);
        todo.mark();
        assertEquals("T | 1 | Exercise", todo.toFileString());
    }

    @Test
    void testToString() {
        Todo todo = new Todo("Homework", false);
        assertEquals("[T][ ] Homework", todo.toString());
    }

}
