package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Event}.
 */
public class EventTest {

    @Test
    public void toString_prefixAndFromTo_included() {
        final Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        final String output = e.toString();
        assertTrue(output.startsWith("[E]"), "toString() should start with [E].");
        assertTrue(output.contains("from:") && output.contains("to:"),
                "toString() should include 'from' and 'to'.");
    }

    @Test
    public void typeCode_returnsE() {
        final Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        assertEquals("E", e.typeCode());
    }

    @Test
    public void extraFieldsForSave_returnsIsoDates() {
        final Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        final String[] fields = e.extraFieldsForSave();
        assertEquals("2025-08-29T14:00:00", fields[0]);
        assertEquals("2025-08-29T16:00:00", fields[1]);
    }

    @Test
    public void toSaveString_containsTypeDoneNameAndIsos() {
        final Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        final String s = e.toSaveString();
        assertTrue(s.startsWith("E|0|team meeting|2025-08-29T14:00:00|2025-08-29T16:00:00"));
    }
}
