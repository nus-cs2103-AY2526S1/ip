package conversal.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link Deadline}.
 *
 * Verifies these expected behaviours:
 * - Constructor stores description and due date
 * - Mark/unmark toggles {@code isDone}
 * - {@code toString()} shows formatted date: {@code (by: MMM d yyyy)}
 */
class DeadlineTest {

    /** Constructor & getters - String format is correct */
    @Test
    void constructorAndGetters() {
        LocalDate due = LocalDate.of(2025, 9, 18);
        Deadline d = new Deadline("return book", due);

        assertEquals("return book", d.getDescription());
        assertEquals(due, d.getDueDate());
        assertFalse(d.isDone());
        assertEquals("[D][ ] return book (by: Sept 18 2025)", d.toString());
    }

    /** Marking complete should update completion status and reflect in {@code toString}. */
    @Test
    void markCompleteReflectedInToString() {
        Deadline d = new Deadline("submit", LocalDate.of(2030, 1, 1));
        d.markAsComplete();
        assertTrue(d.isDone());
        assertTrue(d.toString().startsWith("[D][X] submit (by: Jan 1 2030)"));
    }

    /** Marking incomplete reverts flag and {@code toString}. */
    @Test
    void markIncompleteResetsStatus() {
        Deadline d = new Deadline("submit", LocalDate.of(2030, 1, 1));
        d.markAsComplete();
        d.markAsIncomplete();
        assertFalse(d.isDone());
        assertTrue(d.toString().startsWith("[D][ ] submit (by: Jan 1 2030)"));
    }
}
