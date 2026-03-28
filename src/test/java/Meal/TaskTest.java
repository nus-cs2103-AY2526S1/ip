package Meal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    void markTest() {
        Task t = new Task("read book");
        t.mark();
        assertTrue(t.isMarked());
    }

    @Test
    void unmarkTest() {
        Task t = new Task("read book");
        t.mark();
        t.unmark();
        assertFalse(t.isMarked());
    }
}