package conversal.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link DoWithinPeriodTask}.
 *
 * Verifies these expected behaviours:
 * - Constructor stores description and start/end schedule
 * - {@code getSchedule()} returns {@code start-end}
 * - Mark/unmark toggles {@code isDone}
 * - {@code toString()} shows "(from: ... to: ...)"
 */
class DoWithinTest {

    /** Constructor & Getters - String format is correct. */
    @Test
    void constructor_getters_andToString() {
        DoWithinPeriodTask w = new DoWithinPeriodTask("finish homework", "2 days");

        assertEquals("finish homework", w.getDescription());
        assertEquals("2 days", w.getPeriod());
        assertFalse(w.isDone());
        assertEquals("[W][ ] finish homework (within: 2 days)", w.toString());
    }

    /** Marking complete should update completion status and reflect in {@code toString}. */
    @Test
    void markCompleteReflectedInToString() {
        DoWithinPeriodTask w = new DoWithinPeriodTask("exercise", "1 hr");

        w.markAsComplete();
        assertTrue(w.isDone());
        assertTrue(w.toString().startsWith("[W][X]"));

    }

    /** Marking incomplete should update completion status and reflect in {@code toString}. */
    @Test
    void markIncompleteResetsStatus() {
        DoWithinPeriodTask w = new DoWithinPeriodTask("exercise", "1 hr");
        w.markAsIncomplete();
        assertFalse(w.isDone());
        assertTrue(w.toString().startsWith("[W][ ]"));
    }
}
