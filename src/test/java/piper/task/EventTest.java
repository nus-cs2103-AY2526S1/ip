package piper.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import piper.PiperException;

class EventTest {

    @Test
    void toSerializedLine_writesExpectedFormat() throws PiperException {
        Event e = new Event("project meeting", "2025-09-01", "2025-09-02");
        String line = e.toSerializedLine();
        assertEquals("E | 0 | project meeting | 2025-09-01 | 2025-09-02", line);
    }

    @Test
    void toString_reformatsDate() throws PiperException {
        Event e = new Event("project meeting", "2025-09-01", "2025-09-02");
        String s = e.toString();
        assertTrue(s.contains("Sep 1 2025"));
        assertTrue(s.contains("Sep 2 2025"));
    }

    @Test
    void toString_keepsRawText() throws PiperException {
        Event e = new Event("party", "next Friday", "Saturday evening");
        String s = e.toString();
        assertTrue(s.contains("next Friday"));
        assertTrue(s.contains("Saturday evening"));
    }
}
