package conversal.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link Todo}.
 *
 * Verifies these expected behaviours:
 * - Constructor stores description
 * - Mark/unmark toggles {@code isDone}
 * - {@code toString()} format matches {@code [T][ ] desc} / {@code [T][X] desc}
 */
class TodoTest {

    /** Constructor should set description. */
    @Test
    void constructor_andGetters() {
        Todo t = new Todo("read newspaper");
        assertEquals("read newspaper", t.getDescription());
        assertFalse(t.isDone());
        assertEquals("[T][ ] read newspaper", t.toString());
    }

    /** Marking complete should set {@code isDone=true} and show 'X' in {@code toString}. */
    @Test
    void markComplete_thenToStringShowsX() {
        Todo t = new Todo("read newspaper");
        t.markAsComplete();
        assertTrue(t.isDone());
        assertEquals("[T][X] read newspaper", t.toString());
    }

    /** Marking incomplete should reset {@code isDone=false} and show a ' ' in {@code toString}. */
    @Test
    void markIncomplete_resetsStatus() {
        Todo t = new Todo("read newspaper");
        t.markAsComplete();
        t.markAsIncomplete();
        assertFalse(t.isDone());
        assertEquals("[T][ ] read newspaper", t.toString());
    }
}
