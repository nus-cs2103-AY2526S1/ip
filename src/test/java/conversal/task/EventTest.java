package conversal.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link Event}.
 *
 * Verifies these expected behaviours:
 * - Constructor stores description and start/end schedule
 * - {@code getSchedule()} returns {@code start-end}
 * - Mark/unmark toggles {@code isDone}
 * - {@code toString()} shows "(from: ... to: ...)"
 */
class EventTest {

    /** Constructor & Getters - String format is correct. */
    @Test
    void constructorAndGetters() {
        Event e = new Event("project meeting", "Mon 2pm", "4pm");

        assertEquals("project meeting", e.getDescription());
        assertEquals("Mon 2pm-4pm", e.getSchedule());
        assertFalse(e.isDone());
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", e.toString());
    }

    /** Marking complete should update completion status and reflect in {@code toString}. */
    @Test
    void markCompleteReflectedInToString() {
        Event e = new Event("Party", "10am", "1pm");

        e.markAsComplete();
        assertTrue(e.isDone());
        assertTrue(e.toString().startsWith("[E][X]"));
    }

    /** Marking incomplete should update completion status and reflect in {@code toString}. */
    @Test
    void markIncompleteResetsStatus() {
        Event e = new Event("Party", "10am", "1pm");
        e.markAsIncomplete();
        assertFalse(e.isDone());
        assertTrue(e.toString().startsWith("[E][ ]"));
    }
}
